let index = {
    init: function() {
        $("#btn-reply-save").on("click", ()=>{
            this.replySave();
        });
    },

    replySave: function() {
        let data = {
            boardId: $("#noticeId").val(),
            content: $("#reply-content").val()
        };

        console.log(data);

        $.ajax( {
            type: "POST",
            url: '/api/board/' + data.boardId + '/reply',
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp) {
            alert("댓글 작성이 완료되었습니다.");
            location.href = '/noticeView?id=' + data.boardId;
        }).fail(function(error) {
            alert(JSON.stringify(error));
        });
    }
}

index.init();