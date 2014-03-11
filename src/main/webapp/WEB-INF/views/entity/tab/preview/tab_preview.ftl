<#ftl encoding="UTF-8" strict_syntax=true>
<#import "/metalcon.ftl" as mtl>

<#macro tabPreview name>
  <div class="tabPreview">
	<a href="tghzuj">${name}</a>
	<#nested>
  </div>
</#macro>

<li class="tabPreview">
  <#--
   # Will include the corrrect tabPreview subtemplate.
   # For example, if tabPreviewName is "ABOUT_TAB" this will include "impl/about_tab.ftl"
   #-->
  <#include "impl/" + tab.entityTabType?lower_case + ".ftl">
</li>