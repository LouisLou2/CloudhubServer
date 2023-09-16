<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>文件选择器</title>
    <style>
        /* 样式自定义 */
        .modal {
            display: none;
            position: fixed;
            top: 10%;
            left: 35%;
            width: 0%;
            height: 50%;
            background-color: rgba(0, 0, 0, 0.5);
            justify-content: center;
            align-items: center;
        }

        .modal-content {
            background-color: #fff;
            width: 400px;
            height: 400px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
            text-align: center;
            padding: 20px;
            overflow: auto; /* 添加滚动条 */
        }

        .card {
            background-color: #f0f0f0;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin: 5px;
            padding: 10px;
        }

        .card a {
            text-decoration: none;
            color: #0073e6;
        }

        .button {
            background: linear-gradient(to bottom, #4CAF50, #4384F7);
            border: none;
            color: white;
            padding: 12px 24px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            border-radius: 5px;
            cursor: pointer;
        }

        .button:hover {
            background: linear-gradient(to bottom, #43A047, #3565C1);
        }
    </style>
</head>
<body>
<div id="fileChooserModal" class="modal">
    <div class="modal-content">
        <h2>选择保存位置</h2>
        <div id="folderListWidget">
            <!-- 文件夹列表将在JavaScript中填充 -->
        </div>
        <button id="confirmSaveButton" class="button">确认保存</button>
    </div>
</div>

<script>
    const fileChooserModal = document.getElementById('fileChooserModal');
    const folderListWidget = document.getElementById('folderListWidget');
    const confirmSaveButton = document.getElementById('confirmSaveButton');
    let items = [];
    let currentFolder = '0';
    let currentFolderName = '根目录';
    // 确认保存操作，可以根据需求添加保存逻辑
    confirmSaveButton.addEventListener('click', () => {
        alert(`文件将被保存在：${currentFolderName}`);
        fileChooserModal.style.display = 'none';
    });
    function updateFolderList() {
        fetch(`/childfolderinfo?folderId=${currentFolder}`) // 发送GET请求以获取指定路径的文件和文件夹列表
            .then(response => response.json())
            .then(data => {
                    folderListWidget.innerHTML = '';
                    items = data.childList;
                    for (const item of items) {
                        const card = document.createElement('div');
                        card.classList.add('card');
                        const folderLink = document.createElement('a');
                        folderLink.textContent = item.name;
                        folderLink.dataset.id = item.id;
                        folderLink.addEventListener('click', () => {
                            // 用户点击文件夹，进入下一级
                            currentFolder = folderLink.dataset.id;
                            currentFolderName=folderLink.textContent;
                            updateFolderList();
                        });
                        card.appendChild(folderLink);
                        folderListWidget.appendChild(card);
                    };
                }
            )
            .catch(error => {
                console.error('请求失败:', error);
                throw error; // 可选：将错误重新抛出以进行更多处理
            });
    }
    updateFolderList();
</script>
</body>
</html>
