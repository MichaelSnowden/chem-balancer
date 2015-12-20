<!DOCTYPE html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
          integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
            integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
            crossorigin="anonymous"></script>

    <script>
        $(function () {
            $("form").submit(function (event) {
                event.preventDefault();
                $.get("/solve", function (data) {
                    $("#answer").text(data);
                });
            });
        });
    </script>
</head>

<body>
<div class="container" style="margin-top:50px;">
    <div class="row">
        <div class="col-lg-12">

            <div class="row">

                <div class="col-lg-12">
                    <form>
                        <div class="form-group">
                            <input id="equation" name="equation" type="text" class="form-control"
                                   placeholder="CH4 + O2 = CO2 + H2O">
                        </div>
                    </form>
                </div>
            </div>
            <div class="panel">
                <div class="col-lg-12" id="answer">
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>