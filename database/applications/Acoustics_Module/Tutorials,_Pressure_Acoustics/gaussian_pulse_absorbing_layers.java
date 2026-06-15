/*
 * gaussian_pulse_absorbing_layers.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class gaussian_pulse_absorbing_layers {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Tutorials,_Pressure_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("cwe", "ConvectedWaveEquation", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/cwe", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("W", "100", "\u57df\u5bbd\u5ea6");
    model.param().set("dW", "20", "\u5438\u6536\u5c42\u5bbd\u5ea6");
    model.param().set("c0", "1", "\u58f0\u901f");
    model.param().set("rho0", "1", "\u80cc\u666f\u5bc6\u5ea6");
    model.param().set("p0", "1[atm]/(c0^2*rho0)", "\u80cc\u666f\u538b\u529b");
    model.param().set("alpha", "log(2)/9", "\u9ad8\u65af\u8109\u51b2\u5bbd\u5ea6");
    model.param().set("beta", "0.04", "\u9ad8\u65af\u8109\u51b2\u901f\u5ea6\u56e0\u5b50");
    model.param().set("Ma", "0.5", "\u9a6c\u8d6b\u6570");

    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", "W");
    model.component("comp1").geom("geom1").feature("sq1").set("base", "center");
    model.component("comp1").geom("geom1").feature("sq1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("sq1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("sq1").set("layertop", true);
    model.component("comp1").geom("geom1").feature("sq1").setIndex("layer", "dW", 0);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("G", "exp(-alpha*(x^2+y^2))", "\u9ad8\u65af\u51fd\u6570");
    model.component("comp1").variable("var1")
         .set("u_i", "c0*beta*x*G", "x \u5411\u901f\u5ea6\u521d\u59cb\u6761\u4ef6");
    model.component("comp1").variable("var1")
         .set("v_i", "c0*beta*y*G", "y \u5411\u901f\u5ea6\u521d\u59cb\u6761\u4ef6");
    model.component("comp1").variable("var1").set("p_i", "rho0*c0^2*G", "\u538b\u529b\u521d\u59cb\u6761\u4ef6");
    model.component("comp1").variable("var1")
         .set("r", "sqrt((x-Ma*t)^2+y^2)", "\u8ddd\u79bb\u53d8\u91cf\uff08\u52a8\u5750\u6807\u7cfb\uff09");
    model.component("comp1").variable("var1")
         .set("p_a", "1/(2*alpha)*integrate((cos(L*t)-beta/(2*alpha)*L*sin(L*t))*L*besselj(0,L*r)*exp(-L^2/(4*alpha)),L,0,10,1e-3)", "\u89e3\u6790\u538b\u529b\u8868\u8fbe\u5f0f");

    model.component("comp1").coordSystem().create("ab1", "AbsorbingLayer");
    model.component("comp1").coordSystem("ab1").selection().set(1, 2, 3, 4, 6, 7, 8, 9);

    model.baseSystem("none");

    model.component("comp1").physics("cwe").feature("cwem1").set("minput_pressure", "p0");
    model.component("comp1").physics("cwe").feature("cwem1").set("minput_velocity", new String[]{"Ma*c0", "0", "0"});
    model.component("comp1").physics("cwe").feature("cwem1").set("rho0_mat", "userdef");
    model.component("comp1").physics("cwe").feature("cwem1").set("rho0", "rho0");
    model.component("comp1").physics("cwe").feature("cwem1").set("c0_mat", "userdef");
    model.component("comp1").physics("cwe").feature("cwem1").set("c0", "c0");
    model.component("comp1").physics("cwe").feature("init1").set("p", "p_i");
    model.component("comp1").physics("cwe").feature("init1").set("u", new String[]{"u_i", "v_i", "0"});
    model.component("comp1").physics("cwe").create("imp1", "AcousticImpedance", 1);
    model.component("comp1").physics("cwe").feature("imp1").selection().all();

    model.component("comp1").mesh("mesh1").autoMeshSize(3);

    model.study("std1").feature("time").set("tlist", "range(0,1,120)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u58f0\u538b (cwe)");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature("surf1").set("resolution", "custom");
    model.result("pg1").feature("surf1").set("refine", 6);
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u58f0\u901f (cwe)");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "cwe.v_inst");
    model.result("pg2").feature("surf1").set("unit", "m/s");
    model.result("pg2").feature("surf1").set("resolution", "custom");
    model.result("pg2").feature("surf1").set("refine", 6);
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("color", "white");
    model.result("pg2").feature("arws1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u80cc\u666f\u5e73\u5747\u6d41 (cwe)");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "cwe.p0");
    model.result("pg3").feature("surf1").set("resolution", "custom");
    model.result("pg3").feature("surf1").set("refine", 6);
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature().create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("expr", new String[]{"cwe.u0x", "cwe.u0y"});
    model.result("pg3").feature("arws1").set("xnumber", 7);
    model.result("pg3").feature("arws1").set("ynumber", 7);
    model.result("pg3").feature("arws1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 11, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 21, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 41, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 121, 0);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 11, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 21, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 31, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 121, 0);
    model.result("pg2").run();
    model.result().dataset().create("cpt1", "CutPoint2D");
    model.result().dataset("cpt1").set("pointx", 20);
    model.result().dataset("cpt1").set("pointy", 10);
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", -50, 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", 50, 1, 0);
    model.result().dataset().create("cln2", "CutLine2D");
    model.result().dataset("cln2").setIndex("genpoints", -30, 0, 0);
    model.result().dataset("cln2").setIndex("genpoints", 30, 1, 0);
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u70b9\u4e0a\u7684\u538b\u529b");
    model.result("pg4").set("data", "cpt1");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").set("legend", true);
    model.result("pg4").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg4").feature("ptgr1").setIndex("legends", "COMSOL \u6a21\u578b", 0);
    model.result("pg4").run();
    model.result("pg4").create("ptgr2", "PointGraph");
    model.result("pg4").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr2").set("linewidth", "preference");
    model.result("pg4").feature("ptgr2").set("expr", "p_a");
    model.result("pg4").feature("ptgr2").set("legend", true);
    model.result("pg4").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg4").feature("ptgr2").setIndex("legends", "\u89e3\u6790\u6a21\u578b", 0);
    model.result("pg4").feature("ptgr2").set("linestyle", "none");
    model.result("pg4").feature("ptgr2").set("linemarker", "point");
    model.result("pg4").feature("ptgr2").set("markerpos", "interp");
    model.result("pg4").feature("ptgr2").set("markers", 100);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u622a\u7ebf\u4e0a\u7684\u538b\u529b");
    model.result("pg5").set("data", "none");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").set("data", "cln1");
    model.result("pg5").feature("lngr1").setIndex("looplevelinput", "manual", 0);
    model.result("pg5").feature("lngr1").setIndex("looplevel", new int[]{41}, 0);
    model.result("pg5").feature("lngr1").set("expr", "p");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "manual");
    model.result("pg5").feature("lngr1").setIndex("legends", "COMSOL \u6a21\u578b", 0);
    model.result("pg5").feature("lngr1").set("resolution", "extrafine");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").create("lngr2", "LineGraph");
    model.result("pg5").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr2").set("linewidth", "preference");
    model.result("pg5").feature("lngr2").set("data", "cln2");
    model.result("pg5").feature("lngr2").setIndex("looplevelinput", "manual", 0);
    model.result("pg5").feature("lngr2").setIndex("looplevel", new int[]{41}, 0);
    model.result("pg5").feature("lngr2").set("expr", "p_a");
    model.result("pg5").feature("lngr2").set("xdata", "expr");
    model.result("pg5").feature("lngr2").set("xdataexpr", "x");
    model.result("pg5").feature("lngr2").set("linestyle", "none");
    model.result("pg5").feature("lngr2").set("linemarker", "point");
    model.result("pg5").feature("lngr2").set("markerpos", "interp");
    model.result("pg5").feature("lngr2").set("markers", 100);
    model.result("pg5").feature("lngr2").set("legend", true);
    model.result("pg5").feature("lngr2").set("legendmethod", "manual");
    model.result("pg5").feature("lngr2").setIndex("legends", "\u89e3\u6790\u6a21\u578b", 0);
    model.result("pg5").feature("lngr2").set("resolution", "extrafine");
    model.result("pg5").run();
    model.result("pg1").run();
    model.result().duplicate("pg6", "pg1");
    model.result("pg6").run();
    model.result("pg6").label("\u58f0\u538b (cwe) \u9009\u62e9");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").create("sel1", "Selection");
    model.result("pg6").feature("surf1").feature("sel1").selection().set(5);
    model.result("pg6").run();

    model
         .title("\u5747\u5300\u6d41\u4e2d\u7684\u9ad8\u65af\u8109\u51b2\u4e8c\u7ef4\u6a21\u578b\uff1a\u5bf9\u6d41\u6ce2\u52a8\u65b9\u7a0b\u548c\u5438\u6536\u5c42");

    model
         .description("\u8fd9\u4e00\u5c0f\u578b\u6559\u7a0b\u6a21\u62df\u4e86\u5c06\u65e0\u53cd\u5c04\u6761\u4ef6\u548c\u6d77\u7ef5\u5c42\u7528\u4e8e\u7ebf\u6027\u6b27\u62c9\u5f0f\u7cfb\u7edf\u7684\u4e00\u4e2a\u6807\u51c6\u6d4b\u8bd5\u548c\u57fa\u51c6\u6a21\u578b\uff0c\u5206\u6790\u4e86\u77ac\u6001\u9ad8\u65af\u8109\u51b2\u5728\u4e8c\u7ef4\u5747\u5300\u6d41\u4e2d\u7684\u4f20\u64ad\u3002\u201c\u5bf9\u6d41\u6ce2\u52a8\u65b9\u7a0b\uff0c\u65f6\u57df\u663e\u5f0f\u201d\u63a5\u53e3\u6c42\u89e3\u7edd\u70ed\u72b6\u6001\u65b9\u7a0b\u4e0b\u7684\u7ebf\u6027\u6b27\u62c9\u65b9\u7a0b\uff0c\u5e76\u4f7f\u7528\u201c\u5438\u6536\u5c42\u201d\u7279\u5f81\u6765\u6a21\u62df\u65e0\u9650\u57df\u3002\n\n\u5728\u8ba1\u7b97\u57df\u4e2d\u5fc3\u7531\u521d\u59cb\u9ad8\u65af\u5206\u5e03\u751f\u6210\u4e00\u4e2a\u58f0\u5b66\u8109\u51b2\uff0c\u7136\u540e\u5728\u9ad8\u9a6c\u8d6b\u6570\u7684\u5747\u5300\u6d41\u52a8\u4e2d\u4f20\u64ad\u3002\u6b64\u95ee\u9898\u7684\u89e3\u6790\u89e3\u7528\u4e8e\u9a8c\u8bc1\u8be5\u89e3\uff0c\u7ed3\u679c\u663e\u793a\u4e24\u8005\u975e\u5e38\u4e00\u81f4\u3002\n\n\u6b64\u6a21\u578b\u8fd8\u6f14\u793a\u5982\u4f55\u8bbe\u7f6e\u548c\u4f7f\u7528\u5438\u6536\u5c42\uff0c\u53ef\u4ee5\u5c06\u4f2a\u53cd\u5c04\u6ce2\u51cf\u5c11\u5230\u5165\u5c04\u573a\u5e45\u503c\u7684 1/1000\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("gaussian_pulse_absorbing_layers.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
