/*
 * inplane_and_space_truss.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:24 by COMSOL 6.3.0.290. */
public class inplane_and_space_truss {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("truss", "Truss", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/truss", true);

    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", 2);
    model.component("comp1").geom("geom1").feature("sq1").set("rot", 45);
    model.component("comp1").geom("geom1").feature("sq1").set("type", "curve");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"0", "sqrt(8)"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("truss").feature("csd1").set("SectionType", "CircularSection");
    model.component("comp1").physics("truss").feature("csd1").set("do_circ", 0.05);
    model.component("comp1").physics("truss").create("pin1", "Pinned", 0);
    model.component("comp1").physics("truss").feature("pin1").selection().set(1, 4);
    model.component("comp1").physics("truss").create("pl1", "PointLoad", 0);
    model.component("comp1").physics("truss").feature("pl1").selection().set(2);
    model.component("comp1").physics("truss").feature("pl1").set("forcePoint", new String[]{"0", "-50e3", "0"});

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup("def").set("density", "");
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.material("mat1").propertyGroup("def").set("density", new String[]{"2900"});
    model.material("mat1").propertyGroup("Enu").set("E", new String[]{"70e9"});
    model.material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material().create("matlnk1", "Link");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", new String[]{"truss.misesGp"});
    model.result("pg1").feature("line1").set("threshold", "manual");
    model.result("pg1").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("line1").set("colortable", "Rainbow");
    model.result("pg1").feature("line1").set("colortabletrans", "none");
    model.result("pg1").feature("line1").set("colorscalemode", "linear");
    model.result("pg1").label("\u5e94\u529b (truss)");
    model.result("pg1").feature("line1").set("colortable", "Rainbow");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", "truss.re");
    model.result("pg1").feature("line1").set("resolution", "extrafine");
    model.result("pg1").feature("line1").set("smooth", "internal");
    model.result("pg1").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line1").set("tuberadiusscale", 1);
    model.result("pg1").feature("line1").set("tubeendcaps", false);
    model.result("pg1").feature("line1").create("def", "Deform");
    model.result("pg1").feature("line1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", new String[]{"truss.Nxl"});
    model.result("pg2").feature("line1").set("threshold", "manual");
    model.result("pg2").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("line1").set("colortable", "Wave");
    model.result("pg2").feature("line1").set("colortabletrans", "none");
    model.result("pg2").feature("line1").set("colorscalemode", "linearsymmetric");
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("radiusexpr", "truss.re");
    model.result("pg2").feature("line1").set("resolution", "extrafine");
    model.result("pg2").feature("line1").set("smooth", "internal");
    model.result("pg2").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("line1").set("tuberadiusscale", 1);
    model.result("pg2").feature("line1").set("tubeendcaps", false);
    model.result("pg2").feature("line1").create("def", "Deform");
    model.result("pg2").feature("line1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg2").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").label("\u529b (truss)");
    model.result("pg2").label("\u529b (truss)");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").run();
    model.result("pg2").feature("line1").set("unit", "kN");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u9876\u70b9\u7684\u4f4d\u79fb (2D)");
    model.result().evaluationGroup("eg1").create("pev1", "EvalPoint");
    model.result().evaluationGroup("eg1").feature("pev1").selection().set(2, 3);
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("expr", "v", 0);
    model.result().evaluationGroup("eg1").run();

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").set("opname", "aveop_ac");
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().set(5);
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").set("opname", "aveop_ad");
    model.component("comp1").cpl("aveop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop2").selection().set(4);
    model.component("comp1").cpl().create("aveop3", "Average");
    model.component("comp1").cpl("aveop3").set("axisym", true);
    model.component("comp1").cpl("aveop3").set("opname", "aveop_cd");
    model.component("comp1").cpl("aveop3").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop3").selection().set(3);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("F_ac", "aveop_ac(truss.Nxl)");
    model.component("comp1").variable("var1").descr("F_ac", "\u8f74\u5411\u529b\uff0c\u6784\u4ef6 ac");
    model.component("comp1").variable("var1").set("F_ad", "aveop_ad(truss.Nxl)");
    model.component("comp1").variable("var1").descr("F_ad", "\u8f74\u5411\u529b\uff0c\u6784\u4ef6 ad");
    model.component("comp1").variable("var1").set("F_cd", "aveop_cd(truss.Nxl)");
    model.component("comp1").variable("var1").descr("F_cd", "\u8f74\u5411\u529b\uff0c\u6784\u4ef6 cd");

    model.sol("sol1").updateSolution();

    model.result("pg1").run();
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").label("\u6784\u4ef6\u4e2d\u7684\u8f74\u5411\u529b (2D)");
    model.result().evaluationGroup("eg2").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "F_ac", 0);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "F_ad", 1);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "F_cd", 2);
    model.result().evaluationGroup("eg2").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").physics().create("truss2", "Truss", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/truss2", false);

    model.component("comp2").geom("geom2").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/truss", false);
    model.study("std2").feature("stat").setSolveFor("/physics/truss2", true);

    model.component("comp2").geom("geom2").create("wp1", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp1").set("unite", true);
    model.component("comp2").geom("geom2").feature("wp1").geom().create("sq1", "Square");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("sq1").set("size", 2);
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("sq1").set("rot", 45);
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("sq1").set("type", "curve");
    model.component("comp2").geom("geom2").feature("wp1").geom().run("sq1");
    model.component("comp2").geom("geom2").run("wp1");
    model.component("comp2").geom("geom2").create("rot1", "Rotate");
    model.component("comp2").geom("geom2").feature("rot1").set("keep", true);
    model.component("comp2").geom("geom2").feature("rot1").selection("input").set("wp1");
    model.component("comp2").geom("geom2").feature("rot1").set("axistype", "cartesian");
    model.component("comp2").geom("geom2").feature("rot1").set("ax3", new int[]{0, 1, 0});
    model.component("comp2").geom("geom2").feature("rot1").set("rot", 90);
    model.component("comp2").geom("geom2").runPre("fin");
    model.component("comp2").geom("geom2").create("ls1", "LineSegment");
    model.component("comp2").geom("geom2").feature("ls1").set("specify1", "coord");
    model.component("comp2").geom("geom2").feature("ls1").set("specify2", "coord");
    model.component("comp2").geom("geom2").feature("ls1").set("coord2", new String[]{"0", "sqrt(8)", "0"});
    model.component("comp2").geom("geom2").runPre("fin");

    model.component("comp2").cpl().create("aveop4", "Average");

    model.component("comp2").geom("geom2").run();

    model.component("comp2").cpl("aveop4").set("axisym", true);
    model.component("comp2").cpl("aveop4").set("opname", "aveop_ac");
    model.component("comp2").cpl("aveop4").selection().geom("geom2", 1);
    model.component("comp2").cpl("aveop4").selection().set(8);
    model.component("comp2").cpl().create("aveop5", "Average");
    model.component("comp2").cpl("aveop5").set("axisym", true);
    model.component("comp2").cpl("aveop5").set("opname", "aveop_ad");
    model.component("comp2").cpl("aveop5").selection().geom("geom2", 1);
    model.component("comp2").cpl("aveop5").selection().set(4);
    model.component("comp2").cpl().create("aveop6", "Average");
    model.component("comp2").cpl("aveop6").set("axisym", true);
    model.component("comp2").cpl("aveop6").set("opname", "aveop_cd");
    model.component("comp2").cpl("aveop6").selection().geom("geom2", 1);
    model.component("comp2").cpl("aveop6").selection().set(5);

    model.component("comp2").variable().create("var2");
    model.component("comp2").variable("var2")
         .set("F_ac", "aveop_ac(truss2.Nxl)", "\u8f74\u5411\u529b\uff0c\u6784\u4ef6 ac");
    model.component("comp2").variable("var2")
         .set("F_ad", "aveop_ad(truss2.Nxl)", "\u8f74\u5411\u529b\uff0c\u6784\u4ef6 ad");
    model.component("comp2").variable("var2")
         .set("F_cd", "aveop_cd(truss2.Nxl)", "\u8f74\u5411\u529b\uff0c\u6784\u4ef6 cd");

    model.component("comp2").physics("truss2").feature("csd1").set("area", "pi/4*0.05^2");
    model.component("comp2").physics("truss2").create("csd2", "CrossSectionTruss", 1);
    model.component("comp2").physics("truss2").feature("csd2").selection().set(5);
    model.component("comp2").physics("truss2").feature("csd2").set("area", "2*pi/4*0.05^2");
    model.component("comp2").physics("truss2").create("pin1", "Pinned", 0);
    model.component("comp2").physics("truss2").feature("pin1").selection().set(1, 3, 4, 6);
    model.component("comp2").physics("truss2").create("pl1", "PointLoad", 0);
    model.component("comp2").physics("truss2").feature("pl1").selection().set(2);
    model.component("comp2").physics("truss2").feature("pl1").set("forcePoint", new String[]{"0", "-100e3", "0"});

    model.component("comp2").material().create("matlnk2", "Link");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", new String[]{"truss2.misesGp"});
    model.result("pg3").feature("line1").set("threshold", "manual");
    model.result("pg3").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("line1").set("colortable", "Rainbow");
    model.result("pg3").feature("line1").set("colortabletrans", "none");
    model.result("pg3").feature("line1").set("colorscalemode", "linear");
    model.result("pg3").label("\u5e94\u529b (truss2)");
    model.result("pg3").feature("line1").set("colortable", "Rainbow");
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("radiusexpr", "truss2.re");
    model.result("pg3").feature("line1").set("resolution", "extrafine");
    model.result("pg3").feature("line1").set("smooth", "internal");
    model.result("pg3").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg3").feature("line1").set("tuberadiusscale", 1);
    model.result("pg3").feature("line1").set("tubeendcaps", false);
    model.result("pg3").feature("line1").create("def", "Deform");
    model.result("pg3").feature("line1").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg3").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", new String[]{"truss2.Nxl"});
    model.result("pg4").feature("line1").set("threshold", "manual");
    model.result("pg4").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("line1").set("colortable", "Wave");
    model.result("pg4").feature("line1").set("colortabletrans", "none");
    model.result("pg4").feature("line1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("radiusexpr", "truss2.re");
    model.result("pg4").feature("line1").set("resolution", "extrafine");
    model.result("pg4").feature("line1").set("smooth", "internal");
    model.result("pg4").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg4").feature("line1").set("tuberadiusscale", 1);
    model.result("pg4").feature("line1").set("tubeendcaps", false);
    model.result("pg4").feature("line1").create("def", "Deform");
    model.result("pg4").feature("line1").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg4").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg4").label("\u529b (truss2)");
    model.result("pg4").label("\u529b (truss2)");
    model.result("pg4").run();
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").run();
    model.result("pg4").feature("line1").set("unit", "kN");
    model.result().evaluationGroup().duplicate("eg3", "eg1");
    model.result().evaluationGroup("eg3").label("\u9876\u70b9\u7684\u4f4d\u79fb (3D)");
    model.result().evaluationGroup("eg3").set("data", "dset3");
    model.result().evaluationGroup("eg3").feature("pev1").selection().set(2, 5);
    model.result().evaluationGroup("eg3").feature("pev1").setIndex("expr", "v2", 0);
    model.result().evaluationGroup("eg3").run();
    model.result().evaluationGroup().duplicate("eg4", "eg2");
    model.result().evaluationGroup("eg4").label("\u6784\u4ef6\u4e2d\u7684\u8f74\u5411\u529b (3D)");
    model.result().evaluationGroup("eg4").set("data", "dset3");
    model.result().evaluationGroup("eg4").feature("gev1").setIndex("expr", "F_cd/2", 2);
    model.result().evaluationGroup("eg4").run();
    model.result("pg2").run();

    model.title("\u9762\u5185\u548c\u7a7a\u95f4\u6841\u67b6");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u7b80\u5355\u7684\u4e8c\u7ef4\u6841\u67b6\u7684\u7ebf\u6027\u9759\u6001\u5206\u6790\uff0c\u4f7f\u7528\u201c\u6841\u67b6\u201d\u63a5\u53e3\u5bf9\u7528\u9500\u9489\u8fde\u63a5\u7684\u6841\u67b6\u8fdb\u884c\u6a21\u62df\u3002\u8ba1\u7b97\u7684\u89e3\u4e0e\u89e3\u6790\u7ed3\u679c\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.label("inplane_and_space_truss.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
