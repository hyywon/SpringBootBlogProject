let index = {
    init: function () {
        $("#btn-join").on("click", () => { //()=> : this 를 binding 하기 위해서 사용
            this.save();
        });
        $("#btn-signin").on("click", () => {
            this.signin();
        });
    },
    save: function () {
        let data ={
            name: $("#name").val(),
            password: $("#password").val()
        }
        console.log(data);

        // ajax 통신을 통해서 입력받은 데이터 input 요청
        // ajax 호출 시 default 가 비동기 호출
        $.ajax({
            // 회원가입 수행 요청
            type:"POST",
            url:"/user/join",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            // dataType: "json"
        }).done(function(resp){
            console.log(resp);
            alert("회원가입 완료");
            location.href = "/";
        }).fail(function(error){
                alert("회원가입 실패");
        });
    },

    signin: function () {
        // alert("user Function");
        let data ={
            name: $("#name").val(),
            password: $("#password").val()
        }
        console.log(data);

        // ajax 통신을 통해서 입력받은 데이터 input 요청
        // ajax 호출 시 default 가 비동기 호출
        $.ajax({
            // 회원가입 수행 요청
            type:"POST",
            url:"/user/login",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            // dataType: "json"
        }).done(function(resp){
            console.log(resp);
            alert("로그인 완료");
            location.href = "/";
        }).fail(function(error){
            alert("로그인 실패");
        });
    }
}


index.init();