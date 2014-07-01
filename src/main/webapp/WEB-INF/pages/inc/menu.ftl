[#ftl]
 	</head>
 	<body>
		<div id="wrapper">

		    <div id="topmenu">
		        <ul>
		        	[#list ["parser","api"] as m]
		        	<li[#if menu==m] class="current"[/#if]><a href="${baseURL}/${m}.do">${m}</a></li>
		        	[/#list]
		        </ul>
		    </div>

			<div id="logo">
				<a href="${baseURL}/parser.do"><img src="${baseURL}/styles/logo.jpg"></a>					
			</div>

			<div id="content">			

			[@s.actionmessage/]
			[@s.actionerror/]