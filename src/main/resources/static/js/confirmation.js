
$(function() {
    $("#task-table").dataTable({
      
      // DataTablesを日本語化する
      language: {
        url: "/webjars/datatables-plugins/i18n/Japanese.json"
      },
      lengthMenu: [5, 10, 20],
      /* dom:"<'row'<'col-sm-6'l><'col-sm-6 right'i>>" +
            "<'row'<'col-sm-12'tr>>" +
            "<'row'<'col-sm-12'p>>" */
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


function addButton() {
  const parent = document.getElementById("parent");
  let count = document.getElementsByClassName("count").length
  //--------------------------【DEPARTMENT】----------------------------
  const department = document.getElementById("department").cloneNode(true)
  department.children[0].name = "taskList[" + count + "].department"
  //--------------------------【EMPLOYEEID】----------------------------
  const employeeId = document.getElementById("employeeId").cloneNode(true)
  employeeId.children[0].name = "taskList[" + count + "].employeeId"
  //-----------------------------【AREA】----------------------------
  const area = document.getElementById("area").cloneNode(true)
  area.children[0].name = "taskList[" + count + "].area"
  //-----------------------------【SALES】----------------------------
  const sales = document.getElementById("sales").cloneNode(true)
  sales.children[0].name = "taskList[" + count + "].sales"
  //--------------------------【CUSTOMERS】----------------------------
  const customers = document.getElementById("customers").cloneNode(true)
  customers.children[0].name = "taskList[" + count + "].customers"
  //--------------------------【UPDATEDATE】----------------------------
  const updateDate = document.getElementById("updateDate").cloneNode(true)
  updateDate.children[0].name = "taskList[" + count + "].updateDate"
  //--------------------------------------------------------------------
  let tr = document.createElement("tr")
  tr.className = "count"
  tr.appendChild(department)
  tr.appendChild(employeeId)
  tr.appendChild(area)
  tr.appendChild(sales)
  tr.appendChild(customers)
  tr.appendChild(updateDate)
  parent.appendChild(tr)
}
