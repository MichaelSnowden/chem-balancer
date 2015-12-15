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
                $.ajax({
                    method: "GET",
                    url: "https://juniper-chemistry.herokuapp.com/solve",
                    data: $(this).serialize(),
                    success: function (data) {
                        math = document.createElement("math");

//                        <math xmlns="http://www.w3.org/1998/Math/MathML" display="block">
//                                <mrow>
//                                <mi>x</mi>
//                                <mo>=</mo>
//                                <mfrac>
//                                <mrow>
//                                <mo>&#x2212;</mo>
//                        <mi>b</mi>
//                        <mo>&#xB1;</mo>
//                        <msqrt>
//                        <mrow>
//                        <msup>
//                        <mi>b</mi>
//                        <mn>2</mn>
//                        </msup>
//                        <mo>&#x2212;</mo>
//                        <mn>4</mn>
//                        <mi>a</mi>
//                        <mi>c</mi>
//                        </mrow>
//                        </msqrt>
//                        </mrow>
//                        <mrow>
//                        <mn>2</mn>
//                        <mi>a</mi>
//                        </mrow>
//                        </mfrac>
//                        </mrow>
//                        </math>

                        $("#answer").text(data);
                    }

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