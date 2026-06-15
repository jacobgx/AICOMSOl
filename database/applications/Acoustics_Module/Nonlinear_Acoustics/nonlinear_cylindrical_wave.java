/*
 * nonlinear_cylindrical_wave.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class nonlinear_cylindrical_wave {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Nonlinear_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("nate", "NonlinearPressureAcousticsTimeExplicit", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/nate", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("P0", "0.1", "\u6e90\u538b\u529b\u5e45\u503c");
    model.param().set("beta", "1", "\u975e\u7ebf\u6027\u7cfb\u6570");
    model.param().set("N", "5", "\u8981\u89e3\u6790\u7684\u8c10\u6ce2\u6570");
    model.param().set("r0", "1", "\u5185\u534a\u5f84");
    model.param().set("r_sh", "r0*(1 + 1/(4*pi*beta*P0*r0))^2", "\u6fc0\u6ce2\u5f62\u6210\u534a\u5f84");
    model.param().set("a", "0.7", "\u76f8\u5bf9\u51b2\u51fb\u8ddd\u79bb");
    model.param().set("tau", "0", "\u5ef6\u8fdf\u65f6\u95f4");

    model.baseSystem("none");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "r0");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 45);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").feature().duplicate("c2", "c1");
    model.component("comp1").geom("geom1").feature("c2").set("r", "5*r0");
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("c2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("nate").prop("Limiter").set("Limiter", "WENO");
    model.component("comp1").physics("nate").prop("ShapeProperty").set("shapeorder", 1);
    model.component("comp1").physics("nate").feature("natem1").set("c_mat", "userdef");
    model.component("comp1").physics("nate").feature("natem1").set("c", 1);
    model.component("comp1").physics("nate").feature("natem1").set("rho_mat", "userdef");
    model.component("comp1").physics("nate").feature("natem1").set("rho", 1);
    model.component("comp1").physics("nate").feature("natem1").set("CoefficientOfNonlinearity", "UserDefined");
    model.component("comp1").physics("nate").feature("natem1").set("beta", "beta");
    model.component("comp1").physics("nate").create("pr1", "Pressure", 1);
    model.component("comp1").physics("nate").feature("pr1").selection().set(3);
    model.component("comp1").physics("nate").feature("pr1").set("p0", "P0*sin(2*pi*t)");
    model.component("comp1").physics("nate").create("imp1", "Impedance", 1);
    model.component("comp1").physics("nate").feature("imp1").selection().set(4);
    model.component("comp1").physics("nate").feature("imp1").set("PressureVelocityRelation", "SecondOrder");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "1/10/N");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u6570\u503c");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("erkorder", 3);
    model.sol("sol1").feature("t1").set("updtstep", "manual");

    model.study("std1").feature("time").set("tlist", "range(0, 1/50, 5)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u58f0\u538b (nate)");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature("surf1").set("resolution", "custom");
    model.result("pg1").feature("surf1").set("refine", 6);
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u58f0\u901f (nate)");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("expr", "nate.v_inst");
    model.result("pg2").feature("surf1").set("resolution", "custom");
    model.result("pg2").feature("surf1").set("refine", 6);
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").label("\u9762\u4e0a\u7bad\u5934");
    model.result("pg2").feature("arws1").set("color", "white");
    model.result("pg2").feature("arws1").set("data", "parent");
    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u6cbf\u5f84\u5411\u7ebf\u7684\u58f0\u538b");
    model.result("pg3").setIndex("looplevelinput", "last", 0);
    model.result("pg3").label("\u58f0\u538b\uff0c\u7ebf");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").selection().set(2);
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").set("linewidth", 2);
    model.result("pg3").feature().duplicate("lngr2", "lngr1");
    model.result("pg3").run();
    model.result("pg3").feature("lngr2").set("xdataexpr", "r_sh");
    model.result("pg3").run();
    model.result("pg3").feature("lngr2").set("legend", true);
    model.result("pg3").feature("lngr2").set("legendmethod", "manual");
    model.result("pg3").feature("lngr2").setIndex("legends", "\u6fc0\u6ce2\u5f62\u6210\u8ddd\u79bb", 0);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u58f0\u538b\uff0c\u9891\u8c31");
    model.result("pg4").setIndex("looplevelinput", "interp", 0);
    model.result("pg4").setIndex("interp", "range(4, 1/50, 5)", 0);
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u58f0\u538b\uff0c\u9891\u8c31");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").selection().set(2);
    model.result("pg4").feature("ptgr1").set("xdata", "fourier");
    model.result("pg4").feature("ptgr1").set("fouriershow", "spectrum");
    model.result("pg4").feature("ptgr1").set("scale", "multiplyperiod");
    model.result("pg4").feature("ptgr1").set("freqrangeactive", true);
    model.result("pg4").feature("ptgr1").set("freqmax", "N");
    model.result("pg4").feature("ptgr1").set("linestyle", "none");
    model.result("pg4").feature("ptgr1").set("linemarker", "point");
    model.result("pg4").feature("ptgr1").set("legend", true);
    model.result("pg4").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg4").feature("ptgr1").setIndex("legends", "\u5185\u90e8\u8fb9\u754c", 0);
    model.result("pg4").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr2").selection().set(4);
    model.result("pg4").feature("ptgr2").set("linemarker", "asterisk");
    model.result("pg4").feature("ptgr2").set("markerpos", "interp");
    model.result("pg4").feature("ptgr2").setIndex("legends", "\u5916\u90e8\u8fb9\u754c", 0);
    model.result("pg4").run();

    model.component("comp1").physics().create("ge", "GlobalEquations", "geom1");

    model.study("std1").feature("time").setSolveFor("/physics/ge", false);

    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "pa", 0, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "pa - P0*sqrt(r0/(a*r_sh))*sin(2*pi*(tau + 2*(sqrt(a*r_sh*r0) - r0)*beta*pa))", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "\u89e3\u6790\u89e3", 0, 0);

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/nate", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ge", true);
    model.study("std2").label("\u7814\u7a76 2 - \u89e3\u6790");
    model.study("std2").setGenPlots(false);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "P0", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "", 0);
    model.study("std2").feature("param").setIndex("pname", "P0", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "", 0);
    model.study("std2").feature("param").setIndex("pname", "tau", 0);
    model.study("std2").feature("param").setIndex("plistarr", "range(0, 1/50, 5)", 0);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("cpt1", "CutPoint2D");
    model.result().dataset("cpt1").set("pointx", "a*r_sh");
    model.result().dataset("cpt1").set("pointy", 0);
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u58f0\u538b\uff0c\u70b9");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u70b9\u4e0a\u7684\u58f0\u538b");
    model.result("pg5").set("data", "none");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").set("data", "cpt1");
    model.result("pg5").feature("ptgr1").set("linewidth", 2);
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr1").setIndex("legends", "\u6570\u503c\u89e3", 0);
    model.result("pg5").run();
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").set("data", "dset2");
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "tau + (a*r_sh - r0)");
    model.result("pg5").feature("glob1").set("linewidth", 2);
    model.result("pg5").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 51, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 101, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 151, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 201, 0);
    model.result("pg1").run();
    model.result("pg1").stepLast(0);
    model.result("pg1").run();

    model.title("\u67f1\u9762\u6ce2\u7684\u975e\u7ebf\u6027\u4f20\u64ad - \u9a8c\u8bc1\u6a21\u578b");

    model
         .description("\u672c\u6a21\u578b\u793a\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528 COMSOL Multiphysics \u7684\u201c\u58f0\u5b66\u6a21\u5757\u201d\u63d0\u4f9b\u7684\u201c\u975e\u7ebf\u6027\u538b\u529b\u58f0\u5b66\uff0c\u65f6\u57df\u663e\u5f0f\u201d\u7269\u7406\u573a\u63a5\u53e3\u5bf9\u67f1\u9762\u6ce2\u7684\u975e\u7ebf\u6027\u4f20\u64ad\u8fdb\u884c\u5efa\u6a21\u3002\u8be5\u63a5\u53e3\u4f7f\u7528\u65f6\u57df\u663e\u5f0f\u95f4\u65ad\u4f3d\u8fbd\u91d1\u6709\u9650\u5143\u6cd5\uff0c\u5b9e\u73b0\u4e86\u5177\u6709\u53cc\u66f2\u578b\u5b88\u6052\u5f8b\u5f62\u5f0f\u7684\u975e\u7ebf\u6027\u58f0\u5b66\u65b9\u7a0b\u7ec4\u3002\n\n\u672c\u4f8b\u5206\u6790\u6ce2\u5728\u65e0\u635f\u4ecb\u8d28\u4e2d\u7684\u4f20\u64ad\uff0c\u5176\u4f20\u64ad\u8ddd\u79bb\u5927\u4e8e\u6fc0\u6ce2\u5f62\u6210\u8ddd\u79bb\u3002\u56e0\u6b64\uff0c\u672c\u6559\u7a0b\u7279\u522b\u5f3a\u8c03\u5904\u7406\u89e3\u4e0d\u8fde\u7eed\u6240\u9700\u7684\u6280\u672f\uff0c\u4f8b\u5982\u9650\u5236\u5668\u3001\u79bb\u6563\u5316\u548c\u6c42\u89e3\u5668\u8bbe\u7f6e\uff0c\u5e76\u5c06\u6570\u503c\u89e3\u4e0e\u5728\u6fc0\u6ce2\u5f62\u6210\u524d\u7684\u8ddd\u79bb\u5904\u6709\u6548\u7684\u89e3\u6790\u89e3\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("nonlinear_cylindrical_wave.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
