<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<body>
<h2>Hello World!</h2>
<button onclick ="download()" >jdxlkgjzg</button>
</body>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
    //    function bb() {
    //        var aa = $('#aa').val();
    //        $.ajax({
    //            type: "get",
    //            url: 'http://192.168.8.5:8090/AAEnglish/web/work/consultList.do',
    //            data: {studentName: aa},
    //            dataType: 'json',
    //            cache: false,63
    //            success: function (data) {
    //                alert(data.msg);
    //            },
    //            error: function () {
    //                alert(11);
    //            },
    // });


    function download(){

        $.ajax({
            url:"http://19124za186.imwork.net/AAEnglish/app/person/register.do?username=12345678930&password=12345&openId=1234564754",
            type:'get',
            dataType:'json',
            success:function(response){
                alert(JSON.stringify(response))

            },
            error:function(error){
                alert(JSON.stringify(error))
            }
        })
    }


</script>
</html>

