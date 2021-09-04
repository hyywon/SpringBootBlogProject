let index = {
    init: function () {
        $("#btn-save").on("click", () => { //()=> : this 를 binding 하기 위해서 사용
            this.save();
        }),
        $("#btn-warning").on("click", () => { //()=> : this 를 binding 하기 위해서 사용
            this.warn();
        }),
        $("#btn-update").on("click", () => { //()=> : this 를 binding 하기 위해서 사용
            this.update();
        });
    },
    save: function () {
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        }
        console.log(data);

        $.ajax({
            // 회원가입 수행 요청
            type:"POST",
            url:"/write",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            console.log(resp);
            alert("글쓰기 완료");
            location.href = "/";
        }).fail(function(error){
                alert(JSON.stringify(error));
        });
    },
    warn: function () {
        let id = $("#boardId").val();

        console.log(id);

        $.ajax({
            // 회원가입 수행 요청
            type:"DELETE",
            url:"/post/delete/"+id,
            dataType: "json",
            contentType: "application/json; charset=utf-8"
        }).done(function(resp){
            console.log(resp);
            alert("글 삭제 완료");
            location.href = "/";
        }).fail(function(error){
            console.log(JSON.stringify(error));
            alert(JSON.stringify(error));
        });
    },
    update: function () {
        let id = $("#id").val();

        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        }
        console.log(id);

        $.ajax({
            // 회원가입 수행 요청
            type:"PUT",
            url:"/post/update/"+id,
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json; charset=utf-8"
        }).done(function(resp){
            console.log(resp);
            alert("글 수정 완료");
            location.href = "/";
        }).fail(function(error){
            alert(JSON.stringify(error));
            console.log(JSON.stringify(error));
        });
    },

}


index.init();