let index = {
    init: function () {
        $("#btn-save").on("click", () => { //()=> : this 를 binding 하기 위해서 사용
            this.save();
        }),
        $("#btn-update").on("click", () => { //()=> : this 를 binding 하기 위해서 사용
            this.update();
        });
    },
    save: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password").val()
        }
        console.log(data);
        // ajax 통신을 통해서 입력받은 데이터 input 요청
        // ajax 호출 시 default 가 비동기 호출
        $.ajax({
            // 회원가입 수행 요청
            type:"POST",
            url:"/auth/joinProc",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            if(resp.status === 500){
                console.log(resp);
                alert("회원가입 실패");
            }else{
                console.log(resp);
                alert("회원가입 실패");
                location.href = "/";
            }

        }).fail(function(error){
                alert(JSON.stringify(error));
        });
    },
    update: function () {
        let data = {
            id: $("#id").val(),
            username: $("#username").val(),
            password: $("#password").val()
        }
        console.log(id);

        $.ajax({
            // 회원가입 수행 요청
            type: "PUT",
            url: "/user/update/",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json; charset=utf-8"
        }).done(function (resp) {
            console.log(resp);
            alert("회원정보 수정 완료");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
            console.log(JSON.stringify(error));
        });
    }
}


index.init();