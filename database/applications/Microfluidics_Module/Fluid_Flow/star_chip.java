/*
 * star_chip.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:08 by COMSOL 6.3.0.290. */
public class star_chip {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Microfluidics_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .set("x", "325 325 30 0 0 -25 -25 -97 -115 -30 -150  -150 -30");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .set("y", "12.5 25 25 30 150 150 55 125 110 25 25 12.5 12.5");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").selection("input").set("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1")
         .set("pos", new double[]{82.5, 12.5});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mir1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input")
         .set("mir1", "pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 25, 0);
    model.component("comp1").geom("geom1").runPre("fin");

    model.param().set("p0", "50[Pa]");
    model.param().descr("p0", "\u538b\u529b\u504f\u79fb\u91cf");
    model.param().set("p1", "10[Pa]");
    model.param().descr("p1", "\u538b\u529b\u5e45\u503c");
    model.param().set("omega", "pi[rad/s]");
    model.param().descr("omega", "\u89d2\u901f\u5ea6");
    model.param().set("t", "0");
    model.param().descr("t", "\u7528\u4e8e\u7a33\u6001\u7814\u7a76\u7684\u865a\u62df\u53d8\u91cf");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("p_in_rm", "p0+p1*sin(omega*t)");
    model.component("comp1").variable("var1").descr("p_in_rm", "\u538b\u529b\uff0c\u6700\u53f3\u4fa7\u5165\u53e3");
    model.component("comp1").variable("var1").set("p_in_ir", "p0+p1*sin(omega*t+pi/4)");
    model.component("comp1").variable("var1").descr("p_in_ir", "\u538b\u529b\uff0c\u53f3\u5185\u4fa7\u5165\u53e3");
    model.component("comp1").variable("var1").set("p_in_c", "p0+p1*sin(omega*t+pi/2)");
    model.component("comp1").variable("var1").descr("p_in_c", "\u538b\u529b\uff0c\u4e2d\u592e\u5165\u53e3");
    model.component("comp1").variable("var1").set("p_in_il", "p0+p1*sin(omega*t+3*pi/4)");
    model.component("comp1").variable("var1").descr("p_in_il", "\u538b\u529b\uff0c\u5de6\u5185\u4fa7\u5165\u53e3");
    model.component("comp1").variable("var1").set("p_in_lm", "p0+p1*sin(omega*t+pi)");
    model.component("comp1").variable("var1").descr("p_in_lm", "\u538b\u529b\uff0c\u6700\u5de6\u4fa7\u5165\u53e3");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1000"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"1e-3"});

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().set(1, 5);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "Pressure");
    model.component("comp1").physics("spf").feature("inl1").set("p0", "p_in_c");
    model.component("comp1").physics("spf").create("inl2", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl2").selection().set(7);
    model.component("comp1").physics("spf").feature("inl2").set("BoundaryCondition", "Pressure");
    model.component("comp1").physics("spf").feature("inl2").set("p0", "p_in_ir");
    model.component("comp1").physics("spf").create("inl3", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl3").selection().set(9);
    model.component("comp1").physics("spf").feature("inl3").set("BoundaryCondition", "Pressure");
    model.component("comp1").physics("spf").feature("inl3").set("p0", "p_in_il");
    model.component("comp1").physics("spf").create("inl4", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl4").selection().set(14);
    model.component("comp1").physics("spf").feature("inl4").set("BoundaryCondition", "Pressure");
    model.component("comp1").physics("spf").feature("inl4").set("p0", "p_in_rm");
    model.component("comp1").physics("spf").create("inl5", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl5").selection().set(16);
    model.component("comp1").physics("spf").feature("inl5").set("BoundaryCondition", "Pressure");
    model.component("comp1").physics("spf").feature("inl5").set("p0", "p_in_lm");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().set(23, 24);

    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 8);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "range(0,0.1,4)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("\u5207\u9762");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection().set(2, 3, 4, 6, 8, 10, 11, 12, 13, 15, 17, 18, 19, 20, 21, 22);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").setIndex("looplevel", 41, 0);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "p");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 6, 0);
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").feature("slc1").set("unit", "mm/s");
    model.result("pg1").feature("slc1").set("quickplane", "xy");
    model.result("pg1").feature("slc1").set("quickznumber", 1);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").set("xnumber", 35);
    model.result("pg1").feature("arwv1").set("ynumber", 35);
    model.result("pg1").feature("arwv1").set("znumber", 1);
    model.result("pg1").feature("arwv1").set("arrowlength", "normalized");
    model.result("pg1").feature("arwv1").set("scaleactive", true);
    model.result("pg1").feature("arwv1").set("scale", 2600);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 6, 0);
    model.result("pg2").run();
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("pointx", 275);
    model.result().dataset("cpt1").set("pointy", 12.5);
    model.result().dataset("cpt1").set("pointz", 12.5);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("x \u65b9\u5411\u901f\u5ea6");
    model.result("pg3").set("data", "cpt1");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").set("data", "cpt1");
    model.result("pg3").feature("ptgr1").set("expr", "u");
    model.result("pg3").feature("ptgr1").set("descr", "\u901f\u5ea6\u573a\uff0cx \u5206\u91cf");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u51fa\u53e3\u538b\u529b");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr1").label("\u51fa\u53e3\u538b\u529b");
    model.result("pg4").feature("ptgr1").set("expr", "p");
    model.result("pg4").feature("ptgr1").set("descr", "\u538b\u529b");
    model.result("pg4").run();
    model.result("pg1").run();

    model.title("\u661f\u5f62\u5fae\u901a\u9053");

    model
         .description("\u672c\u4f8b\u5bf9\u4e00\u79cd\u53ef\u51b2\u6d17\u542b\u6d41\u4f53\u7684\u8bbe\u5907\u7684\u5fae\u901a\u9053\u8fdb\u884c\u77ac\u6001\u7814\u7a76\u3002\u5176\u4e2d\u4e94\u4e2a\u5165\u53e3\u4e0a\u65bd\u52a0\u7684\u538b\u529b\u968f\u65f6\u95f4\u5448\u6b63\u5f26\u53d8\u5316\uff0c\u7814\u7a76\u4e86\u51fa\u53e3\u5904\u7684\u901f\u5ea6\u77e2\u91cf\u3002\u672c\u4f8b\u4e2d\u4f7f\u7528\u4e86\u62c9\u4f38\u7684\u68f1\u67f1\u7f51\u683c\uff0c\u4ece\u800c\u51cf\u5c11\u4e86\u8ba1\u7b97\u65f6\u95f4\u548c\u5185\u5b58\u9700\u6c42\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("star_chip.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
