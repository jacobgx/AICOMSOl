/*
 * cylinder_flow.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:19 by COMSOL 6.3.0.290. */
public class cylinder_flow {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Fluid_Dynamics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);

    model.param().set("H", "0.41[m]");
    model.param().descr("H", "\u901a\u9053\u9ad8\u5ea6");
    model.param().set("L", "2.2[m]");
    model.param().descr("L", "\u901a\u9053\u957f\u5ea6");
    model.param().set("W", "1[m]");
    model.param().descr("W", "\u901a\u9053\u5bbd\u5ea6\uff08\u672a\u5efa\u6a21\uff09");
    model.param().set("R", "0.05[m]");
    model.param().descr("R", "\u5706\u67f1\u534a\u5f84");
    model.param().set("U_mean", "1[m/s]");
    model.param().descr("U_mean", "\u5e73\u5747\u6d41\u5165\u901f\u5ea6");

    model.func().create("step1", "Step");
    model.func("step1").set("location", 0.05);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L", "H"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"4*R", "4*R"});
    model.component("comp1").geom("geom1").feature("c1").set("r", "R");
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"1e-3"});

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(1);
    model.component("comp1").physics("spf").feature("inl1").set("U0in", "6*U_mean*y*(H-y)/H^2*step1(t[1/s])");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(4);

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.2,3.4) range(3.5,0.02,7)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "intermediate");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
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
    model.result("pg1").run();
    model.result("pg1").create("ptr1", "ParticleMass");
    model.result("pg1").feature("ptr1").set("mass", "4*pi/3*1e-9");
    model.result("pg1").feature("ptr1").set("velstartx", "u");
    model.result("pg1").feature("ptr1").set("velstarty", "v");
    model.result("pg1").feature("ptr1").set("ycoord", "range(0.05,0.025,0.35)");
    model.result("pg1").feature("ptr1").set("xcoord", 0);
    model.result("pg1").feature("ptr1").set("dropmethod", "freq");
    model.result("pg1").feature("ptr1").set("tstartactive", true);
    model.result("pg1").feature("ptr1").set("tstart", 3.2);
    model.result("pg1").feature("ptr1").set("dropfreq", 0.2);
    model.result("pg1").feature("ptr1").set("linetype", "none");
    model.result("pg1").feature("ptr1").set("pointtype", "point");
    model.result("pg1").feature("ptr1").set("sphereradiusscaleactive", true);
    model.result("pg1").feature("ptr1").set("sphereradiusscale", 7.5);
    model.result("pg1").feature("ptr1").set("pointcolor", "custom");
    model.result("pg1").feature("ptr1")
         .set("custompointcolor", new double[]{0.501960813999176, 0.501960813999176, 0.501960813999176});
    model.result("pg1").feature("ptr1").set("pointdom", "disappear");
    model.result("pg1").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("maxframes", 50);
    model.result().export("anim1").run();
    model.result().dataset().create("int1", "Integral");
    model.result().dataset("int1").set("intsurface", true);
    model.result().dataset("int1").set("intvolume", true);
    model.result().dataset("int1").set("showlevel", "off");
    model.result().dataset("int1").selection().geom("geom1", 1);
    model.result().dataset("int1").selection().set(5, 6, 7, 8);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u5347\u529b\u7cfb\u6570");
    model.result("pg3").set("data", "int1");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").set("expr", "-2*reacf(v)[N/m^2]*W/(spf.rho*U_mean^2*(2*R*W))");
    model.result("pg3").feature("ptgr1").set("descractive", true);
    model.result("pg3").feature("ptgr1").set("descr", "\u5347\u529b\u7cfb\u6570");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u66f3\u529b\u7cfb\u6570");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr1").set("expr", "-2*reacf(u)[N/m^2]*W/(spf.rho*U_mean^2*(2*R*W))");
    model.result("pg4").feature("ptgr1").set("descr", "\u66f3\u529b\u7cfb\u6570");
    model.result("pg4").run();
    model.result("pg1").run();

    model.title("\u5706\u67f1\u7ed5\u6d41");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u6d41\u4f53\u7ed5\u8fc7\u5706\u67f1\u65f6\u7684\u77ac\u6001\u6d41\u52a8\u3002\u96f7\u8bfa\u6570\u7ea6\u4e3a\u00a0100\uff0c\u8be5\u503c\u5c0f\u5230\u8db3\u4ee5\u4f7f\u6d41\u52a8\u4fdd\u6301\u5c42\u6d41\u72b6\u6001\uff0c\u4f46\u5df2\u8db3\u591f\u5f62\u6210\u5361\u95e8\u6da1\u8857\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("cylinder_flow.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
