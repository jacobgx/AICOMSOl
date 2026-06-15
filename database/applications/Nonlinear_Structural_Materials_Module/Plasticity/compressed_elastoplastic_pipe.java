/*
 * compressed_elastoplastic_pipe.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:33 by COMSOL 6.3.0.290. */
public class compressed_elastoplastic_pipe {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Plasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("Ro", "200[mm]");
    model.param().descr("Ro", "\u5916\u534a\u5f84");
    model.param().set("thic", "25[mm]");
    model.param().descr("thic", "\u7ba1\u58c1\u539a\u5ea6");
    model.param().set("Sy", "250[MPa]");
    model.param().descr("Sy", "\u5c48\u670d\u5e94\u529b");
    model.param().set("hfact", "1");
    model.param().descr("hfact", "\u786c\u5316\u66f2\u7ebf\u4e58\u5b50");
    model.param().set("maxLoad", "6[MN]");
    model.param().descr("maxLoad", "\u4ece\u5de5\u5177\u65bd\u52a0\u7684\u6700\u5927\u8f7d\u8377");
    model.param().set("par", "0");
    model.param().descr("par", "\u89e3\u53c2\u6570");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").label("\u5706\uff1a\u7ba1");
    model.component("comp1").geom("geom1").feature("c1").set("r", "Ro");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "thic", 0);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("c1", 1);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").label("\u77e9\u5f62\uff1a\u5de5\u5177");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"0.05", "Ro"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"Ro+0.0001", "0"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("r1", 4);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "Ro/10");
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1")
         .label("\u591a\u8fb9\u5f62\uff1a\u63a5\u89e6\u5bf9\u79f0\u9762");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.15, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -0.01, 1, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").pair().create("p1", "Contact");
    model.component("comp1").pair("p1").source().set(6, 10);
    model.component("comp1").pair("p1").destination().set(5);
    model.component("comp1").pair().create("p2", "Contact");
    model.component("comp1").pair("p2").source().set(1);
    model.component("comp1").pair("p2").destination().set(4);

    model.component("comp1").physics("solid").feature("lemm1").create("plsty1", "Plasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1").selection().set(1);
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1")
         .set("IsotropicHardeningModel", "HardeningFunction");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"195[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"8000"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic_material_model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", new String[]{"Sy"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"0.00000", "0"}, 
         {"0.00062", "25"}, 
         {"0.00140", "50"}, 
         {"0.00200", "66"}, 
         {"0.00439", "100"}, 
         {"0.00952", "125"}, 
         {"0.01950", "150"}, 
         {"0.03592", "175"}, 
         {"0.06028", "200"}, 
         {"0.09400", "225"}, 
         {"0.13845", "250"}, 
         {"0.19498", "275"}, 
         {"0.26487", "300"}, 
         {"0.34939", "325"}, 
         {"0.44978", "350"}, 
         {"0.52292", "366"}});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("funcname", "hardFcn");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .setIndex("argunit", 1, 0);
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .setIndex("fununit", "MPa", 0);
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmagh", new String[]{"hardFcn(epe)*hfact"});

    model.common().create("state1", "StateVariables", "");
    model.common("state1").setIndex("state", "peakParam", 0);
    model.common("state1").setIndex("initialValue", "", 0);
    model.common("state1").setIndex("updateExpression", "", 0);
    model.common("state1").setIndex("description", "", 0);
    model.common("state1").setIndex("initialValue", 0, 0);
    model.common("state1")
         .setIndex("updateExpression", "if(peakParam>0,peakParam,if(comp1.reacF>maxLoad,par,0))", 0);
    model.common("state1").setIndex("description", "\u6700\u5927\u538b\u7d27\u7684\u53c2\u6570\u503c", 0);
    model.common("state1").set("update", "afterStep");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "ReacInt");
    model.component("comp1").cpl("intop1").selection().set(2);
    model.component("comp1").cpl("intop1").set("method", "summation");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "AreaInt");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().set(4);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("reacF", "-2*ReacInt(solid.RFx)");
    model.component("comp1").variable("var1").descr("reacF", "\u4ece\u5de5\u5177\u65bd\u52a0\u7684\u529b");
    model.component("comp1").variable("var1").set("area", "AreaInt(-x*solid.nx)*4");
    model.component("comp1").variable("var1").descr("area", "\u5f53\u524d\u6d41\u9053\u6a2a\u622a\u9762\u79ef");
    model.component("comp1").variable("var1").set("disp", "if(peakParam,peakParam-(par-peakParam)/2,par)[mm]");
    model.component("comp1").variable("var1").descr("disp", "\u538b\u7d27\u51fd\u6570");

    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 1);
    model.component("comp1").physics("solid").feature("sym1").selection().set(2, 3);
    model.component("comp1").physics("solid").create("disp1", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp1")
         .label("\u6307\u5b9a\u4f4d\u79fb\uff1a\u538b\u7d27\u8ddd\u79bb");
    model.component("comp1").physics("solid").feature("disp1").selection().set(2);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "-disp", 0);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis2").selection().set(10);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis2").set("numelem", 30);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(4, 5);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 80);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 8);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("symmetric", true);
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "Ro", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "Ro", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "par", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,4,152) range(154,1,200)", 0);
    model.study("std1").feature("stat").set("plot", true);
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
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
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().remove("pg1");

    model.study("std1").feature("stat").set("plotgroup", "Default");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
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
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 1, 0);
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
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").label("\u63a5\u89e6\u529b (solid)");
    model.result("pg3").set("showlegends", true);
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg3").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg3").feature("surf1").set("coloring", "uniform");
    model.result("pg3").feature("surf1").set("color", "gray");
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg3").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg3").feature("surf1").feature("def").set("scale", 1);
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg3").feature("surf1").feature("sel1").selection().set(1, 2);
    model.result("pg3").create("arwl1", "ArrowLine");
    model.result("pg3").feature("arwl1").set("arrowbase", "head");
    model.result("pg3").feature("arwl1").set("expr", new String[]{"solid.dcnt1.Tnx", "solid.dcnt1.Tny"});
    model.result("pg3").feature("arwl1").set("placement", "gausspoints");
    model.result("pg3").feature("arwl1").set("gporder", 4);
    model.result("pg3").feature("arwl1").label("\u63a5\u89e6 1, \u538b\u529b");
    model.result("pg3").feature("arwl1").set("inheritplot", "none");
    model.result("pg3").feature("arwl1").set("color", "green");
    model.result("pg3").feature("arwl1").create("col", "Color");
    model.result("pg3").feature("arwl1").feature("col").set("colortable", "Rainbow");
    model.result("pg3").feature("arwl1").feature("col").set("colortabletrans", "none");
    model.result("pg3").feature("arwl1").feature("col").set("colorscalemode", "linear");
    model.result("pg3").feature("arwl1").feature("col").set("colordata", "arrowlength");
    model.result("pg3").feature("arwl1").feature("col").set("coloring", "gradient");
    model.result("pg3").feature("arwl1").feature("col").set("topcolor", "green");
    model.result("pg3").feature("arwl1").feature("col").set("bottomcolor", "custom");
    model.result("pg3").feature("arwl1").feature("col")
         .set("custombottomcolor", new double[]{0.509804, 0.54902, 0.509804});
    model.result("pg3").feature("arwl1").create("def", "Deform");
    model.result("pg3").feature("arwl1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg3").feature("arwl1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").feature("arwl1").feature("def").set("scaleactive", true);
    model.result("pg3").feature("arwl1").feature("def").set("scale", 1);
    model.result("pg3").feature().move("surf1", 1);
    model.result("pg3").label("\u63a5\u89e6\u529b (solid)");
    model.result("pg3").run();

    model.sol("sol1").feature("s1").feature("p1").set("porder", "linear");
    model.sol("sol1").feature("s1").feature("p1").set("paramtuning", true);
    model.sol("sol1").feature("s1").feature("p1").set("pinitstep", 1);
    model.sol("sol1").feature("s1").feature("p1").set("pmaxstep", 1);

    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").set("removesymelem", true);
    model.result().dataset().create("mir2", "Mirror2D");
    model.result().dataset("mir2").set("data", "mir1");
    model.result().dataset("mir2").setIndex("genpoints", 1, 1, 0);
    model.result().dataset("mir2").setIndex("genpoints", 0, 1, 1);
    model.result().dataset("mir2").set("removesymelem", true);
    model.result("pg1").run();
    model.result("pg1").set("data", "mir2");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("unit", "MPa");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u5269\u4f59\u6a2a\u622a\u9762\u79ef");
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "area", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "mm^2", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "", 0);
    model.result().evaluationGroup("eg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 55, 0);
    model.result("pg1").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg1");
    model.result().export("anim1").run();
    model.result("pg2").run();
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("rangecoloractive", true);
    model.result("pg2").feature("surf1").set("rangecolormax", 1.1);
    model.result("pg2").feature("surf1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg2").run();
    model.result("pg2").create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", "solid.epeGp");
    model.result("pg2").feature("con1").set("titletype", "none");
    model.result("pg2").feature("con1").set("number", 1);
    model.result("pg2").feature("con1").set("levelmethod", "levels");
    model.result("pg2").feature("con1").set("levels", 0.52);
    model.result("pg2").feature("con1").set("contourtype", "tubes");
    model.result("pg2").feature("con1").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("con1").set("radiusexpr", "5e-4");
    model.result("pg2").feature("con1").set("coloring", "uniform");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").feature("con1").set("inheritplot", "surf1");
    model.result("pg2").feature("con1").set("inheritcolor", false);
    model.result("pg2").feature("con1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u538b\u7d27\u529b");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u5355\u4f4d\u957f\u5ea6\u7684\u529b (MN/m)");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u538b\u7d27\u8ddd\u79bb (mm)");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"reacF"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u4ece\u5de5\u5177\u65bd\u52a0\u7684\u529b"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg4").feature("glob1").setIndex("unit", "MN", 0);
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "disp");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u6d41\u9053\u9762\u79ef");
    model.result("pg5").set("ylabel", "\u6a2a\u622a\u9762\u79ef [mm<sup>2</sup>]");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").setIndex("expr", "area", 0);
    model.result("pg5").feature("glob1").setIndex("unit", "mm^2", 0);
    model.result("pg5").run();
    model.result("pg2").run();
    model.result().duplicate("pg6", "pg2");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("surf1").create("filt1", "Filter");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").feature("filt1").set("expr", "y>0.23");
    model.result("pg6").run();
    model.result("pg6").feature("con1").create("filt1", "Filter");
    model.result("pg6").run();
    model.result("pg6").feature("con1").feature("filt1").set("expr", "y>0.23");
    model.result("pg6").run();
    model.result("pg6").set("edges", false);
    model.result("pg6").run();
    model.result().remove("pg6");
    model.result("pg1").run();

    model.title("\u5f39\u5851\u6027\u7ba1\u7684\u538b\u7d27\u8fc7\u7a0b");

    model
         .description("\u4e3a\u4e86\u89e3\u51b3\u53d7\u635f\u7ba1\u9053\u7684\u5bc6\u5c01\u95ee\u9898\uff0c\u53ef\u4ee5\u5c06\u5176\u6324\u538b\u5728\u4e24\u4e2a\u521a\u6027\u538b\u5934\u4e4b\u95f4\uff0c\u76f4\u81f3\u5176\u51e0\u4e4e\u5b8c\u5168\u5e73\u6574\u3002\u6b64\u64cd\u4f5c\u4f1a\u4ea7\u751f\u975e\u5e38\u5927\u7684\u5851\u6027\u5e94\u53d8\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("compressed_elastoplastic_pipe.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
