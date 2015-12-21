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

    <script type="text/javascript"
            src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML">
    </script>

    <script type="text/javascript" async
            src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS_CHTML">
    </script>

    <script>
        function prettify(text) {
            return text.replace(/([A-Za-z])(\d+)/g, "$1_$2");
        }

        $(function () {
            $("form").submit(function (event) {
                event.preventDefault();
                var equation = $("#equation").val();
                $.get("https://chem-balancer.herokuapp.com/solve", {"equation": equation}, function (answer) {
                    $("#answer").text("$$" + prettify(equation) + " \\xrightarrow{Balance} " + prettify(answer) + "$$");
                    MathJax.Hub.Typeset()
                });
            });
        });
    </script>
</head>

<body>
<div class="container" style="margin-top:50px;">
    <div class="page-header">
        <h1> Online Chemical Equation Balancer
            <br/>
            <small> Enter a chemical equation below and press enter.</small>
        </h1>
    </div>

    <div class="row">
        <div class="col-lg-12">

            <div class="row">

                <div class="col-lg-12">
                    <form>
                        <div class="form-group">
                            <label for="equation" class="sr-only"></label>
                            <input id="equation" name="equation" type="text" class="form-control"
                                   placeholder="CH4 + O2 = CO2 + H2O">
                        </div>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-12" id="answer"></div>
            </div>
        </div>
    </div>
</div>

<footer class="footer">
    <div class="container">
        <p class="text-muted">Created by <a href="http://michaelsnowden.github.io/">Michael Snowden</a></p>
    </div>
</footer>

</body>
</html>