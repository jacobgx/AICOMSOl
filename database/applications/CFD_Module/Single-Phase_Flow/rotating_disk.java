/*
 * rotating_disk.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class rotating_disk {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Single-Phase_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

    model.param().set("omega", "0.25*pi[rad/s]");
    model.param().descr("omega", "\u89d2\u901f\u5ea6");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.02, 0.04});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{0.008, 0.003});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{0, 0.014});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new double[]{0.001, 0.023});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new double[]{0, 0.017});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("r2", "r3");
    model.component("comp1").geom("geom1").feature("dif1").set("intbnd", false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1e3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"1e-3"});

    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("SwirlFlow", true);
    model.component("comp1").physics("spf").prop("ShapeProperty").set("order_fluid", 2);
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 1);
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(3, 4, 5, 7);
    model.component("comp1").physics("spf").feature("wallbc2").set("SlidingWall", true);
    model.component("comp1").physics("spf").feature("wallbc2").set("vw", "omega*r");
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("spf").feature("sym1").selection().set(6);
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(8);

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "omega", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "rad/s", 0);
    model.study("std1").feature("stat").setIndex("pname", "omega", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "rad/s", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "0.25*pi 0.5*pi 2*pi 4*pi", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("dataisaxisym", "off");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u4e09\u7ef4\u901f\u5ea6 (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("unit", "mm/s");
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowcountactive", true);
    model.result("pg1").feature("str1").set("arrowcount", 100);
    model.result("pg1").feature("str1").set("color", "gray");
    model.result("pg1").feature("str1").set("udist", 0.04);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("colorlegend", false);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 3, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 4, 0);
    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("\u65b9\u4f4d\u89d2\u901f\u5ea6");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").run();
    model.result("pg4").create("con1", "Contour");
    model.result("pg4").run();
    model.result("pg4").feature("con1").set("expr", "v");
    model.result("pg4").feature("con1").set("descr", "\u901f\u5ea6\u573a\uff0cphi \u5206\u91cf");
    model.result("pg4").feature("con1").set("number", 15);
    model.result("pg4").feature("con1").set("coloring", "uniform");
    model.result("pg4").feature("con1").set("color", "gray");
    model.result("pg4").feature("con1").set("resolution", "finer");
    model.result("pg4").feature("con1").set("recover", "pprint");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("colorlegend", false);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 2, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 3, 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 4, 0);
    model.result("pg4").run();

    model.sol("sol1").copySolution("sol2");
    model.sol("sol2").label("\u5c42\u6d41\u65cb\u6d41");

    model.component("comp1").physics("spf").prop("TurbulenceModelProperty").set("TurbulenceModelType", "RANS");
    model.component("comp1").physics("spf").prop("ShapeProperty").set("order_fluid", 1);

    model.component("comp1").mesh("mesh1").autoMeshSize(4);

    model.study("std1").feature("stat").setIndex("plistarr", "range(pi*100,pi*200,pi*500)", 0);
    model.study("std1").feature("stat").set("pcontinuationmode", "no");
    model.study("std1").feature("stat").set("preusesol", "yes");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("planetype", "general");
    model.result().dataset("cpl1").setIndex("genpoints", 0, 1, 0);
    model.result().dataset("cpl1").setIndex("genpoints", 1, 1, 2);
    model.result().dataset("cpl1").setIndex("genpoints", "cos(-90[deg])", 2, 0);
    model.result().dataset("cpl1").setIndex("genpoints", "sin(-90[deg])", 2, 1);
    model.result().dataset().duplicate("cpl2", "cpl1");
    model.result().dataset("cpl2").setIndex("genpoints", "cos(135[deg])", 2, 0);
    model.result().dataset("cpl2").setIndex("genpoints", "sin(135[deg])", 2, 1);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("colortable", "GrayScale");
    model.result("pg3").feature("surf1").set("colorlegend", false);
    model.result("pg3").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg3").run();
    model.result("pg3").create("str1", "StreamlineSurface");
    model.result("pg3").feature("str1").set("revcoordsys", "cylindrical");
    model.result("pg3").feature("str1").set("data", "cpl1");
    model.result("pg3").feature("str1").setIndex("looplevel", 1, 0);
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("linetype", "tube");
    model.result("pg3").feature("str1").set("tuberadiusscaleactive", true);
    model.result("pg3").feature("str1").set("tuberadiusscale", "1e-4");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowcountactive", true);
    model.result("pg3").feature("str1").set("arrowcount", 100);
    model.result("pg3").feature("str1").set("arrowscaleactive", true);
    model.result("pg3").feature("str1").set("arrowscale", 0.018);
    model.result("pg3").feature("str1").create("col1", "Color");
    model.result("pg3").run();
    model.result("pg3").feature("str1").feature("col1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg3").run();
    model.result("pg3").feature("str1").create("filt1", "Filter");
    model.result("pg3").run();
    model.result("pg3").feature("str1").feature("filt1").set("expr", "rev1y<0[m]");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("revcoordsys", "cylindrical");
    model.result("pg3").feature("arws1").set("data", "cpl2");
    model.result("pg3").feature("arws1").setIndex("looplevel", 1, 0);
    model.result("pg3").feature("arws1").set("scaleactive", true);
    model.result("pg3").feature("arws1").set("scale", 0.02);
    model.result("pg3").feature("arws1").set("arrowcount", 300);
    model.result("pg3").feature("arws1").set("inheritplot", "str1");
    model.result("pg3").feature("arws1").create("col1", "Color");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("arws1").create("filt1", "Filter");
    model.result("pg3").run();
    model.result("pg3").feature("arws1").feature("filt1").set("expr", "rev1y>0[m]");
    model.result("pg1").run();
    model.result().duplicate("pg5", "pg1");
    model.result("pg5").run();
    model.result("pg5").label("\u6e4d\u6d41\u9ecf\u5ea6");
    model.result("pg5").run();
    model.result("pg5").feature("str1").set("uadv", "manual");
    model.result("pg5").feature("str1").set("usatfactor", "1.0");
    model.result("pg5").feature("str1").set("udistend", 0.25);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "spf.muT");
    model.result("pg5").feature("surf1").set("descr", "\u6e4d\u6d41\u52a8\u529b\u9ecf\u5ea6");
    model.result("pg5").feature("surf1").set("colorlegend", true);
    model.result("pg5").feature("surf1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg3").run();

    model.title("\u65cb\u8f6c\u76d8\u5468\u56f4\u7684\u6da1\u6d41");

    model
         .description("\u6da1\u6d41\u662f\u4e00\u4e2a\u8003\u8651\u7ed5\u8f74\u7a33\u5b9a\u65cb\u8f6c\u6d41\u7684\u9009\u9879\u3002\u201cCFD \u6a21\u5757\u201d\u63d0\u4f9b\u4e86\u4e00\u4e2a\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u7269\u7406\u573a\u63a5\u53e3\uff0c\u5176\u4e2d\u65cb\u8f6c\u65b9\u5411\u7684\u6d41\u52a8\u4ecd\u7136\u5305\u542b\u5728\u65b9\u7a0b\u4e2d\uff0c\u800c\u4e0d\u662f\u5728\u4e09\u7ef4\u4e2d\u5bf9\u8be5\u8fc7\u7a0b\u8fdb\u884c\u5efa\u6a21\u3002\u672c\u4f8b\u6f14\u793a\u65cb\u8f6c\u5706\u67f1\u4f53\u5bf9\u5bb9\u5668\u5185\u6d41\u52a8\u7684\u5f71\u54cd\u3002\u8fd9\u79cd\u5e94\u7528\u901a\u5e38\u7528\u4e8e\u5316\u5b66\u52a8\u529b\u5b66\u5b9e\u9a8c\u88c5\u7f6e\uff0c\u79f0\u4e3a\u65cb\u8f6c\u5706\u76d8\u7535\u6781\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("rotating_disk.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
