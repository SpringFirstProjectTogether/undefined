
window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }



    function DropFile(dropAreaId, fileListId) {
        let dropArea = document.getElementById(dropAreaId);
        let fileList = document.getElementById(fileListId);

        function preventDefaults(e) {
            e.preventDefault();
            e.stopPropagation();
        }

        function highlight(e) {
            preventDefaults(e);
            dropArea.classList.add("highlight");
        }

        function unhighlight(e) {
            preventDefaults(e);
            dropArea.classList.remove("highlight");
        }

        function handleDrop(e) {
            unhighlight(e);
            let dt = e.dataTransfer;
            let files = dt.files;

            handleFiles(files);

            const fileList = document.getElementById(fileListId);
            if (fileList) {
                fileList.scrollTo({ top: fileList.scrollHeight });
            }
        }

        function handleFiles(files) {
            files = [...files];
            files.forEach(previewFile);
        }

        function previewFile(file) {
            console.log(file);
            fileList.appendChild(renderFile(file));
        }

        function renderFile(file) {
            let fileDOM = document.createElement("div");
            fileDOM.className = "file";
            fileDOM.innerHTML = `
            <div class="thumbnail">
              <img src="https://img.icons8.com/pastel-glyph/2x/image-file.png" alt="파일타입 이미지" class="image">
            </div>
            <div class="details">
              <header class="header">
                <span class="name">${file.name}</span>
                <span class="size">${file.size}</span>
              </header>
              <div class="progress">
                <div class="bar"></div>
              </div>
              <div class="status">
                <span class="percent">${file.path}</span>
                <span class="speed">90KB/sec</span>
              </div>
            </div>
          `;
            return fileDOM;
        }

        dropArea.addEventListener("dragenter", highlight, false);
        dropArea.addEventListener("dragover", highlight, false);
        dropArea.addEventListener("dragleave", unhighlight, false);
        dropArea.addEventListener("drop", handleDrop, false);

        return {
            handleFiles
        };
    }

    const dropFile = new DropFile("drop-file", "files");

    const frm = document.body.querySelector('.writeForm');

    const compBtn = document.body.querySelector('.compBtn');
    compBtn.addEventListener('click', () => {
        document.querySelector('#feedState').setAttribute('value', 'comp');
        frm.submit();
    });

    const tempBtn = document.body.querySelector('.tempBtn');
    tempBtn.addEventListener('click', () => {
        document.querySelector('#feedState').setAttribute('value', 'temp');
        frm.submit();
    });

    const cancelBtn = document.body.querySelector('.cancelBtn');
    cancelBtn.addEventListener('click', () => {
        // 취소할 시 완료글 페이지로 이동
        // frm.submit();
        location.href = `list`;
    });

});