document.addEventListener('DOMContentLoaded', () => {
  const dropArea = document.getElementById('drop-area');
  const input    = document.getElementById('photoInput');
  const preview  = document.getElementById('photoPreview');
  const dropText = document.getElementById('drop-text');
  const base64Fld= document.getElementById('photoBase64');
  // 画像が初期表示されている場合はfilledクラスを付与
  if (
    preview &&
    preview.src &&
    (
      preview.src.startsWith('data:image') || // Base64画像
      (preview.src && !preview.src.includes('placeholder.png')) // placeholder以外のURL画像
    )
  ) {
    dropArea.classList.add('filled');
    if (dropText) dropText.style.display = 'none';
  }
//  if (preview && preview.src && !preview.src.includes('placeholder.png')) {
//    dropArea.classList.add('filled');
//  }

  // ブラウザのデフォルト挙動をキャンセル
  ['dragenter','dragover','dragleave','drop'].forEach(evt =>
    document.addEventListener(evt, e => {
      e.preventDefault();
      e.stopPropagation();
    })
  );

  // ドロップエリアのハイライト
  dropArea.addEventListener('dragover', () => dropArea.classList.add('dragover'));
  dropArea.addEventListener('dragleave', () => dropArea.classList.remove('dragover'));

  // ドロップ時
  dropArea.addEventListener('drop', e => {
    e.preventDefault(); e.stopPropagation();
    dropArea.classList.remove('dragover');

    const file = e.dataTransfer.files[0];
    if (!file || !file.type.startsWith('image/')) return;

    const reader = new FileReader();
    reader.onload = () => {
      // ● 画像をそのままpreviewにセット（貼り付け表示）
      preview.src = reader.result;
      dropArea.classList.add('filled');
      // ● Base64文字列を隠しフィールドに格納
      base64Fld.value = reader.result;
	  dropText.style.display = 'none';
    };
    reader.readAsDataURL(file);
  });

  // クリックでも選択可能
  dropArea.addEventListener('click', () => input.click());
  input.addEventListener('change', () => {
    const file = input.files[0];
    if (!file || !file.type.startsWith('image/')) return;
    const reader = new FileReader();
    reader.onload = () => {
      preview.src = reader.result;
      dropArea.classList.add('filled');
      base64Fld.value = reader.result;
	  dropText.style.display = 'none'; // 案内テキストを非表示
    };
    reader.readAsDataURL(file);
  });
});
