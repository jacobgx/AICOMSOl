/*
 * ring_impact.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:25 by COMSOL 6.3.0.290. */
public class ring_impact {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);

    model.param().set("radius", "10[m]");
    model.param().descr("radius", "\u73af\u534a\u5f84");
    model.param().set("thickness", "0.3[m]");
    model.param().descr("thickness", "\u73af\u539a\u5ea6");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "radius");
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "thickness", 0);
    model.component("comp1").geom("geom1").feature("c1").label("\u73af 1");
    model.component("comp1").geom("geom1").feature("c1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("c1").set("color", "4");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("c2", "c1");
    model.component("comp1").geom("geom1").feature("c2").set("pos", new int[]{10, -20});
    model.component("comp1").geom("geom1").feature("c2").label("\u73af 2");
    model.component("comp1").geom("geom1").feature("c2").set("color", "12");
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("c1", 5);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("c2", 5);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").pair().create("p1", "Contact");
    model.component("comp1").pair("p1").source().set(14, 19, 21, 24);
    model.component("comp1").pair("p1").destination().set(9, 10, 15, 18);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"20[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"1/6"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1000"});

    model.component("comp1").physics("solid").create("init2", "init", 2);
    model.component("comp1").physics("solid").feature("init2").selection().named("geom1_c1_dom");
    model.component("comp1").physics("solid").feature("init2").set("ut", new int[]{0, -4, 0});
    model.component("comp1").physics("solid").feature("dcnt1").set("ContactMethodCtrl", "PenaltyDynamic");
    model.component("comp1").physics("solid").feature("dcnt1").set("penaltyCtrlPenalty", "ViscousOnly");
    model.component("comp1").physics("solid").feature("dcnt1").set("taun_penalty", ".1[ms]");
    model.component("comp1").physics("solid").feature("dcnt1").set("storeContactEnergy", true);
    model.component("comp1").physics("solid").feature().duplicate("dcnt2", "dcnt1");
    model.component("comp1").physics("solid").feature("dcnt2").set("pairSelection", "list");
    model.component("comp1").physics("solid").feature("dcnt2").set("pairs", new String[]{"p1"});
    model.component("comp1").physics("solid").feature("dcnt2").set("ContactMethodCtrl", "AugmentedLagrangeDynamic");
    model.component("comp1").physics("solid").feature("dcnt2").set("taun", ".01[ms]");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(3, 7);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection()
         .set(9, 10, 13, 14, 15, 18, 21, 24);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 20);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1\uff1a\u7f5a\u51fd\u6570");
    model.study("std1").feature("time").set("tlist", "range(0,0.1,20)");
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"solid/dcnt2"});
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsgenalpha", "manual");
    model.sol("sol1").feature("t1").set("timestepgenalpha", "0.05");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 201, 0);
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
    model.result("pg1").label("\u53d8\u5f62\uff08\u7f5a\u51fd\u6570\uff09");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "1");
    model.result("pg1").feature("surf1").set("titletype", "none");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "yellow");
    model.result("pg1").feature("surf1").create("sel1", "Selection");
    model.result("pg1").feature("surf1").feature("sel1").selection().named("geom1_c1_dom");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("color", "red");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("sel1").selection().named("geom1_c2_dom");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("plotgroup", "pg1");
    model.result().export("anim1").run();
    model.result().export("anim1").set("maxframes", 100);
    model.result().export("anim1").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/solid", true);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u589e\u5e7f\u62c9\u683c\u6717\u65e5");
    model.study("std2").feature("time").set("tlist", "range(0,0.1,20)");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").set("tstepsgenalpha", "manual");
    model.sol("sol2").feature("t1").set("timestepgenalpha", "0.05");
    model.sol("sol2").feature("t1").feature("se1").set("maxsegiter", 50);

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 201, 0);
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
    model.result("pg2").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def").set("scale", "1");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").run();
    model.result("pg2").label("\u53d8\u5f62 (AugLag)");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").feature("surf1").set("titletype", "none");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "yellow");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().named("geom1_c1_dom");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf2", "surf1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("color", "red");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").feature("sel1").selection().named("geom1_c2_dom");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().export().create("anim2", "Animation");
    model.result().export("anim2").set("target", "player");
    model.result().export("anim2").set("plotgroup", "pg2");
    model.result().export("anim2").run();
    model.result().export("anim2").set("maxframes", 100);
    model.result().export("anim2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u80fd\u91cf\u5e73\u8861");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"solid.Wk_tot"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u603b\u52a8\u80fd"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"J"});
    model.result("pg3").feature("glob1").set("expr", new String[]{"solid.Wk_tot", "solid.Wh_tot"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u603b\u52a8\u80fd", "\u603b\u50a8\u80fd"});
    model.result("pg3").feature("glob1").set("expr", new String[]{"solid.Wk_tot", "solid.Wh_tot", "solid.Wd_tot"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u603b\u52a8\u80fd", "\u603b\u50a8\u80fd", "\u603b\u80fd\u8017"});
    model.result("pg3").run();
    model.result("pg3").feature("glob1").setIndex("unit", "kJ", 0);
    model.result("pg3").feature("glob1").setIndex("unit", "kJ", 1);
    model.result("pg3").feature("glob1").setIndex("unit", "kJ", 2);
    model.result("pg3").feature("glob1").setIndex("expr", "solid.Wk_tot+solid.Wh_tot+solid.Wd_tot", 3);
    model.result("pg3").feature("glob1").setIndex("unit", "kJ", 3);
    model.result("pg3").feature("glob1").setIndex("descr", "\u603b\u80fd\u91cf", 3);
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").feature().duplicate("glob2", "glob1");
    model.result("pg3").run();
    model.result("pg3").feature("glob2").set("data", "dset2");
    model.result("pg3").feature("glob2").set("linestyle", "dashed");
    model.result("pg3").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg3").feature("glob2").set("legend", false);
    model.result("pg3").run();
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u80fd\u91cf (kJ)");
    model.result("pg3").set("legendpos", "middleright");
    model.result("pg3").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u7ebf\u6027\u52a8\u91cf\uff0cx\uff08\u7f5a\u51fd\u6570\uff09");
    model.result().evaluationGroup("eg1").create("int1", "IntSurface");
    model.result().evaluationGroup("eg1").feature("int1").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("int1").selection().named("geom1_c1_dom");
    model.result().evaluationGroup("eg1").feature("int1").setIndex("expr", "solid.rho*ut*solid.d", 0);
    model.result().evaluationGroup("eg1").feature("int1").setIndex("unit", "kg*m/s", 0);
    model.result().evaluationGroup("eg1").feature("int1").setIndex("descr", "\u73af 1", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("int2", "int1");
    model.result().evaluationGroup("eg1").feature("int2").selection().named("geom1_c2_dom");
    model.result().evaluationGroup("eg1").feature("int2").setIndex("descr", "\u73af 2", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("int3", "int2");
    model.result().evaluationGroup("eg1").feature("int3").selection().all();
    model.result().evaluationGroup("eg1").feature("int3").setIndex("descr", "\u603b\u8ba1", 0);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().duplicate("eg2", "eg1");
    model.result().evaluationGroup("eg2").label("\u7ebf\u6027\u52a8\u91cf\uff0cx (AugLag)");
    model.result().evaluationGroup("eg2").set("data", "dset2");
    model.result().evaluationGroup("eg2").run();
    model.result().evaluationGroup().duplicate("eg3", "eg1");
    model.result().evaluationGroup("eg3").label("\u7ebf\u6027\u52a8\u91cf\uff0cy\uff08\u7f5a\u51fd\u6570\uff09");
    model.result().evaluationGroup("eg3").feature("int1").setIndex("expr", "solid.rho*vt*solid.d", 0);
    model.result().evaluationGroup("eg3").feature("int2").setIndex("expr", "solid.rho*vt*solid.d", 0);
    model.result().evaluationGroup("eg3").feature("int3").setIndex("expr", "solid.rho*vt*solid.d", 0);
    model.result().evaluationGroup("eg3").run();
    model.result().evaluationGroup().duplicate("eg4", "eg3");
    model.result().evaluationGroup("eg4").label("\u7ebf\u6027\u52a8\u91cf\uff0cy (AugLag)");
    model.result().evaluationGroup("eg4").set("data", "dset2");
    model.result().evaluationGroup("eg4").run();
    model.result().evaluationGroup().duplicate("eg5", "eg1");
    model.result().evaluationGroup("eg5").label("\u89d2\u52a8\u91cf\uff08\u7f5a\u51fd\u6570\uff09");
    model.result().evaluationGroup("eg5").feature("int1").setIndex("expr", "solid.rho*(x*vt-y*ut)*solid.d", 0);
    model.result().evaluationGroup("eg5").feature("int1").setIndex("unit", "kg*m^2/s", 0);
    model.result().evaluationGroup("eg5").feature("int2").setIndex("expr", "solid.rho*(x*vt-y*ut)*solid.d", 0);
    model.result().evaluationGroup("eg5").feature("int2").setIndex("unit", "kg*m^2/s", 0);
    model.result().evaluationGroup("eg5").feature("int3").setIndex("expr", "solid.rho*(x*vt-y*ut)*solid.d", 0);
    model.result().evaluationGroup("eg5").feature("int3").setIndex("unit", "kg*m^2/s", 0);
    model.result().evaluationGroup("eg5").run();
    model.result().evaluationGroup().duplicate("eg6", "eg5");
    model.result().evaluationGroup("eg6").label("\u89d2\u52a8\u91cf (AugLag)");
    model.result().evaluationGroup("eg6").set("data", "dset2");
    model.result().evaluationGroup("eg6").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u7ebf\u6027\u52a8\u91cf\uff0cx");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u7ebf\u6027\u52a8\u91cf (kg*m/s)");
    model.result("pg4").create("tblp1", "Table");
    model.result("pg4").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg4").feature("tblp1").set("linewidth", "preference");
    model.result("pg4").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg4").feature("tblp1").set("linewidth", 2);
    model.result("pg4").feature("tblp1").set("legend", true);
    model.result("pg4").feature().duplicate("tblp2", "tblp1");
    model.result("pg4").run();
    model.result("pg4").feature("tblp2").set("evaluationgroup", "eg2");
    model.result("pg4").feature("tblp2").set("linestyle", "dashed");
    model.result("pg4").feature("tblp2").set("linecolor", "cyclereset");
    model.result("pg4").feature("tblp2").set("legend", false);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u7ebf\u6027\u52a8\u91cf\uff0cy");
    model.result("pg5").run();
    model.result("pg5").feature("tblp1").set("evaluationgroup", "eg3");
    model.result("pg5").run();
    model.result("pg5").feature("tblp2").set("evaluationgroup", "eg4");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u89d2\u52a8\u91cf");
    model.result("pg6").set("ylabel", "\u89d2\u52a8\u91cf (kg*m^2/s)");
    model.result("pg6").run();
    model.result("pg6").feature("tblp1").set("evaluationgroup", "eg5");
    model.result("pg6").run();
    model.result("pg6").feature("tblp2").set("evaluationgroup", "eg6");
    model.result("pg6").run();
    model.result("pg1").run();
    model.result().duplicate("pg7", "pg1");
    model.result("pg7").run();
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("edges", false);
    model.result("pg7").setIndex("looplevel", 1, 0);
    model.result("pg7").create("line1", "Line");
    model.result("pg7").feature("line1").set("expr", "1");
    model.result("pg7").feature("line1").set("coloring", "uniform");
    model.result("pg7").feature("line1").set("color", "black");
    model.result("pg7").feature("line1").set("inheritplot", "surf1");
    model.result("pg7").feature("line1").set("inheritcolor", false);
    model.result("pg7").feature("line1").set("inheritrange", false);
    model.result("pg7").feature("line1").set("inheritheightscale", false);
    model.result("pg7").feature("line1").set("inherittubescale", false);
    model.result("pg7").feature("line1").create("def1", "Deform");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf3", "surf1");
    model.result("pg7").feature().duplicate("surf4", "surf2");
    model.result("pg7").feature().duplicate("line2", "line1");
    model.result("pg7").run();
    model.result("pg7").feature("surf3").set("data", "dset1");
    model.result("pg7").feature("surf3").setIndex("looplevel", 51, 0);
    model.result("pg7").run();
    model.result("pg7").feature("surf4").set("data", "dset1");
    model.result("pg7").feature("surf4").setIndex("looplevel", 51, 0);
    model.result("pg7").run();
    model.result("pg7").feature("line2").set("data", "dset1");
    model.result("pg7").feature("line2").setIndex("looplevel", 51, 0);
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf5", "surf3");
    model.result("pg7").feature().duplicate("surf6", "surf4");
    model.result("pg7").feature().duplicate("line3", "line2");
    model.result("pg7").run();
    model.result("pg7").feature("surf5").setIndex("looplevel", 101, 0);
    model.result("pg7").run();
    model.result("pg7").feature("surf6").setIndex("looplevel", 101, 0);
    model.result("pg7").run();
    model.result("pg7").feature("line3").setIndex("looplevel", 101, 0);
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf7", "surf5");
    model.result("pg7").feature().duplicate("surf8", "surf6");
    model.result("pg7").feature().duplicate("line4", "line3");
    model.result("pg7").run();
    model.result("pg7").feature("surf7").setIndex("looplevel", 151, 0);
    model.result("pg7").run();
    model.result("pg7").feature("surf8").setIndex("looplevel", 151, 0);
    model.result("pg7").run();
    model.result("pg7").feature("line4").setIndex("looplevel", 151, 0);
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf9", "surf7");
    model.result("pg7").feature().duplicate("surf10", "surf8");
    model.result("pg7").feature().duplicate("line5", "line4");
    model.result("pg7").run();
    model.result("pg7").feature("surf9").setIndex("looplevel", 201, 0);
    model.result("pg7").run();
    model.result("pg7").feature("surf10").setIndex("looplevel", 201, 0);
    model.result("pg7").run();
    model.result("pg7").feature("line5").setIndex("looplevel", 201, 0);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().remove("pg7");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 61, 0);
    model.result("pg1").run();

    model.title("\u4e24\u4e2a\u8f6f\u73af\u4e4b\u95f4\u7684\u78b0\u649e");

    model
         .description("\u8fd9\u4e2a\u6982\u5ff5\u793a\u4f8b\u4f7f\u7528\u201c\u56fa\u4f53\u529b\u5b66\u201d\u63a5\u53e3\u5bf9\u4e24\u4e2a\u5f39\u6027\u73af\u4e4b\u95f4\u7684\u8f6f\u51b2\u51fb\u8fdb\u884c\u5efa\u6a21\uff0c\u901a\u8fc7\u4e3a\u5176\u4e2d\u4e00\u4e2a\u73af\u7ed9\u5b9a\u521d\u59cb\u901f\u5ea6\u6765\u5f15\u53d1\u51b2\u51fb\u4e8b\u4ef6\u3002\u4e24\u4e2a\u73af\u90fd\u4e0d\u53d7\u7ea6\u675f\u548c\u4efb\u4f55\u5916\u529b\u4f5c\u7528\u3002\u672c\u4f8b\u4f7f\u7528\u9ecf\u6027\u516c\u5f0f\u5bf9\u63a5\u89e6\u8fdb\u884c\u5efa\u6a21\uff0c\u8be5\u516c\u5f0f\u91c7\u7528\u4e86\u7f5a\u51fd\u6570\u548c\u589e\u5e7f\u62c9\u683c\u6717\u65e5\u6280\u672f\uff0c\u5e76\u8bc1\u660e\u4e86\u7ebf\u6027\u52a8\u91cf\u5b88\u6052\u3001\u89d2\u52a8\u91cf\u5b88\u6052\u548c\u80fd\u91cf\u5b88\u6052\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("ring_impact.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
