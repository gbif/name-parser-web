[#ftl]
[#include "/WEB-INF/pages/inc/header.ftl"/]
 <title>Name Parser Result</title>
 <style>
 .ext{
 	display:none;
 }
 </style>
 <script type="text/javascript">
var hidden=true;
$(document).ready(function() {
    $("#toggleExtended").click(function(e) {
  	    e.preventDefault();
  	    if (hidden){
	  	    hidden=false;
	  	    $(".ext").show();
	  	    $("#toggleExtended").text("Hide");
  	    }else{
	  	    hidden=true;
	  	    $(".ext").hide();
	  	    $("#toggleExtended").text("Show");
  	    }
    });
});
</script>

[#assign menu = "parser"]
[#include "/WEB-INF/pages/inc/menu.ftl"/]

<h1>Parsed Names</h1>
<p>${numParsed} name parsed. ${numScientific} scientific, ${numHybrid} hybrid formulas and ${numDoubtful} doubtful names.
See legend for <a href="#legend">parsing types</a>.</p>

<p><a id="toggleExtended" href="#">Show</a> extended parsing</p>

  <table class="simple" width="100%">
    <tr>
    	<th>Original</th>
      <th>Genus</th>
      <th>Infrageneric</th>
      <th>Specific</th>
      <th>Rank</th>
      <th>Notho</th>
      <th>InfraSpecific</th>
      <th>Authorship</th>
      <th>Year</th>
      <th>(Authorship)</th>
      <th>(Year)</th>

			<th class="ext">Type</th>
			<th class="ext">Standardised</th>
    	<th class="ext">Canonical</th>
    	<th class="ext">Sensu</th>
    	<th class="ext">NomStatus</th>
			<th class="ext">Remarks</th>
    	<th class="ext">GBIF Nub</th>
    </tr>

    [#list names as n]
  	<tr>
		<td>${n.scientificName!}</td>
		<td>${n.genusOrAbove!}</td>
		<td>${n.infraGeneric!}</td>
		<td>${n.specificEpithet!}</td>
		<td>${n.rank!}</td>
    <td>${n.notho!}</td>
    <td>${n.infraSpecificEpithet!}</td>
		<td>${n.authorship!}</td>
		<td>${n.year!}</td>
		<td>${n.bracketAuthorship!}</td>
		<td>${n.bracketYear!}</td>

    <td class="ext">${n.type!}</td>
    <td class="ext">${n.canonicalNameComplete()!}</td>
		<td class="ext">${n.canonicalName()!}</td>
		<td class="ext">${n.sensu!}</td>
		<td class="ext">${n.nomStatus!}</td>
		<td class="ext">${n.remarks!}</td>
 		<td class="ext"><a target="_blank" href="${portalUrl}/species/search?dataset_key=d7dddbf4-2cf0-4f39-9b2a-bb099caae36c&q=${n.canonicalName()?replace(' ','+')}">search</a></td>
  	</tr>
    [/#list]
  </table>
<br/><br/>

<a name="legend"></a>
<h2>Parser Result Types</h2>
<dl>
  <dt>SCIENTIFIC</dt><dd>A scientific Latin name that might contain authorship but is not any of the other name types below (virus, hybrid, cultivar, etc).</dd>
  <dt>VIRUS</dt><dd>A virus name.</dd>
  <dt>HYBRID</dt><dd>A hybrid <b>formula</b> (not a hybrid name).</dd>
  <dt>INFORMAL</dt><dd>A scientific name with some informal addition like "cf." or indetermined like Abies spec.</dd>
  <dt>CULTIVAR</dt><dd>A cultivated plant name.</dd>
  <dt>CANDIDATUS</dt><dd>A component of the taxonomic name for a bacterium that cannot be maintained in a Bacteriology Culture Collection.</dd>
  <dt>DOUBTFUL</dt><dd>Doubtful whether this is a scientific name at all.</dd>
  <dt>PLACEHOLDER</dt><dd>A placeholder name like "incertae sedis" or "unknown genus".</dd>
  <dt>NO_NAME</dt><dd>Surely not a scientific name of any kind.</dd>
</dl>

[#include "/WEB-INF/pages/inc/footer.ftl"/]
