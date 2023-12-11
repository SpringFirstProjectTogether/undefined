$(document).ready(function () {

    // sidebar toggle
    const sidebarToggle = $('#sidebarToggle');
    if (sidebarToggle) {
        if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
            document.body.classList.toggle('sb-sidenav-toggled');
        }
        sidebarToggle.click(function (event) {
            event.preventDefault();
            $(`body`).toggleClass('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', $(`body`).hasClass('sb-sidenav-toggled'));
        })
    }

    // search button
    const searchBtn = $('#searchSubmit');
    searchBtn.click(function (event) {
        $('#searchForm').submit();
    })

    $(`[id^="likeBtn"]`).click(function () {
        // console.log(this);
        const feedId = this.id.slice('likeBtn'.length);
        // console.log(feedId);
        let data;
        if ($(this).hasClass('like')) { // 좋아요를 누른 상태
            data = {
                isLike: false,
                feedId: feedId
            }
        } else {
            data = {
                isLike: true,
                feedId: feedId
            }
        }

        $.ajax({
            type: "POST",
            url: "/rest/like",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
        }).done((response) => {
            $(this).find('.likeIcon').toggleClass('visually-hidden');
            $(this).toggleClass("like");
            $(`#likeCnt` + feedId).text(response);
        });
    })

    $(`[id^='addComment']`).click(function() {
        const feedId = this.id.slice('addComment'.length);
        if($(`#isReply${feedId}`).hasClass('visually-hidden')) {
            $(`#commentFrm${feedId}`).submit();
        }
    })

    $(`[id^='cancelComment']`).click(function() {
        const feedId = this.id.slice('addComment'.length);
        $(`#placehold${feedId}`).value('');
    })

    $(`[id^="editBtn"]`).click(function() {
        const feedId = this.id.slice('editBtn'.length);
        $(`#formEdit${feedId}`).submit();
    })

    $(`[id^="deleteBtn"]`).click(function() {
        const feedId = this.id.slice('deleteBtn'.length);
        if (confirm("해당 피드글을 휴지통으로 보내시겠습니까??")) {
            $(`#formDelete${feedId}`).submit();
        }
    })

    $(`[id^='more']`).click(function() {
        console.log(this);
        const feedId = this.id.slice('more'.length);
        const content = $(`.feedText${feedId}`);

        let data;

        if(content.text().length > 100) {
            data = {
                isShort: true,  //짧은거 주셈
                feedId: feedId
            }
        } else {
            data = {
                isShort: false,  //긴거 주셈
                feedId: feedId
            }
        }

        $.ajax({
            type: "POST",
            url: "/community/shortContent",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
        }).done(response => {
            content.text(response);
            $(this).children(':first-child').text(data['isShort'] ? 'more...' : 'less...')
        })
    })

    $(`[id^='commentDelBtn']`).click(function() {
        const feedId = this.id.slice('commentDelBtn'.length);
        $(`#commentDel${feedId}`).submit();
    })

    $(`[id^='replyDelBtn']`).click(function() {
        const feedId = this.id.slice('replyDelBtn'.length);
        $(`#replyDel${feedId}`).submit();
    })

})
