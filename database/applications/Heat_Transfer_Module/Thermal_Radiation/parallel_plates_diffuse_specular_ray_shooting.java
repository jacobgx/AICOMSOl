/*
 * parallel_plates_diffuse_specular_ray_shooting.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:31 by COMSOL 6.3.0.290. */
public class parallel_plates_diffuse_specular_ray_shooting {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Thermal_Radiation");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("rad", "SurfaceToSurfaceRadiation", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/rad", true);

    model.param().set("w", "10[cm]");
    model.param().descr("w", "\u677f\u7684\u5bbd\u5ea6");
    model.param().set("d", "10[cm]");
    model.param().descr("d", "\u677f\u95f4\u8ddd");
    model.param().set("th", "w/20");
    model.param().descr("th", "\u677f\u7684\u539a\u5ea6");
    model.param().set("T0", "300[K]");
    model.param().descr("T0", "\u5ba4\u6e29");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"w", "th"});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-(d+th)/2"});
    model.component("comp1").geom("geom1").feature("r1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("r1").set("selresultshow", "bnd");
    model.component("comp1").geom("geom1").feature("r1").label("\u4e0b\u677f");
    model.component("comp1").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "(d+th)/2"});
    model.component("comp1").geom("geom1").feature("r2").label("\u4e0a\u677f");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").selection().geom("geom1", 1);
    model.component("comp1").variable("var1").selection().all();
    model.component("comp1").variable("var1").label("\u7814\u7a76 1");
    model.component("comp1").variable("var1").set("epsilon_mat", "0.6");
    model.component("comp1").variable("var1").descr("epsilon_mat", "\u8f90\u5c04\u7387");
    model.component("comp1").variable("var1").set("rhod_mat", "0.08");
    model.component("comp1").variable("var1").descr("rhod_mat", "\u6f2b\u53cd\u5c04\u7387");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1")
         .set("table", new String[][]{{"-.49945499542445175000", "-.72041782640953041531"}, 
         {"-.49713063021837630000", "-.71991088499246001211"}, 
         {"-.49295799586795145000", "-.71900347615318219878"}, 
         {"-.48695168400966195000", "-.71770340943363138076"}, 
         {"-.47913392430695410000", "-.71602244650687917286"}, 
         {"-.46953377200148115000", "-.71397635504777592866"}, 
         {"-.45818693115489010000", "-.71158514338917501783"}, 
         {"-.44513560901476370000", "-.70887332026698187993"}, 
         {"-.43042835559114615000", "-.70587014060788902533"}, 
         {"-.41411988191153245000", "-.70260980221763138353"}, 
         {"-.39627085604969060000", "-.69913155603436351148"}, 
         {"-.37694767724268775000", "-.69547969143363354903"}, 
         {"-.35622222878851835000", "-.69170335941042814263"}, 
         {"-.33417161058768500000", "-.68785620107079841485"}, 
         {"-.31087785230036165000", "-.68399575711894696970"}, 
         {"-.28642760817565195000", "-.68018264586555002201"}, 
         {"-.26091183468309290000", "-.67647951212565348695"}, 
         {"-.23442545214302050000", "-.67294976610059335070"}, 
         {"-.20706699161315195000", "-.66965614839425370876"}, 
         {"-.17893822834420475000", "-.66665917292461353676"}, 
         {"-.15014380316766595000", "-.66401551195067755216"}, 
         {"-.12079083322389935000", "-.66177639540324401875"}, 
         {"-.09098851347853875000", "-.65998609943959078245"}, 
         {"-.06084771050944440000", "-.65868059657406921520"}, 
         {"-.03048055007528935000", "-.65788643241758727058"}, 
         {"0", "-.65761988294637615913"}, 
         {".03048055007528935000", "-.65788643241758727068"}, 
         {".06084771050944440000", "-.65868059657406921538"}, 
         {".09098851347853875000", "-.65998609943959078255"}, 
         {".12079083322389935000", "-.66177639540324401903"}, 
         {".15014380316766595000", "-.66401551195067755193"}, 
         {".17893822834420475000", "-.66665917292461353693"}, 
         {".20706699161315195000", "-.66965614839425370868"}, 
         {".23442545214302050000", "-.67294976610059335081"}, 
         {".26091183468309290000", "-.67647951212565348678"}, 
         {".28642760817565195000", "-.68018264586555002215"}, 
         {".31087785230036165000", "-.68399575711894696948"}, 
         {".33417161058768500000", "-.68785620107079841490"}, 
         {".35622222878851835000", "-.69170335941042814270"}, 
         {".37694767724268775000", "-.69547969143363354901"}, 
         {".39627085604969060000", "-.69913155603436351146"}, 
         {".41411988191153245000", "-.70260980221763138351"}, 
         {".43042835559114615000", "-.70587014060788902550"}, 
         {".44513560901476370000", "-.70887332026698188006"}, 
         {".45818693115489010000", "-.71158514338917501795"}, 
         {".46953377200148115000", "-.71397635504777592845"}, 
         {".47913392430695410000", "-.71602244650687917298"}, 
         {".48695168400966195000", "-.71770340943363138086"}, 
         {".49295799586795145000", "-.71900347615318219870"}, 
         {".49713063021837630000", "-.71991088499246001206"}, 
         {".49945499542445175000", "-.72041782640953041538"}});
    model.component("comp1").func("int1").setIndex("argunit", 1, 0);
    model.component("comp1").func("int1").setIndex("fununit", 1, 0);
    model.component("comp1").func("int1").set("funcname", "reference_solution");

    model.component("comp1").physics("rad").prop("RadiationSettings").set("radiationMethod", "rayshooting");
    model.component("comp1").physics("rad").prop("RadiationSettings").set("radiationResolutionRayShooting", 256);
    model.component("comp1").physics("rad").prop("RadiationSettings").set("highOrderMeshElements", true);
    model.component("comp1").physics("rad").create("osurf1", "OpaqueSurfaceSpecularDiffuse", 1);
    model.component("comp1").physics("rad").feature("osurf1").selection().all();
    model.component("comp1").physics("rad").feature("osurf1").set("radDirectionType", "RadiationDirectionPlus");
    model.component("comp1").physics("rad").feature("osurf1").set("minput_temperature", "T0");
    model.component("comp1").physics("rad").feature("osurf1").set("Tamb", 0);
    model.component("comp1").physics("rad").feature("init1").set("Tinit", "T0");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u677f\uff0c\u8fb9\u754c");
    model.component("comp1").material("mat1").propertyGroup("def").set("emissivity", new String[]{"epsilon_mat"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("surfacediffusereflectivity", new String[]{"rhod_mat"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u8868\u9762\u8f90\u5c04\u5ea6 (rad)");
    model.result("pg1").feature().create("line1", "Line");
    model.result("pg1").feature("line1").label("\u4e0a\u4fa7\u8f90\u5c04\u5ea6");
    model.result("pg1").feature("line1").set("showsolutionparams", "on");
    model.result("pg1").feature("line1").set("expr", "rad.Ju");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("smooth", "internal");
    model.result("pg1").feature("line1").set("inheritdeformscale", false);
    model.result("pg1").feature("line1").set("showsolutionparams", "on");
    model.result("pg1").feature("line1").set("data", "parent");
    model.result("pg1").feature("line1").feature().create("def1", "Deform");
    model.result("pg1").feature("line1").feature("def1")
         .set("expr", new String[]{"nx/sqrt(tremetric)", "ny/sqrt(tremetric)"});
    model.result("pg1").feature("line1").feature("def1").set("scale", "0.1");
    model.result("pg1").feature().create("line2", "Line");
    model.result("pg1").feature("line2").label("\u4e0b\u4fa7\u8f90\u5c04\u5ea6");
    model.result("pg1").feature("line2").set("showsolutionparams", "on");
    model.result("pg1").feature("line2").set("expr", "rad.Jd");
    model.result("pg1").feature("line2").set("linetype", "tube");
    model.result("pg1").feature("line2").set("smooth", "internal");
    model.result("pg1").feature("line2").set("showsolutionparams", "on");
    model.result("pg1").feature("line2").set("data", "parent");
    model.result("pg1").feature("line2").set("inheritplot", "line1");
    model.result("pg1").feature("line2").feature().create("def1", "Deform");
    model.result("pg1").feature("line2").feature("def1")
         .set("expr", new String[]{"-nx/sqrt(tremetric)", "-ny/sqrt(tremetric)"});
    model.result("pg1").run();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").importData("parallel_plates_diffuse_specular_data.txt");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u9a8c\u8bc1");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u65e0\u91cf\u7eb2\u8f90\u5c04\u70ed\u901a\u91cf");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "x/w");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "q/(\\varepsilon Eb)");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").label("\u7406\u8bba");
    model.result("pg2").feature("lngr1").selection().set(5);
    model.result("pg2").feature("lngr1").set("expr", "reference_solution(x/w)");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x/w");
    model.result("pg2").feature("lngr1").set("linestyle", "none");
    model.result("pg2").feature("lngr1").set("linemarker", "circle");
    model.result("pg2").feature("lngr1").set("linecolor", "fromtheme");
    model.result("pg2").feature("lngr1").set("resolution", "norefine");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("autosolution", false);
    model.result("pg2").feature("lngr1").set("autoplotlabel", true);
    model.result("pg2").run();
    model.result("pg2").create("lngr2", "LineGraph");
    model.result("pg2").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr2").set("linewidth", "preference");
    model.result("pg2").feature("lngr2").label("\u4eff\u771f");
    model.result("pg2").feature("lngr2").selection().set(5);
    model.result("pg2").feature("lngr2").set("expr", "rad.rflux/(rad.epsilon*rad.ebu)");
    model.result("pg2").feature("lngr2").set("xdata", "expr");
    model.result("pg2").feature("lngr2").set("xdataexpr", "x/w");
    model.result("pg2").feature("lngr2").set("linewidth", 3);
    model.result("pg2").feature("lngr2").set("resolution", "norefine");
    model.result("pg2").feature("lngr2").set("legend", true);
    model.result("pg2").feature("lngr2").set("autosolution", false);
    model.result("pg2").feature("lngr2").set("autoplotlabel", true);
    model.result("pg2").run();

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

    model.component("comp1").multiphysics().create("htrad1", "HeatTransferWithSurfaceToSurfaceRadiation", 1);

    model.study("std1").feature("stat").setSolveFor("/multiphysics/htrad1", true);

    model.component("comp1").multiphysics("htrad1").selection().all();
    model.component("comp1").multiphysics("htrad1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("htrad1").set("Rad_physics", "rad");

    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u77f3\u82f1");
    model.component("comp1").material("mat2").selection().set(1);
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.1[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"2200[kg/m^3]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("heatcapacity", new String[]{"480[J/(kg*K)]"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").selection().set(2);
    model.component("comp1").material("mat3").label("\u94dc");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"8700[kg/m^3]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("heatcapacity", new String[]{"385[J/(kg*K)]"});

    model.component("comp1").variable().duplicate("var2", "var1");
    model.component("comp1").variable("var2").selection().named("geom1_r2_bnd");
    model.component("comp1").variable("var2").label("\u7814\u7a76 2\uff0c\u4e0a\u677f");
    model.component("comp1").variable("var2")
         .descr("epsilon_mat", "\u955c\u9762\u53cd\u5c04\u7387\uff0c\u4e0a\u677f");
    model.component("comp1").variable().duplicate("var3", "var2");
    model.component("comp1").variable("var3").selection().named("geom1_r1_bnd");
    model.component("comp1").variable("var3").label("\u7814\u7a76 2\uff0c\u4e0b\u677f");
    model.component("comp1").variable("var3").set("epsilon_mat", "0.9");
    model.component("comp1").variable("var3").descr("epsilon_mat", "\u6f2b\u53cd\u5c04\u7387\uff0c\u4e0b\u677f");
    model.component("comp1").variable("var3").set("rhod_mat", "0.09");

    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("Tref", "T0");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().all();
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "10[W/(m^2*K)]");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "T0");
    model.component("comp1").physics("ht").create("dbp1", "DepositedBeamPower", 1);
    model.component("comp1").physics("ht").feature("dbp1").selection().set(6);
    model.component("comp1").physics("ht").feature("dbp1").set("e", new int[]{0, -1, 0});
    model.component("comp1").physics("ht").feature("dbp1").set("P0", "4000[W]");
    model.component("comp1").physics("ht").feature("dbp1").set("O", new String[]{"0.025[m]", "d+w", "0"});
    model.component("comp1").physics("ht").feature("dbp1").set("sigma", "0.01[m]");

    model.study("std1").label("\u7814\u7a76 1\uff0c\u4e0d\u542b\u4f20\u70ed");
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledvariables", new String[]{"var2", "var3"});
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/htrad1", false);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"ht"});
    model.study("std1").feature("stat").set("disabledcoupling", new String[]{"htrad1"});
    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/rad", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/htrad1", true);
    model.study("std2").label("\u7814\u7a76 2\uff0c\u542b\u4f20\u70ed");
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledvariables", new String[]{"var1"});
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u8868\u9762\u8f90\u5c04\u5ea6 (rad) 1");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").feature().create("line1", "Line");
    model.result("pg3").feature("line1").label("\u4e0a\u4fa7\u8f90\u5c04\u5ea6");
    model.result("pg3").feature("line1").set("showsolutionparams", "on");
    model.result("pg3").feature("line1").set("expr", "rad.Ju");
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("smooth", "internal");
    model.result("pg3").feature("line1").set("inheritdeformscale", false);
    model.result("pg3").feature("line1").set("showsolutionparams", "on");
    model.result("pg3").feature("line1").set("data", "parent");
    model.result("pg3").feature("line1").feature().create("def1", "Deform");
    model.result("pg3").feature("line1").feature("def1")
         .set("expr", new String[]{"nx/sqrt(tremetric)", "ny/sqrt(tremetric)"});
    model.result("pg3").feature("line1").feature("def1").set("scale", "0.1");
    model.result("pg3").feature().create("line2", "Line");
    model.result("pg3").feature("line2").label("\u4e0b\u4fa7\u8f90\u5c04\u5ea6");
    model.result("pg3").feature("line2").set("showsolutionparams", "on");
    model.result("pg3").feature("line2").set("expr", "rad.Jd");
    model.result("pg3").feature("line2").set("linetype", "tube");
    model.result("pg3").feature("line2").set("smooth", "internal");
    model.result("pg3").feature("line2").set("showsolutionparams", "on");
    model.result("pg3").feature("line2").set("data", "parent");
    model.result("pg3").feature("line2").set("inheritplot", "line1");
    model.result("pg3").feature("line2").feature().create("def1", "Deform");
    model.result("pg3").feature("line2").feature("def1")
         .set("expr", new String[]{"-nx/sqrt(tremetric)", "-ny/sqrt(tremetric)"});
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u6e29\u5ea6 (ht)");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg3").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u6e29\u5ea6");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").label("\u4e0a\u677f");
    model.result("pg5").feature("lngr1").selection().set(5);
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").set("linewidth", 3);
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("autosolution", false);
    model.result("pg5").feature("lngr1").set("autoplotlabel", true);
    model.result("pg5").feature().duplicate("lngr2", "lngr1");
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").label("\u4e0b\u677f");
    model.result("pg5").feature("lngr2").selection().set(3);
    model.result("pg5").feature("lngr2").set("titletype", "none");
    model.result("pg5").feature("lngr2").set("linestyle", "dashed");
    model.result("pg5").run();
    model.result("pg5").set("legendpos", "middleright");
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u8f90\u5c04\u70ed\u901a\u91cf");
    model.result("pg6").run();
    model.result("pg6").feature("lngr1").set("expr", "rad.rflux");
    model.result("pg6").run();
    model.result("pg6").feature("lngr2").set("expr", "rad.rflux");
    model.result("pg6").run();
    model.result().numerical().create("int1", "IntLine");
    model.result().numerical("int1").set("intsurface", true);
    model.result().numerical("int1").label("\u5e73\u5747\u8f90\u5c04\u70ed\u901a\u91cf");
    model.result().numerical("int1").selection().set(3, 5);
    model.result().numerical("int1").setIndex("expr", "rad.rflux/(w*rad.epsilon*rad.ebu)", 0);
    model.result().numerical("int1").setIndex("unit", "m", 0);
    model.result().numerical("int1").setIndex("descr", "\u8f90\u5c04\u70ed\u901a\u91cf", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5e73\u5747\u8f90\u5c04\u70ed\u901a\u91cf");
    model.result().numerical("int1").set("table", "tbl2");
    model.result().numerical("int1").setResult();
    model.result().numerical().duplicate("int2", "int1");
    model.result().numerical("int2").label("\u8f90\u5c04\u70ed\u901a\u91cf\u7684\u76f8\u5bf9\u8bef\u5dee");
    model.result().numerical("int2")
         .setIndex("expr", "((rad.rflux/(rad.epsilon*rad.ebu))/reference_solution(x)-1)^2/w", 0);
    model.result().numerical("int2").setIndex("descr", "\u76f8\u5bf9\u8bef\u5dee", 0);
    model.result().numerical("int2").set("table", "tbl2");
    model.result().numerical("int2").appendResult();
    model.result().numerical().create("int3", "IntSurface");
    model.result().numerical("int3").set("intvolume", true);
    model.result().numerical("int3").label("\u5e73\u5747\u6e29\u5ea6\uff0c\u4e0b\u677f");
    model.result().numerical("int3").set("data", "dset2");
    model.result().numerical("int3").selection().set(1);
    model.result().numerical("int3").setIndex("expr", "T/(w*th)", 0);
    model.result().numerical("int3").setIndex("unit", "m^2*K", 0);
    model.result().numerical("int3").setIndex("descr", "\u6e29\u5ea6", 0);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u5e73\u5747\u6e29\u5ea6\uff0c\u4e0b\u677f");
    model.result().numerical("int3").set("table", "tbl3");
    model.result().numerical("int3").setResult();
    model.result().numerical().duplicate("int4", "int3");
    model.result().numerical("int4").label("\u5e73\u5747\u6e29\u5ea6\uff0c\u4e0a\u677f");
    model.result().numerical("int4").selection().set(2);
    model.result().numerical("int4").set("table", "tbl3");
    model.result().numerical("int4").appendResult();
    model.result().dataset().duplicate("dset3", "dset1");
    model.result().dataset("dset3")
         .label("\u7814\u7a76 1\uff0c\u4e0d\u542b\u4f20\u70ed/\u89e3 1\uff0c\u5185\u8868\u9762");
    model.result().dataset("dset3").selection().geom("geom1", 1);
    model.result().dataset("dset3").selection().geom("geom1", 1);
    model.result().dataset("dset3").selection().set(3, 5);
    model.result().dataset().create("extr1", "Extrude2D");
    model.result().dataset("extr1").set("data", "dset3");
    model.result().dataset("extr1").set("zmax", "2");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u4e09\u7ef4\u4e2d\u7684\u8868\u9762\u8f90\u5c04\u5ea6");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "rad.J");
    model.result("pg7").feature("surf1").label("\u8f90\u5c04\u5ea6");
    model.result("pg7").feature("surf1").set("colortable", "HeatCamera");
    model.result("pg7").feature("surf1").set("rangecoloractive", true);
    model.result("pg7").feature("surf1").set("rangecolormin", 285);
    model.result("pg7").feature("surf1").set("rangecolormax", 288);
    model.result("pg7").run();

    model.view("view2").set("showgrid", false);
    model.view("view2").camera().set("zoomanglefull", 121);
    model.view("view2").camera().setIndex("position", 0.1, 0);
    model.view("view2").camera().setIndex("position", 0, 1);
    model.view("view2").camera().set("position", new double[]{0.1, 0, 0.1});
    model.view("view2").camera().set("target", new double[]{0, 0, 0.1});
    model.view("view2").camera().setIndex("up", 0, 0);
    model.view("view2").camera().setIndex("up", 0, 1);
    model.view("view2").camera().set("up", new int[]{0, 0, 1});
    model.view("view2").camera().set("rotationpoint", new double[]{0, 0, 0.1});
    model.view("view2").camera().setIndex("viewoffset", -0.02, 0);
    model.view("view2").camera().set("viewoffset", new double[]{-0.02, -0.02});

    model.result("pg7").run();

    model.view("view2").set("scenelight", false);

    model
         .title("\u5177\u6709\u6f2b\u53cd\u5c04\u548c\u955c\u9762\u53cd\u5c04\u7684\u8868\u9762\u5bf9\u8868\u9762\u8f90\u5c04");

    model
         .description("\u672c\u6559\u7a0b\u4ecb\u7ecd\u5982\u4f55\u4f7f\u7528\u201c\u8868\u9762\u5bf9\u8868\u9762\u8f90\u5c04\u201d\u63a5\u53e3\u6a21\u62df\u6f2b\u53cd\u5c04\u53d1\u5c04\u5668\u4e0e\u6f2b\u53cd\u5c04\u955c\u9762\u53cd\u5c04\u5668\u4e4b\u95f4\u7684\u8f90\u5c04\u4f20\u70ed\u3002\u6b64\u6a21\u578b\u5206\u4e3a\u4e24\u90e8\u5206\u3002\u7b2c\u4e00\u90e8\u5206\u4e3a\u6839\u636e\u5c04\u7ebf\u53d1\u5c04\u7b97\u6cd5\u8ba1\u7b97\u7684\u8f90\u5c04\u70ed\u901a\u91cf\u7684\u9a8c\u8bc1\u6d4b\u8bd5\uff0c\u5176\u4e2d\u5c06\u7ed3\u679c\u4e0e\u4e24\u4e2a\u5e73\u884c\u677f\u5728\u6052\u6e29\u4e0b\u7684\u89e3\u6790\u89e3\u8fdb\u884c\u6bd4\u8f83\u3002\u7b2c\u4e8c\u90e8\u5206\u4ecb\u7ecd\u4e0e\u56fa\u4f53\u4f20\u70ed\u7684\u8026\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("parallel_plates_diffuse_specular_ray_shooting.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
