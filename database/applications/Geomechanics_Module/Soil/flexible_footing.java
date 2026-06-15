/*
 * flexible_footing.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:14 by COMSOL 6.3.0.290. */
public class flexible_footing {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Geomechanics_Module\\Soil");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("v_prescr", "0[m]");
    model.param().descr("v_prescr", "\u6307\u5b9a\u4f4d\u79fb");
    model.param().set("W", "7.32[m]");
    model.param().descr("W", "\u5bbd\u5ea6");
    model.param().set("H", "3.66[m]");
    model.param().descr("H", "\u9ad8\u5ea6");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W*1.1", "H"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-W*0.1", "0"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "W*0.1", 0);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "W-1.57[m]", 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "H", 1);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");
    model.component("comp1").coordSystem("ie1").selection().set(1);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(7);

    model.component("comp1").physics("solid").feature("lemm1").create("sopl1", "SoilPlasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("sopl1")
         .set("matchToMohrCoulomb", "inscribe");
    model.component("comp1").physics("solid").feature("lemm1").create("sopl2", "SoilPlasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("sopl2")
         .set("failureCriterion", "MohrCoulomb");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(2, 5);
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 1);
    model.component("comp1").physics("solid").feature("sym1").selection().set(8);
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(1);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(7);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "footing_pressure", "0"});
    model.component("comp1").physics("solid").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("solid").feature("ge1").setIndex("name", "footing_pressure", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").setIndex("equation", "intop1(v) - v_prescr", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").set("DependentVariableQuantity", "pressure");
    model.component("comp1").physics("solid").feature("ge1").set("SourceTermQuantity", "displacement");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"207[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup().create("Soil", "Soil", "Soil_material");
    model.component("comp1").material("mat1").propertyGroup("Soil").set("cohesion0", new String[]{"69[kPa]"});
    model.component("comp1").material("mat1").propertyGroup("Soil").set("phis", new String[]{"20[deg]"});

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(2);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76\uff1a\u5fb7\u9c81\u514b-\u666e\u62c9\u683c");
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"solid/lemm1/sopl2"});
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "v_prescr", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "v_prescr", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0, -2e-3, -32e-3)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 17, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 17, 0);
    model.result("pg2").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.epeGp"});
    model.result("pg2").feature("surf1").set("inheritplot", "none");
    model.result("pg2").feature("surf1").set("resolution", "normal");
    model.result("pg2").feature("surf1").set("colortabletype", "discrete");
    model.result("pg2").feature("surf1").set("bandcount", 11);
    model.result("pg2").feature("surf1").set("colortable", "AuroraAustralisDark");
    model.result("pg2").feature("surf1").set("descractive", true);
    model.result("pg2").feature("surf1").set("descr", "\u7b49\u6548\u5851\u6027\u5e94\u53d8");
    model.result("pg2").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").label("\u7814\u7a76\uff1a\u83ab\u5c14-\u5e93\u4ed1\u51c6\u5219");
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"solid/lemm1/sopl1"});
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "v_prescr", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "v_prescr", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0, -2e-3, -32e-3)", 0);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 17, 0);
    model.result("pg3").label("\u5e94\u529b (solid) 1");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg3").feature("surf1").set("threshold", "manual");
    model.result("pg3").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("colortabletrans", "none");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").set("resolution", "normal");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg3").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 17, 0);
    model.result("pg4").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid) 1");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"solid.epeGp"});
    model.result("pg4").feature("surf1").set("inheritplot", "none");
    model.result("pg4").feature("surf1").set("resolution", "normal");
    model.result("pg4").feature("surf1").set("colortabletype", "discrete");
    model.result("pg4").feature("surf1").set("bandcount", 11);
    model.result("pg4").feature("surf1").set("colortable", "AuroraAustralisDark");
    model.result("pg4").feature("surf1").set("descractive", true);
    model.result("pg4").feature("surf1").set("descr", "\u7b49\u6548\u5851\u6027\u5e94\u53d8");
    model.result("pg4").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid) 1");
    model.result("pg4").run();
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().set(2);
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().set(2);
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").setIndex("genpoints", "W", 0, 0);
    model.result().dataset("mir1").setIndex("genpoints", "W", 1, 0);
    model.result().dataset("mir1").set("removesymelem", true);
    model.result().dataset().duplicate("mir2", "mir1");
    model.result().dataset("mir2").set("data", "dset2");
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "kPa", 0, 3);
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").label("\u5e94\u529b\uff0c\u5fb7\u9c81\u514b-\u666e\u62c9\u683c\u51c6\u5219");
    model.result("pg1").set("data", "mir1");
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", 10);
    model.result("pg1").run();
    model.result("pg1").create("arwl1", "ArrowLine");
    model.result("pg1").feature("arwl1").set("expr", new String[]{"solid.fax", "solid.fay"});
    model.result("pg1").feature("arwl1")
         .set("descr", "\u5355\u4f4d\u53d8\u5f62\u9762\u79ef\u7684\u529b \uff08\u7a7a\u95f4\u5750\u6807\u7cfb\uff09");
    model.result("pg1").feature("arwl1").set("titletype", "none");
    model.result("pg1").feature("arwl1").set("arrowbase", "head");
    model.result("pg1").feature("arwl1").set("scaleactive", true);
    model.result("pg1").feature("arwl1").set("scale", "5E-4");
    model.result("pg1").feature("arwl1").set("inheritplot", "surf1");
    model.result("pg1").feature("arwl1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").label("\u5e94\u529b\uff0c\u83ab\u5c14-\u5e93\u4ed1\u51c6\u5219");
    model.result("pg3").set("data", "mir2");
    model.result("pg3").set("edges", false);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg3").feature("surf1").feature("def").set("scale", 10);
    model.result("pg3").run();
    model.result("pg3").create("arwl1", "ArrowLine");
    model.result("pg3").feature("arwl1").set("expr", new String[]{"solid.fax", "solid.fay"});
    model.result("pg3").feature("arwl1")
         .set("descr", "\u5355\u4f4d\u53d8\u5f62\u9762\u79ef\u7684\u529b \uff08\u7a7a\u95f4\u5750\u6807\u7cfb\uff09");
    model.result("pg3").feature("arwl1").set("titletype", "none");
    model.result("pg3").feature("arwl1").set("arrowbase", "head");
    model.result("pg3").feature("arwl1").set("scaleactive", true);
    model.result("pg3").feature("arwl1").set("scale", "5E-4");
    model.result("pg3").feature("arwl1").set("inheritplot", "surf1");
    model.result("pg3").feature("arwl1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").label("\u5851\u6027\u533a\u57df\uff0c\u83ab\u5c14-\u5e93\u4ed1\u51c6\u5219");
    model.result("pg4").set("data", "mir2");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "solid.epeGp>0");
    model.result("pg4").feature("surf1").set("bandcount", 2);
    model.result("pg4").feature("surf1").set("resolution", "custom");
    model.result("pg4").feature("surf1").set("refine", 2);
    model.result("pg2").run();
    model.result("pg2").label("\u5851\u6027\u533a\u57df\uff0c\u5fb7\u9c81\u514b-\u666e\u62c9\u683c");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "solid.epeGp>0");
    model.result("pg2").feature("surf1").set("bandcount", 2);
    model.result("pg2").feature("surf1").set("resolution", "custom");
    model.result("pg2").feature("surf1").set("refine", 2);
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u57fa\u811a\u538b\u529b vs. \u4f4d\u79fb");
    model.result("pg5").set("legendpos", "lowerright");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").set("data", "dset1");
    model.result("pg5").feature("ptgr1").selection().set(7);
    model.result("pg5").feature("ptgr1").set("expr", "abs(footing_pressure)");
    model.result("pg5").feature("ptgr1").set("descractive", true);
    model.result("pg5").feature("ptgr1").set("descr", "\u57fa\u811a\u538b\u529b");
    model.result("pg5").feature("ptgr1").set("xdata", "expr");
    model.result("pg5").feature("ptgr1").set("xdataexpr", "abs(v)");
    model.result("pg5").feature("ptgr1").set("xdatadescractive", true);
    model.result("pg5").feature("ptgr1").set("xdatadescr", "\u5782\u76f4\u4f4d\u79fb");
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr1").setIndex("legends", "\u5fb7\u9c81\u514b-\u666e\u62c9\u683c\u51c6\u5219", 0);
    model.result("pg5").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr2").set("data", "dset2");
    model.result("pg5").feature("ptgr2").set("titletype", "none");
    model.result("pg5").feature("ptgr2").setIndex("legends", "\u83ab\u5c14-\u5e93\u4ed1\u51c6\u5219", 0);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg2").run();
    model.result("pg2").set("data", "mir1");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().duplicate("pg6", "pg2");
    model.result("pg6").run();
    model.result("pg6").set("data", "dset1");
    model.result("pg6").selection().geom("geom1", 2);
    model.result("pg6").selection().geom("geom1", 2);
    model.result("pg6").selection().set(2);
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("plotarrayenable", true);
    model.result("pg6").set("arrayshape", "square");
    model.result("pg6").set("order", "columnmajor");
    model.result("pg6").set("relrowpadding", 0.15);
    model.result("pg6").set("relcolumnpadding", 0.1);
    model.result("pg6").feature("surf1").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("data", "dset1");
    model.result("pg6").feature("surf1").setIndex("looplevel", 1, 0);
    model.result("pg6").feature("surf1").set("manualindexing", true);
    model.result("pg6").feature("surf1").set("rowindex", 2);
    model.result("pg6").feature().duplicate("surf2", "surf1");
    model.result("pg6").feature("surf2").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature("surf2").setIndex("looplevel", 3, 0);
    model.result("pg6").feature("surf2").set("inheritplot", "surf1");
    model.result("pg6").feature("surf2").set("colindex", 1);
    model.result("pg6").feature().duplicate("surf3", "surf2");
    model.result("pg6").feature("surf3").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature("surf3").setIndex("looplevel", 5, 0);
    model.result("pg6").feature("surf3").set("rowindex", 1);
    model.result("pg6").feature("surf3").set("colindex", 0);
    model.result("pg6").feature().duplicate("surf4", "surf3");
    model.result("pg6").feature("surf4").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature("surf4").setIndex("looplevel", 7, 0);
    model.result("pg6").feature("surf4").set("colindex", 1);
    model.result("pg6").feature().duplicate("surf5", "surf4");
    model.result("pg6").feature("surf5").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature("surf5").setIndex("looplevel", 9, 0);
    model.result("pg6").feature("surf5").set("rowindex", 0);
    model.result("pg6").feature("surf5").set("colindex", 0);
    model.result("pg6").feature().duplicate("surf6", "surf5");
    model.result("pg6").feature("surf6").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature("surf6").setIndex("looplevel", 16, 0);
    model.result("pg6").feature("surf6").set("colindex", 1);
    model.result("pg6").run();
    model.result("pg6").create("ann1", "Annotation");
    model.result("pg6").feature("ann1").set("arraydim", "2");
    model.result("pg6").feature("ann1").set("data", "dset1");
    model.result("pg6").feature("ann1").setIndex("looplevel", 1, 0);
    model.result("pg6").feature("ann1").set("text", "$p_a = eval(abs(footing_pressure), MPa, 3) \\ \\textrm{MPa}$");
    model.result("pg6").feature("ann1").set("latexmarkup", true);
    model.result("pg6").feature("ann1").set("showpoint", false);
    model.result("pg6").feature("ann1").set("manualindexing", true);
    model.result("pg6").feature("ann1").set("rowindex", 2);
    model.result("pg6").feature("ann1").set("applytodatasetedgesplotarr", false);
    model.result("pg6").feature("ann1").create("trn1", "Transformation");
    model.result("pg6").run();
    model.result("pg6").feature("ann1").feature("trn1").set("move", new String[]{"0", "H*1.2"});
    model.result("pg6").feature("ann1").feature("trn1").set("applytodatasetedges", false);
    model.result("pg6").run();
    model.result("pg6").feature("ann1").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("ann2", "ann1");
    model.result("pg6").feature("ann2").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature("ann2").setIndex("looplevel", 3, 0);
    model.result("pg6").feature("ann2").set("colindex", 1);
    model.result("pg6").feature().duplicate("ann3", "ann2");
    model.result("pg6").feature("ann3").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature("ann3").setIndex("looplevel", 5, 0);
    model.result("pg6").feature("ann3").set("rowindex", 1);
    model.result("pg6").feature("ann3").set("colindex", 0);
    model.result("pg6").feature().duplicate("ann4", "ann3");
    model.result("pg6").feature("ann4").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature("ann4").setIndex("looplevel", 7, 0);
    model.result("pg6").feature("ann4").set("colindex", 1);
    model.result("pg6").feature().duplicate("ann5", "ann4");
    model.result("pg6").feature("ann5").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature("ann5").setIndex("looplevel", 9, 0);
    model.result("pg6").feature("ann5").set("rowindex", 0);
    model.result("pg6").feature("ann5").set("colindex", 0);
    model.result("pg6").feature().duplicate("ann6", "ann5");
    model.result("pg6").feature("ann6").set("arraydim", "2");
    model.result("pg6").run();
    model.result("pg6").feature("ann6").setIndex("looplevel", 16, 0);
    model.result("pg6").feature("ann6").set("colindex", 1);
    model.result("pg6").run();
    model.result().remove("pg6");

    model.title("\u9ecf\u571f\u5c42\u4e0a\u7684\u5149\u6ed1\u67d4\u6027\u6761\u5f62\u57fa\u7840");

    model
         .description("\u5ca9\u571f\u5de5\u7a0b\u95ee\u9898\u4e2d\u7684\u4e00\u4e2a\u5e38\u89c1\u9a8c\u8bc1\u95ee\u9898\u6d89\u53ca\u5230\u6d45\u9ecf\u571f\u5c42\u3002\u672c\u4f8b\u5728\u9ecf\u571f\u5c42\u65bd\u52a0\u4e00\u4e2a\u5782\u76f4\u8f7d\u8377\uff0c\u5e76\u7814\u7a76\u5176\u9759\u6001\u54cd\u5e94\u548c\u6781\u9650\u8f7d\u8377\u3002\u5176\u4e2d\u5c06\u9ecf\u571f\u4f5c\u4e3a\u5f39\u6027-\u7406\u60f3\u5851\u6027\u6750\u6599\u8fdb\u884c\u5efa\u6a21\uff0c\u5e76\u6bd4\u8f83\u4e86\u5e73\u9762\u5e94\u53d8\u6761\u4ef6\u4e0b\u7684\u5fb7\u9c81\u514b-\u666e\u62c9\u683c\u548c\u83ab\u5c14-\u5e93\u4ed1\u5c48\u670d\u6761\u4ef6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("flexible_footing.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
