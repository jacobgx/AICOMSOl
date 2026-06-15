/*
 * concrete_beam_cdpm.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:12 by COMSOL 6.3.0.290. */
public class concrete_beam_cdpm {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Geomechanics_Module\\Concrete");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("truss", "Truss", "geom1");
    model.component("comp1").physics("truss").field("displacement").field("u");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/truss", true);

    model.param().set("disp", "0[mm]");
    model.param().descr("disp", "Applied displacement");
    model.param().set("meshSize", "20[mm]");
    model.param().descr("meshSize", "Mesh size");
    model.param().create("par2");
    model.param("par2").label("\u51e0\u4f55\u53c2\u6570");
    model.param("par2").set("length1", "250[mm]");
    model.param("par2").descr("length1", "Auxiliary length");
    model.param("par2").set("length2", "1750[mm]");
    model.param("par2").descr("length2", "Auxiliary length");
    model.param("par2").set("length", "2250[mm]");
    model.param("par2").descr("length", "Length of the beam");
    model.param("par2").set("height", "300[mm]");
    model.param("par2").descr("height", "Height of the beam");
    model.param("par2").set("width", "200[mm]");
    model.param("par2").descr("width", "Width of the beam");
    model.param("par2").set("bcWidth", "150[mm]");
    model.param("par2").descr("bcWidth", "Boundary condition extension");
    model.param("par2").set("rebarPosition", "50[mm]");
    model.param("par2").descr("rebarPosition", "Rebar position");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"length", "height"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "length1-bcWidth/2", 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "bcWidth", 1);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "length2-bcWidth", 2);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "bcWidth", 3);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "rebarPosition", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "length", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "rebarPosition", 1, 1);
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin", 4, 7, 10, 13);
    model.component("comp1").geom("geom1").feature("mce1").set("includevtx", false);
    model.component("comp1").geom("geom1").run("mce1");
    model.component("comp1").geom("geom1").create("mcv1", "MeshControlVertices");
    model.component("comp1").geom("geom1").feature("mcv1").selection("input").set("mce1", 4, 6, 7, 9);
    model.component("comp1").geom("geom1").run("mcv1");

    model.component("comp1").physics("solid").prop("Type2D").set("Type2D", "PlaneStress");
    model.component("comp1").physics("solid").prop("d").set("d", "width");
    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", 1);
    model.component("comp1").physics("solid").feature("lemm1").set("reducedIntegration", true);
    model.component("comp1").physics("solid").feature("lemm1").create("cm1", "Concrete", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("cm1")
         .set("materialModel", "coupledDamagePlasticity");
    model.component("comp1").physics("solid").feature("lemm1").feature("cm1")
         .set("tensionSofteningCdpm", "cornelissen");
    model.component("comp1").physics("solid").feature("lemm1").feature("cm1").set("epsilonfc_cdpm", "5e-3");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 1);
    model.component("comp1").physics("solid").feature("sym1").selection().set(8);
    model.component("comp1").physics("solid").create("rig1", "RigidConnector", 1);
    model.component("comp1").physics("solid").feature("rig1").selection().set(4);
    model.component("comp1").physics("solid").feature("rig1").setIndex("Direction", true, 1);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(8);

    model.component("comp1").physics("solid").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("solid").feature("ge1").setIndex("name", "load", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").setIndex("equation", "intop1(v)+disp", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").set("DependentVariableQuantity", "force");
    model.component("comp1").physics("solid").feature("ge1").set("SourceTermQuantity", "displacement");
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(6);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bndl1").set("force", new String[]{"0", "-load/2", "0"});
    model.component("comp1").physics("solid").create("bndl2", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl2").selection().set(3, 6, 7);
    model.component("comp1").physics("solid").feature("bndl2").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bndl2").set("force", new String[]{"0", "-load/2", "0"});
    model.component("comp1").physics("truss").selection().set(9);
    model.component("comp1").physics("truss").feature("emm1").create("plsty1", "Plasticity", 1);
    model.component("comp1").physics("truss").feature("csd1").set("area", "height*width*0.8[%]");
    model.component("comp1").physics("truss").create("sym1", "SymmetrySolid20", 0);
    model.component("comp1").physics("truss").feature("sym1").selection().set(10);

    model.component("comp1").multiphysics().create("ere1", "EmbeddedReinforcement", -1);
    model.component("comp1").multiphysics("ere1").selection("dstEdgSel").set(9);
    model.component("comp1").multiphysics("ere1").set("connectionType", "kPerArea");
    model.component("comp1").multiphysics("ere1").set("kaPerArea", "((1e-1*ere1.Eequ)*ere1.perimeter)/(h^2)");
    model.component("comp1").multiphysics("ere1").set("ktPerArea", "((1e1*ere1.Eequ)*ere1.perimeter)/(h^2)");
    model.component("comp1").multiphysics("ere1").set("bondSlip", "friction");
    model.component("comp1").multiphysics("ere1").set("cohesion", "1[MPa]");

    model.component("comp1").func().create("pw1", "Piecewise");
    model.component("comp1").func("pw1").set("argunit", "mm");
    model.component("comp1").func("pw1").set("fununit", "1");
    model.component("comp1").func("pw1").setIndex("pieces", 0, 0, 0);
    model.component("comp1").func("pw1").setIndex("pieces", 0.3, 0, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "max((x/0.3),eps)^0.4", 0, 2);
    model.component("comp1").func("pw1").setIndex("pieces", 0.3, 1, 0);
    model.component("comp1").func("pw1").setIndex("pieces", 2, 1, 1);
    model.component("comp1").func("pw1").setIndex("pieces", 1, 1, 2);
    model.component("comp1").func("pw1").setIndex("pieces", 2, 2, 0);
    model.component("comp1").func("pw1").setIndex("pieces", 6, 2, 1);
    model.component("comp1").func("pw1").setIndex("pieces", "1-(1-0.15)*(x-2)/(6-2)", 2, 2);

    model.component("comp1").multiphysics("ere1").set("hardening", "userDefined");
    model.component("comp1").multiphysics("ere1").set("hardeningFunc", "5[MPa]*pw1(ere1.upe)");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"25[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.2"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"0"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("YieldStressParameters", "YieldStressParameters", "Yield_stress_parameters");
    model.component("comp1").material("mat1").propertyGroup("YieldStressParameters")
         .set("sigmaut", new String[]{"2[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("YieldStressParameters")
         .set("sigmauc", new String[]{"30[MPa]"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("FractureParameters", "FractureParameters", "Fracture_parameters");
    model.component("comp1").material("mat1").propertyGroup("FractureParameters")
         .set("Gft", new String[]{"220[J/m^2]"});

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("expr", "if(x>=length-1.05*meshSize/2,1/2,1)");
    model.component("comp1").func("an1").set("fununit", "1");
    model.component("comp1").func("an1").setIndex("argunit", "m", 0);

    model.component("comp1").material("mat1").propertyGroup("FractureParameters")
         .set("Gft", new String[]{"220[J/m^2]*an1(centroid(X))"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().geom("geom1", 1);
    model.component("comp1").material("mat2").selection().set(9);
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"208[GPa]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"0"});
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic_material_model");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("sigmags", new String[]{"500[MPa]"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").set("Et", new String[]{"1[GPa]"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(9);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "meshSize");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "bcWidth", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "bcWidth", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "disp", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,2,60)", 0);
    model.study("std1").feature("stat").setIndex("punit", "mm", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("p1").set("paramtuning", true);
    model.sol("sol1").feature("s1").feature("p1").set("pinitstep", "1e-3");
    model.sol("sol1").feature("s1").feature("p1").set("pminstep", "1e-8");
    model.sol("sol1").feature("s1").feature("p1").set("pmaxstep", "2e-3");
    model.sol("sol1").feature("s1").feature("p1").set("ponerror", "skip");
    model.sol("sol1").feature("s1").feature("fc1").set("ntolfact", "1.0e-1");

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("expr", "load");
    model.component("comp1").probe("var1").set("unit", "kN");

    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.component("comp1").probe("var1").genResult("none");

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u5e94\u529b (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("resolution", "normal");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", new String[]{"truss.misesGp"});
    model.result("pg3").feature("line1").set("threshold", "manual");
    model.result("pg3").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("line1").set("colortable", "Rainbow");
    model.result("pg3").feature("line1").set("colortabletrans", "none");
    model.result("pg3").feature("line1").set("colorscalemode", "linear");
    model.result("pg3").label("\u5e94\u529b (truss)");
    model.result("pg3").feature("line1").set("colortable", "Rainbow");
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("radiusexpr", "truss.re");
    model.result("pg3").feature("line1").set("resolution", "extrafine");
    model.result("pg3").feature("line1").set("smooth", "internal");
    model.result("pg3").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg3").feature("line1").set("tuberadiusscale", 1);
    model.result("pg3").feature("line1").set("tubeendcaps", false);
    model.result("pg3").feature("line1").create("def", "Deform");
    model.result("pg3").feature("line1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg3").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u635f\u4f24 (solid)");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"solid.dmgGp"});
    model.result("pg4").feature("surf1").set("inheritplot", "none");
    model.result("pg4").feature("surf1").set("resolution", "normal");
    model.result("pg4").feature("surf1").set("colortabletype", "discrete");
    model.result("pg4").feature("surf1").set("bandcount", 11);
    model.result("pg4").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg4").feature("surf1").set("smooth", "none");
    model.result("pg4").feature("surf1").set("descractive", true);
    model.result("pg4").feature("surf1").set("descr", "\u635f\u4f24");
    model.result("pg4").label("\u635f\u4f24 (solid)");
    model.result("pg4").run();

    model.study("std1").feature("stat").set("plot", true);
    model.study("std1").feature("stat").set("plotgroup", "pg4");
    model.study("std1").feature("stat").set("probefreq", "psteps");
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");

    model.sol("sol1").runAll();

    model.result("pg2").run();
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u5782\u76f4\u4f4d\u79fb (mm)");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "solid.sdGpxx");
    model.result("pg2").feature("surf1")
         .set("descr", "\u5e94\u529b\u5f20\u91cf\uff0c\u635f\u4f24\uff0cxx \u5206\u91cf");
    model.result("pg2").feature("surf1").set("unit", "MPa");
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").run();
    model.result("pg4").create("arwl1", "ArrowLine");
    model.result("pg4").feature("arwl1").set("expr", new String[]{"solid.fax", "solid.fay"});
    model.result("pg4").feature("arwl1")
         .set("descr", "\u5355\u4f4d\u53d8\u5f62\u9762\u79ef\u7684\u529b \uff08\u7a7a\u95f4\u5750\u6807\u7cfb\uff09");
    model.result("pg4").feature("arwl1").set("titletype", "none");
    model.result("pg4").feature("arwl1").set("placement", "gausspoints");
    model.result("pg4").feature("arwl1").set("arrowbase", "head");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"displacement", "\u4f4d\u79fb", "m", "m"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "mm", 0, 3);
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1").apply();
    model.result("pg5").run();
    model.result("pg5").label("\u88c2\u7eb9\u5bbd\u5ea6\uff08\u5b9e\u4f53\uff09");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "solid.gpeval(solid.lemm1.cm1.wdmgt)");
    model.result("pg5").feature("surf1").set("descr", "\u88c2\u7eb9\u5bbd\u5ea6");
    model.result("pg5").feature("surf1").set("rangecoloractive", true);
    model.result("pg5").feature("surf1").set("rangecolormax", 1);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u5851\u6027\u5e94\u53d8\uff08\u6841\u67b6\uff09");
    model.result("pg6").setIndex("looplevelinput", "last", 0);
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").selection().set(9);
    model.result("pg6").feature("lngr1").set("expr", "truss.epnGp");
    model.result("pg6").feature("lngr1").set("descr", "\u5851\u6027\u8f74\u5411\u5e94\u53d8");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").set("titletype", "none");
    model.result("pg7").label("\u6ed1\u79fb");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u6ed1\u79fb (mm)");
    model.result("pg7").run();
    model.result("pg7").feature("lngr1").set("expr", "gpeval(2,ere1.un)");
    model.result("pg7").run();
    model.result("pg5").run();

    model.title("\u4f7f\u7528\u635f\u4f24-\u5851\u6027\u8026\u5408\u7684\u6df7\u51dd\u571f\u6881\u5931\u6548");

    model
         .description("\u94a2\u7b4b\u6df7\u51dd\u571f\u7ed3\u6784\u7684\u5931\u6548\u8fc7\u7a0b\u6d89\u53ca\u590d\u6742\u7684\u6750\u6599\u7279\u6027\u4ee5\u53ca\u6750\u6599\u4e4b\u95f4\u7684\u76f8\u4e92\u4f5c\u7528\u3002\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u5305\u542b\u6df7\u51dd\u571f\u7684\u635f\u4f24-\u5851\u6027\u6750\u6599\u8026\u5408\u6a21\u578b\u3001\u94a2\u7b4b\u7684\u91d1\u5c5e\u5851\u6027\uff0c\u4ee5\u53ca\u6df7\u51dd\u571f\u4e0e\u94a2\u7b4b\u4e4b\u95f4\u76f8\u4e92\u4f5c\u7528\u7684\u975e\u7ebf\u6027\u9ecf\u7ed3\u6ed1\u79fb\u5b9a\u5f8b\u7b49\uff0c\u6a21\u62df\u94a2\u7b4b\u6df7\u51dd\u571f\u6881\u5931\u6548\u7684\u5404\u4e2a\u9636\u6bb5\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("concrete_beam_cdpm.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
