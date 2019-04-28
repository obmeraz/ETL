<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="ETL роцесс    ">

    <title>Начать ETL</title>

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
    <!-- Custom styles for this template -->
    <link href="../css/mainETL.css" rel="stylesheet">

</head>
<body>

<script type="text/javascript" async="" src="https://mc.yandex.ru/metrika/watch.js"></script>
<script async="" src="https://www.google-analytics.com/analytics.js"></script>
<script>
    (function (i, s, o, g, r, a, m) {
        i['GoogleAnalyticsObject'] = r;
        i[r] = i[r] || function () {
            (i[r].q = i[r].q || []).push(arguments)
        }, i[r].l = 1 * new Date();
        a = s.createElement(o),
            m = s.getElementsByTagName(o)[0];
        a.async = 1;
        a.src = g;
        m.parentNode.insertBefore(a, m)
    })(window, document, 'script', 'https://www.google-analytics.com/analytics.js', 'ga');

    ga('create', 'UA-4481610-59', 'auto');
    ga('send', 'pageview');

</script>

<!-- Yandex.Metrika counter -->
<script type="text/javascript"> (function (d, w, c) {
    (w[c] = w[c] || []).push(function () {
        try {
            w.yaCounter39705265 = new Ya.Metrika({
                id: 39705265,
                clickmap: true,
                trackLinks: true,
                accurateTrackBounce: true,
                webvisor: true
            });
        } catch (e) {
        }
    });
    var n = d.getElementsByTagName("script")[0], s = d.createElement("script"), f = function () {
        n.parentNode.insertBefore(s, n);
    };
    s.type = "text/javascript";
    s.async = true;
    s.src = "https://mc.yandex.ru/metrika/watch.js";
    if (w.opera == "[object Opera]") {
        d.addEventListener("DOMContentLoaded", f, false);
    } else {
        f();
    }
})(document, window, "yandex_metrika_callbacks"); </script>
<noscript>
    <div><img src="https://mc.yandex.ru/watch/39705265" style="position:absolute; left:-9999px;" alt="Yandex.Metrika"/>
    </div>
</noscript> <!-- /Yandex.Metrika counter -->

<c:import url="/jsp/header.jsp"/>

