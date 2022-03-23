
$(function() {
    $("#task-table").dataTable({
      
      // DataTablesを日本語化する
      language: {
        url: "/webjars/datatables-plugins/i18n/Japanese.json"
      },
      lengthMenu: [5, 10, 20],
      
      // 各種ボタンを有効化する
      //dom: "Bfrtip",
      //buttons: ["excelHtml5", "csvHtml5", "print"]
    });
  });

check = (employeeId,name) => {
    let form = document.getElementsByTagName('form')[0]
    if (confirm('この作業は修正できません。本当によろしいですか。')) {
        form.action = "/task/delete/" + employeeId;
        form.method = "get";
        form.submit();
    }
}


update = (employeeId) => {
  let form = document.getElementsByTagName('form')[0]
      form.action = "/task/update/" + employeeId;
      form.method = "get";
      form.submit();
}



