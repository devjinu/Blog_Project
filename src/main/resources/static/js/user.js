/* ajax 사용하는 이유
*   1. 요청에 대한 응답을 html이 아닌 data(Json)를 받기 위해
*   2. 비동기 통신을 하기 위해 */

let index = {
    init: function (){
        $("#btn-save").on("click", ()=>{
            this.save();
        });

        $("#btn-login").on("click", ()=>{
            this.login();
        });

    },
    save:function (){
        // alert('user의 save함수 호출');
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }

        // console.log(data);

        // ajax를 이용해서 3개의 데이터를 Json타입으로 변경하여 insert
        // ajax 호출 시 default가 비동기 호출
        // ajax가 통신을 성공하고 Json을 리턴해주면, 서버가 자동으로 java object로 변환해줌
         $.ajax({
            type: "POST",
            url: "/blog/api/user",
            data: JSON.stringify(data), // http body데이터
            contentType: "application/json; charset=utf-8", // body데이터 타입
            dataType: "json" // response결과가 json이면 javascript object로 변환
        }).done(function (resp){
           // alert(resp);
           // console.log(resp);
            alert("회원가입이 완료되었습니다.");
            location.href="/blog";
        }).fail(function (error){
            alert(JSON.stringify(error));
            
        });
    },

    login:function (){
        let data = {
            username: $("#username").val(),
            password: $("#password").val()
        }

        $.ajax({
            type: "POST",
            url: "/blog/api/user/login",
            data: JSON.stringify(data), // http body데이터
            contentType: "application/json; charset=utf-8", // body데이터 타입
            dataType: "json" // response결과가 json이면 javascript object로 변환
        }).done(function (resp){
            // alert(resp);
            // console.log(resp);
            alert("로그인이 완료되었습니다.");
            location.href="/blog";
        }).fail(function (error){
            alert(JSON.stringify(error));

        });
    }
    
}
index.init();