<main role="main">


    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

    <div class="container marketing">

        <!-- Three columns of text below the carousel -->
        <div class="row text-center">
            <div class="col-lg-6">
                <img class="bd-placeholder-img rounded-circle" src="../img/DB.jpg"
                     width="140" height="140"></img>
                <h2>Разворачивание базы данных</h2>
                <h2>+ ETL</h2>
                <p>При выборе этого варианта у вас будет возможность создать все таблицы, последовательности, процедуры
                    и пакеты для каждого из слоев(про значение которых вы можете прочитать ниже). Этот процесс
                    называется англоязычным термином "Initial Load" означает что все ETL система будеи создана с
                    нуля.</p>
                <c:if test="${sessionScope.role eq 'ADMIN'}">
                    <p><a class="btn btn-success" role="button" id="btnFetch"
                          href="${pageContext.request.contextPath}/controller?command=init_load_db">
                        Начать разворачивание базы » </a></p>
                </c:if>
            </div><!-- /.col-lg-6 -->
            <div class="col-lg-6">
                <img class="bd-placeholder-img rounded-circle"
                     src="../img/etl-vs-elt.png" width="140" height="140"></img>
                <h2>ETL на существующей</h2>
                <h2>базе</h2>
                <p>При выборе этого варианта предполагается что все необходимые таблицы, последовательности, процедуры и
                    пакеты(со всеми необходимыми им правами) уже созданы. Процесс при этом называется "Incremental Load"
                    и запускается не при первой загрузке, а при последующей ежедневной загрузке новых данных.</p>
                <p><a class="btn btn-success" id="btnFetch3"
                      href="${pageContext.request.contextPath}/controller?command=increment_load_db" role="button">
                    Начать инкрементальную загрузку » </a></p>
            </div><!-- /.col-lg-6 -->
        </div><!-- /.row -->


        <!-- START THE FEATURETTES -->

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7 order-md-2">
                <h2 class="featurette-heading" style=" margin-bottom: 20px">Данные из источников</h2>
                <p class="lead">Этот слой самый простой) Он всего лишь копирует пришедшую от источников информацию в
                    таком виде, в котором она пришла. Это необходимо для того чтобы не нарушать оригинальные данные и
                    для более быстрого доступа к ним.</p>
            </div>
            <div class="col-md-5 order-md-1">
                <svg class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500"
                     height="500" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice"
                     focusable="false" role="img" aria-label="Placeholder: 500x500"><title>Placeholder</title>
                    <rect fill="#876591" width="100%" height="100%"></rect>
                    <text fill="#fff" dy=".3em" x="50%" y="50%">Data Source</text>
                </svg>
            </div>
        </div>

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading" style="margin-top: 0; margin-bottom: 20px">Staging area or Landing
                    zone.</h2>
                <p class="lead">Промежуточная область или зона посадки-это промежуточная область хранения, используемая
                    для обработки данных в процессе извлечения, преобразования и загрузки (ETL). Промежуточная область
                    данных находится между источником(источниками) и целью(целями) данных, которые часто являются
                    хранилищами данных, витринами данных или другими хранилищами данных.[1]

                    Промежуточные области данных часто являются временными по своему характеру, при этом их содержимое
                    стирается до запуска процесса ETL или сразу после успешного завершения процесса ETL. Однако
                    существуют архитектуры промежуточных областей, предназначенные для хранения данных в течение
                    длительного периода времени в целях архивирования или устранения неполадок</p>
            </div>
            <div class="col-md-5">
                <svg class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500"
                     height="500" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice"
                     focusable="false" role="img" aria-label="Placeholder: 500x500"><title>Placeholder</title>
                    <rect fill="#48a85e" width="100%" height="100%"></rect>
                    <text fill="#fff" dy=".3em" x="50%" y="50%">Staging area</text>
                </svg>
            </div>
        </div>

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7 order-md-2">
                <h2 class="featurette-heading" style=" margin-bottom: 20px">Подхода Билла Инмона</h2>
                <p class="lead">Использование третьей нормальной формы для организации атомарных данных, что
                    обеспечивает высокую степень детальности интегрированных данных и, соответственно, предоставляет
                    корпорациям широкие возможности для манипулирования ими и изменения формата и способа представления
                    данных по мере необходимости.
                    Хранилище данных - это не механическая коллекция витрин данных, а физически целостный объект.</p>
            </div>
            <div class="col-md-5 order-md-1">
                <svg class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500"
                     height="500" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice"
                     focusable="false" role="img" aria-label="Placeholder: 500x500"><title>Placeholder</title>
                    <rect fill="#6bf9c5" width="100%" height="100%"></rect>
                    <text fill="#fff" dy=".3em" x="50%" y="50%">3 Normal Form</text>
                </svg>
            </div>
        </div>

        <hr class="featurette-divider">

        <div class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading" style="margin-top: 0; margin-bottom: 20px">Подход Ральфа Кимболла</h2>
                <p class="lead">Использование пространственной модели организации данных с архитектурой "звезда" (star
                    scheme).
                    Использование двухуровневой архитектуры, которая включает стадию подготовки данных, недоступную для
                    конечных пользователей, и Хранилище данных с архитектурой шины как таковое. В состав последнего
                    входят несколько витрин атомарных данных, несколько витрин агрегированных данных и персональная
                    витрина данных, но оно не содержит одного физически целостного или централизованного Хранилища
                    данных.
                    Хранилище данных не является единым физическим репозиторием (в отличие от подхода Билла Инмона). Это
                    "виртуальное" Хранилище. Это коллекция витрин данных, каждая из которых имеет архитектуру типа
                    "звезда"</p>
            </div>
            <div class="col-md-5">
                <svg class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500"
                     height="500" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice"
                     focusable="false" role="img" aria-label="Placeholder: 500x500"><title>Placeholder</title>
                    <rect fill="#f76c91" width="100%" height="100%"></rect>
                    <text fill="#fff" dy=".3em" x="50%" y="45%">Dimentional</text>
                    <text fill="#fff" dy=".3em" x="50%" y="60%">Model</text>
                </svg>
            </div>
        </div>

        <hr class="featurette-divider">

        <!-- /END THE FEATURETTES -->

    </div><!-- /.container -->


    <!-- FOOTER -->
    <footer class="container">
        <p class="float-right"><a href="#">Back to top</a></p>
        <p>© 2019 BSUIR </p>
    </footer>
</main>

<script>
    $(document).ready(function () {
        $("#btnFetch").click(function () {
            // disable button
            $(this).prop("disabled", true);
            // add spinner to button
            $(this).html(
                `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>Loading...`
            );
        });
    });

    $(document).ready(function () {
        $("#btnFetch2").click(function () {
            // disable button
            $(this).prop("disabled", true);
            // add spinner to button
            $(this).html(
                `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>Loading...`
            );
        });
    });

    $(document).ready(function () {
        $("#btnFetch3").click(function () {
            // disable button
            $(this).prop("disabled", true);
            // add spinner to button
            $(this).html(
                `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>Loading...`
            );
        });
    });
</script>
</body>
</html>