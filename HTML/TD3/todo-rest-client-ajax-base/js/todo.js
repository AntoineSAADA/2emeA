$(function () {

    $("#button").click(refreshTaskList);

    function refreshTaskList() {
        $("#currenttask").empty();
        $.ajax({
            url: "http://localhost:3000/tasks",
            type: "GET",
            dataType: "json",
            success: function (tasks) {
                console.log(JSON.stringify(tasks));
                $('#taches').empty();
                $('#taches').append($('<ul>'));
                for (let i = 0; i < tasks.length; i++) {
                    console.log(tasks[i]);
                    $('#taches ul')
                        .append($('<li>')
                            .append($('<a>')
                                .text(tasks[i].title)
                            ).on("click", tasks[i], details)
                        );
                }
            },
            error: function (req, status, err) {
                $("#taches").html("<b>Impossible de récupérer les taches à réaliser !</b>");
            }
        });
    }

    function details(event) {
        $("#currenttask").empty();
        formTask();
        fillFormTask(event.data);
    }

    // Objet Task en JS
    class Task {
        constructor(title, description, done, uri) {
            this.title = title;
            this.description = description;
            this.done = done;
            this.uri = uri;
            console.log(this.uri);
        }
    }


    $("#tools #add").on("click", formTask);
    $('#tools #del').on('click', delTask);

    function formTask(isnew) {
        $("#currenttask").empty();
        $("#currenttask")
            .append($('<span>Titre<input type="text" id="titre"><br></span>'))
            .append($('<span>Description<input type="text" id="descr"><br></span>'))
            .append($('<span>Done<input type="checkbox" id="done"><br></span>'))
            .append($('<span><input type="hidden" id="turi"><br></span>'))
            .append(isnew ? $('<span><input type="button" value="Save Task"><br></span>').on("click", saveNewTask)
                : $('<span><input type="button" value="Modify Task"><br></span>').on("click", saveModifiedTask)
            );
    }

    function fillFormTask(t) {
        // A compléter
    }

    function saveNewTask() {
        var task = new Task(
            $("#currenttask #titre").val(),
            $("#currenttask #descr").val(),
            $("#currenttask #done").is(':checked')
        );
        console.log(JSON.stringify(task));
        // A compléter 
        refreshTaskList();
    }

    function saveModifiedTask() {
        var task = new Task(
            $("#currenttask #titre").val(),
            $("#currenttask #descr").val(),
            $("#currenttask #done").is(':checked'),
            $("#currenttask #turi").val()
        );
        console.log("PUT");
        console.log(task.uri);
        console.log(JSON.stringify(task));
        // A compléter
        refreshTaskList();
    }

    function delTask() {
        if ($("#currenttask #turi").val()) {
            console.log("Suppr");
        }
        refreshTaskList();
    }

});
