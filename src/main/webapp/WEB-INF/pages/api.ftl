[#ftl]
[#include "/WEB-INF/pages/inc/header.ftl"/]
 <title>Name Parser API</title>
[#assign menu = "api"]
[#include "/WEB-INF/pages/inc/menu.ftl"/]

<div>
	<h1>Name Parser API</h1>
	<h4>${parserUrl}</h4>
    <div class="boxed">
		<p>
		To try out the name parsing webservice, please take a look at our simple <a href="${baseURL}/parser.do">name parser client</a>.
		<br/>
		Please see our <a href="http://www.gbif.org/developer/species#parser">portal API documentation</a> for parameters supported
      by the name parser.
		</p>

		<h4>JSON Response</h4>
		Example of a parsed name json object:
		<pre>
 {
		"original": "Symphoricarpos albus (L.) S.F.Blake cv. 'Turesson'",
		"type": "cultivar",
		"authorsParsed": true,
		"genus": "Symphoricarpos",
		"infraGeneric": null,
		"specific": "albus",
		"rank": "cv.",
		"infraSpecific": null,
		"authorship": "S.F.Blake",
		"year": null,
		"bracketAuthorship": "L.",
		"bracketYear": null,
		"nothoRank": null,
		"cultivar": "Turesson",
		"sensu": null,
		"nomStatus": null,
		"remarks": null,
		"standard": "Symphoricarpos albus cv. (L.) S.F.Blake 'Turesson'",
		"canonical": "Symphoricarpos albus cv. 'Turesson'"
 }
		</pre>
		<dl>
			<dt>original</dt>
			<dd>
			    The original name as it was submitted.
			</dd>

			<dt>type</dt>
			<dd>
				One of the following:
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
			</dd>

			<dt>authorsParsed</dt>
			<dd>
				A boolean flag to indicate whether the full parser was applied and author names have been parsed.
				For some names only the canonical name parts can be parsed and this flag will be false.
			</dd>


			<dt>genus</dt>
			<dd>
				The genus of the name.
			</dd>

			<dt>infraGeneric</dt>
			<dd>
				Any infrageneric part above species level of the name, mostly the subgenus or section.
			</dd>

			<dt>specific</dt>
			<dd>
				The specific epithet.
			</dd>

			<dt>rank</dt>
			<dd>
				The rank marker used in an infraspecific name.
			</dd>

			<dt>infraSpecific</dt>
			<dd>
				The infraspecific epithet of the lowest ranked name part, e.g variety or subspecies.
			</dd>

			<dt>authorship</dt>
			<dd>
				The authorship with no brackets. Usually the combination authorship.
			</dd>

			<dt>year</dt>
			<dd>
				The year without bracket. Usually the year of publication of the combination.
			</dd>

			<dt>bracketAuthorship</dt>
			<dd>
				The authorship in brackets. Usually the authorship of the original name, e.g the basionym.
			</dd>

			<dt>bracketYear</dt>
			<dd>
				The year in brackets. Usually the year of publication of the original name, e.g the basionym.
			</dd>

			<dt>nothoRank</dt>
			<dd>
				A property indicating a named hybrid and giving the name part that the hybrid marker applies to.
				One of the following:
				<span class="param">Generic</span> &nbsp;
				<span class="param">Infrageneric</span> &nbsp;
				<span class="param">Specific</span> &nbsp;
				<span class="param">Infraspecific</span> &nbsp;
			</dd>

			<dt>cultivar</dt>
			<dd>
				The cultivar name cited in single quotes, e.g Turesson as in Symphoricarpos albus 'Turesson'
			</dd>

			<dt>sensu</dt>
			<dd>
			    The dwc:taxonAccordingTo part of a taxon concept. E.g. sec. Berendsohn 2001 or sensu latu.
			</dd>

			<dt>nomStatus</dt>
			<dd>
			    A nomenclatural status remark such as nom.illeg., usually starting with nom. or nomen.
			</dd>

			<dt>remarks</dt>
			<dd>
			    Other, mostly identification related remarks such as cf.
			</dd>

			<dt>standard</dt>
			<dd>
			    The standard representation of the name. Tries to be compliant with the best practices of the nomenclatural codes
			    and reduces names consisting of more than one infraspecific epithet to the lowest ranked one.
			</dd>

			<dt>canonical</dt>
			<dd>
			    The canonical form of the name. This form has no authorship or more than 3 name parts.
			</dd>

		</dl>
	</div>


	<h2>Examples</h2>
    <div class="boxed">
		<ul>
			<li> <a href="${parserUrl}?name=Abies%20alba&name=Abies%20alba%20Mill.&name=Poa%20pratensis%20L.%20subsp.%20sergievskajae%20(Probat.)%20Tzvelev">parse names in get parameters</a> </li>
		</ul>
    </div>
</div>

[#include "/WEB-INF/pages/inc/footer.ftl"/]
