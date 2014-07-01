[#ftl]
[#include "/WEB-INF/pages/inc/header.ftl"/]
 <title>GBIF Name Parser</title>
 <style TYPE="text/css">
input#parsed {
	float: right;
}
th {
	font-weight: normal;
	font-style: normal;
	font-size: 90%;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
    $("#loadNames").click(function(e) {
  	    e.preventDefault();
  	    $("#names").load("${baseURL}/testdata/names.txt");
    });
});
</script>

[#assign menu = "parser"]
[#include "/WEB-INF/pages/inc/menu.ftl"/]


<h1>Name Parser</h1>

<p>This is a simple html form to make use of the GBIF name parser.
The parser is written in java and based on regular expressions to disect name strings into its components.
It does only keep name parts required to reconstruct a full 3-parted name with an optional subgenus,
but ignores additional infraspecific parts such as the subspecies given for varieties.
Please see our <a href="${baseURL}/api.do">API documentation</a> for details.
</p>
<p>You can copy paste a list of names, one per row, or upload a text file with a name per line.
Uploaded files have to be encoded as utf8!
</p>

<form action='parser.do' accept-charset="UTF-8" enctype="multipart/form-data" method="post">
	<table>
		<tr>
			<th width="150">Names to parse: <br><br><i>One per line or delimited by the pipe symbol "|"</i></th>
			<td><textarea rows='10' cols='110' id='names' name='text'>Abies alba Mill.
Ge Nicéville 1895
Stagonospora polyspora M.T. Lucas & Sousa da Câmara 1934
</textarea></td>
		</tr>
		<tr>
		    <th>Upload File:</th>
		    <td><input type="file" name="file" size="50" accept="text"></td></tr>
		</tr>
		<tr>
			<td></td>
			<td>
				<input id="loadNames" type=submit value='Load Test Names'>
				<input id="parsed" type=submit value='Parse'>
			</td>
		</tr>
	</table>
</form>


[#include "/WEB-INF/pages/inc/footer.ftl"/]
