/*
 * kirsch_plate.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:24 by COMSOL 6.3.0.290. */
public class kirsch_plate {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("pw", "10[m]");
    model.param().descr("pw", "\u65e0\u9650\u5143\u57df\u7684\u7269\u7406\u5bbd\u5ea6");
    model.param().set("deltaY", "0.1[m]");
    model.param().descr("deltaY", "\u65e0\u9650\u5143\u5c42\u7684\u51e0\u4f55\u539a\u5ea6");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"1", "1+deltaY"});
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r1").set("layertop", true);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "deltaY", 0);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 0.1);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.func().create("an1", "Analytic");
    model.func("an1").label("\u89e3\u6790\u5e94\u529b");
    model.func("an1").set("funcname", "AnaStress");
    model.func("an1").set("expr", "1000/2*(2+(0.1/y)^2+3*(0.1/y)^4)");
    model.func("an1").set("args", "y");
    model.func("an1").setIndex("argunit", "m", 0);
    model.func("an1").set("fununit", "N/m^2");
    model.component("comp1").func().create("an2", "Analytic");
    model.component("comp1").func("an2").set("expr", "(pw-10*deltaY)*x^2+10*deltaY*x");
    model.component("comp1").func("an2").setIndex("argunit", "m", 0);
    model.component("comp1").func("an2").set("fununit", "m");

    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").coordSystem("ie1").selection().set(2);
    model.component("comp1").coordSystem("ie1").set("stretchingType", "userDefined");
    model.component("comp1").coordSystem("ie1").set("function", "an2");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("ym", "if(dom==2,ie1.Ym,y)");
    model.component("comp1").variable("var1").descr("ym", "\u7269\u7406 y \u5750\u6807");

    model.component("comp1").physics("solid").selection().set(1);
    model.component("comp1").physics("solid").prop("Type2D").set("Type2D", "PlaneStress");
    model.component("comp1").physics("solid").prop("d").set("d", 0.1);
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 1);
    model.component("comp1").physics("solid").feature("sym1").selection().set(1, 5);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(6);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"1e3", "0", "0"});
    model.component("comp1").physics().create("solid2", "SolidMechanics", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/solid2", true);

    model.component("comp1").physics("solid2").prop("Type2D").set("Type2D", "PlaneStress");
    model.component("comp1").physics("solid2").prop("d").set("d", 0.1);
    model.component("comp1").physics("solid2").create("sym1", "SymmetrySolid", 1);
    model.component("comp1").physics("solid2").feature("sym1").selection().set(1, 2, 5);
    model.component("comp1").physics("solid2").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid2").feature("bndl1").selection().set(6, 7);
    model.component("comp1").physics("solid2").feature("bndl1")
         .set("forceReferenceArea", new String[]{"1e3", "0", "0"});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"2.1e11"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7800"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 0.02);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

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
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u5e94\u529b (solid2)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid2.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("resolution", "normal");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u2", "v2"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").label("\u8bef\u5dee\u8ba1\u7b97");
    model.result().numerical("pev1").selection().set(1, 2);
    model.result().numerical("pev1").setIndex("expr", "(solid.sx-AnaStress(y))/AnaStress(y)", 0);
    model.result().numerical("pev1").setIndex("unit", "", 0);
    model.result().numerical("pev1").setIndex("descr", "\u6709\u9650\u677f\u4e2d\u7684\u8bef\u5dee", 0);
    model.result().numerical("pev1").setIndex("expr", "(solid2.sx-AnaStress(y))/AnaStress(y)", 1);
    model.result().numerical("pev1").setIndex("unit", "", 1);
    model.result().numerical("pev1").setIndex("descr", "\u65e0\u9650\u677f\u4e2d\u7684\u8bef\u5dee", 1);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8bef\u5dee\u8ba1\u7b97");
    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").setResult();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "solid.sGpxx");
    model.result("pg1").feature("surf1").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0cxx \u5206\u91cf");
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature().remove("def");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "solid2.sGpxx");
    model.result("pg2").feature("surf1").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0cxx \u5206\u91cf");
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature().remove("def");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u5e94\u529b\u66f2\u7ebf");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").selection().set(1, 2);
    model.result("pg3").feature("lngr1").set("expr", "AnaStress(ym)");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "ym");
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").set("legendmethod", "manual");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").setIndex("legends", "\u89e3\u6790\u503c", 0);
    model.result("pg3").feature("lngr1").set("linestyle", "dashed");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("lngr2", "LineGraph");
    model.result("pg3").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr2").set("linewidth", "preference");
    model.result("pg3").feature("lngr2").selection().set(1);
    model.result("pg3").feature("lngr2").set("expr", "solid.sGpxx");
    model.result("pg3").feature("lngr2").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0cxx \u5206\u91cf");
    model.result("pg3").feature("lngr2").set("xdata", "expr");
    model.result("pg3").feature("lngr2").set("xdataexpr", "y");
    model.result("pg3").feature("lngr2").set("legend", true);
    model.result("pg3").feature("lngr2").set("legendmethod", "manual");
    model.result("pg3").feature("lngr2").setIndex("legends", "\u6709\u9650\u677f", 0);
    model.result("pg3").run();
    model.result("pg3").create("lngr3", "LineGraph");
    model.result("pg3").feature("lngr3").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr3").set("linewidth", "preference");
    model.result("pg3").feature("lngr3").selection().set(1, 2);
    model.result("pg3").feature("lngr3").set("expr", "solid2.sGpxx");
    model.result("pg3").feature("lngr3").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0cxx \u5206\u91cf");
    model.result("pg3").feature("lngr3").set("xdata", "expr");
    model.result("pg3").feature("lngr3").set("xdataexpr", "ym");
    model.result("pg3").feature("lngr3").set("legend", true);
    model.result("pg3").feature("lngr3").set("legendmethod", "manual");
    model.result("pg3").feature("lngr3").setIndex("legends", "\u65e0\u9650\u677f", 0);
    model.result("pg3").run();
    model.result("pg3").set("axislimits", true);
    model.result("pg3").set("xmin", 0);
    model.result("pg3").set("xmax", 1.5);
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u7269\u7406 y \u5750\u6807 (m)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u5e94\u529b (N/m\u00b2)");
    model.result("pg3").run();

    model.title("Kirsch \u65e0\u9650\u5927\u5e73\u677f\u95ee\u9898");

    model
         .description("\u8fd9\u4e2a\u9759\u6001\u57fa\u51c6\u6a21\u578b\u8ba1\u7b97\u65e0\u9650\u5927\u5e73\u677f\u4e0a\u4e00\u4e2a\u5c0f\u5b54\u9644\u8fd1\u7684\u5e94\u529b\u5206\u5e03\uff0c\u5e76\u5c06\u7ed3\u679c\u4e0e\u89e3\u6790\u89e3\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.label("kirsch_plate.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
