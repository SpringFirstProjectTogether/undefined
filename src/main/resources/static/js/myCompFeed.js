
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

    const allDelete = document.body.querySelector('.deleteAll');
    allDelete.addEventListener('click', () => {
        console.log("click~!~!~!")
        if(confirm("모든 피드글을 삭제하시겠습니까? (삭제된 피드글은 휴지통에 보관됩니다.)")) {
            location.href='deleteAll';
        }
    })


});
