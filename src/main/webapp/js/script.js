function killSession (id){
    $.ajax({
        url:'/grid/admin/ControlPanel/sessions/doDelete',
        data:{
            sessionId: id
        },
        success:function(){
              location.reload();
        }
    });
}