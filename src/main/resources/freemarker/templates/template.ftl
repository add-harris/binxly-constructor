<#-- freemarker -->
<html>
<head>
    <title>Welcome!</title>
</head>
<body>
<h1>Example ${text}!</h1>
<#list ["1", "2", "3", "4"] as user>
    - ${user}
</#list>
</body>
</html>