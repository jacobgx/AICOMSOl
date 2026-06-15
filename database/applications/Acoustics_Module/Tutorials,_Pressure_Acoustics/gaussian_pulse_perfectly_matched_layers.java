/*
 * gaussian_pulse_perfectly_matched_layers.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class gaussian_pulse_perfectly_matched_layers {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Tutorials,_Pressure_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("actd", "TransientPressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/actd", true);

    model.baseSystem("none");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("W", "70", "\u57df\u5bbd\u5ea6");
    model.param().set("dW", "9", "\u5b8c\u7f8e\u5339\u914d\u5c42\u5bbd\u5ea6");
    model.param().set("c0", "1", "\u58f0\u901f");
    model.param().set("rho0", "1", "\u80cc\u666f\u5bc6\u5ea6");
    model.param().set("p0", "1[atm]/(c0^2*rho0)", "\u80cc\u666f\u538b\u529b");
    model.param().set("alpha", "log(2)/9", "\u9ad8\u65af\u8109\u51b2\u5bbd\u5ea6");
    model.param().set("beta", "0.04", "\u9ad8\u65af\u8109\u51b2\u901f\u5ea6\u56e0\u5b50");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("G", "exp(-alpha*(x^2+y^2))", "\u9ad8\u65af\u51fd\u6570");
    model.component("comp1").variable("var1").set("p_i", "rho0*c0^2*G", "\u538b\u529b\u521d\u59cb\u6761\u4ef6");
    model.component("comp1").variable("var1")
         .set("u_i", "c0*beta*x*G", "x \u5411\u901f\u5ea6\u521d\u59cb\u6761\u4ef6");
    model.component("comp1").variable("var1")
         .set("v_i", "c0*beta*y*G", "y \u5411\u901f\u5ea6\u521d\u59cb\u6761\u4ef6");
    model.component("comp1").variable("var1")
         .set("dp_i", "-c0^2*rho0*(d(u_i, x) + d(v_i, y))", "\u538b\u529b\u65f6\u95f4\u5bfc\u6570\u521d\u59cb\u6761\u4ef6");
    model.component("comp1").variable("var1").set("r", "sqrt(x^2+y^2)", "\u8ddd\u79bb\u53d8\u91cf");
    model.component("comp1").variable("var1")
         .set("p_a", "1/(2*alpha)*integrate((cos(L*t)-beta/(2*alpha)*L*sin(L*t))*L*besselj(0,L*r)*exp(-L^2/(4*alpha)),L,0,10,1e-3)", "\u89e3\u6790\u538b\u529b\u8868\u8fbe\u5f0f");

    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("base", "center");
    model.component("comp1").geom("geom1").feature("sq1").set("size", "W");
    model.component("comp1").geom("geom1").feature("sq1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("sq1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("sq1").set("layertop", true);
    model.component("comp1").geom("geom1").feature("sq1").setIndex("layer", "dW", 0);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(1, 2, 3, 4, 6, 7, 8, 9);
    model.component("comp1").coordSystem("pml1").set("PMLgamma", "3");

    model.component("comp1").physics("actd").prop("TransientSettings").set("fmax", 0.3);
    model.component("comp1").physics("actd").feature("tpam1").set("c_mat", "userdef");
    model.component("comp1").physics("actd").feature("tpam1").set("c", "c0");
    model.component("comp1").physics("actd").feature("tpam1").set("rho_mat", "userdef");
    model.component("comp1").physics("actd").feature("tpam1").set("rho", "rho0");
    model.component("comp1").physics("actd").feature("init1").set("p", "p_i");
    model.component("comp1").physics("actd").feature("init1").set("dp/dt", "dp_i");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 1.5);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", 1.5);

    model.study("std1").feature("time").set("tlist", "range(0,1,120)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 121, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"actd.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (actd)");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 11, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 31, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 51, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 121, 0);
    model.result("pg1").run();
    model.result().dataset().create("cpt1", "CutPoint2D");
    model.result().dataset("cpt1").set("pointx", 20);
    model.result().dataset("cpt1").set("pointy", 10);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u70b9\u4e0a\u7684\u538b\u529b");
    model.result("pg2").set("data", "cpt1");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").set("expr", "p");
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg2").feature("ptgr1").setIndex("legends", "COMSOL \u6a21\u578b", 0);
    model.result("pg2").run();
    model.result("pg2").create("ptgr2", "PointGraph");
    model.result("pg2").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr2").set("linewidth", "preference");
    model.result("pg2").feature("ptgr2").set("expr", "p_a");
    model.result("pg2").feature("ptgr2").set("legend", true);
    model.result("pg2").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg2").feature("ptgr2").setIndex("legends", "\u89e3\u6790\u6a21\u578b", 0);
    model.result("pg2").feature("ptgr2").set("linestyle", "none");
    model.result("pg2").feature("ptgr2").set("linemarker", "point");
    model.result("pg2").feature("ptgr2").set("markerpos", "interp");
    model.result("pg2").feature("ptgr2").set("markers", 100);
    model.result("pg2").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", "-W/2", 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", "W/2", 1, 0);
    model.result().dataset().create("cln2", "CutLine2D");
    model.result().dataset("cln2").setIndex("genpoints", "-W/2+dW", 0, 0);
    model.result().dataset("cln2").setIndex("genpoints", "W/2-dW", 1, 0);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u6cbf\u622a\u7ebf\u7684\u538b\u529b");
    model.result("pg3").set("data", "none");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").set("data", "cln1");
    model.result("pg3").feature("lngr1").setIndex("looplevelinput", "manual", 0);
    model.result("pg3").feature("lngr1").setIndex("looplevel", new int[]{41}, 0);
    model.result("pg3").feature("lngr1").set("expr", "p");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("legendmethod", "manual");
    model.result("pg3").feature("lngr1").setIndex("legends", "COMSOL \u6a21\u578b", 0);
    model.result("pg3").run();
    model.result("pg3").create("lngr2", "LineGraph");
    model.result("pg3").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr2").set("linewidth", "preference");
    model.result("pg3").feature("lngr2").set("data", "cln2");
    model.result("pg3").feature("lngr2").setIndex("looplevelinput", "manual", 0);
    model.result("pg3").feature("lngr2").setIndex("looplevel", new int[]{41}, 0);
    model.result("pg3").feature("lngr2").set("expr", "p_a");
    model.result("pg3").feature("lngr2").set("xdata", "expr");
    model.result("pg3").feature("lngr2").set("xdataexpr", "x");
    model.result("pg3").feature("lngr2").set("legend", true);
    model.result("pg3").feature("lngr2").set("legendmethod", "manual");
    model.result("pg3").feature("lngr2").setIndex("legends", "\u89e3\u6790\u6a21\u578b", 0);
    model.result("pg3").feature("lngr2").set("linestyle", "none");
    model.result("pg3").feature("lngr2").set("linemarker", "point");
    model.result("pg3").feature("lngr2").set("markerpos", "interp");
    model.result("pg3").feature("lngr2").set("markers", 100);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u70b9\u4e0a\u7684\u538b\u529b\uff0cFFT");
    model.result("pg4").set("data", "cpt1");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").set("xdata", "fourier");
    model.result("pg4").feature("ptgr1").set("fouriershow", "spectrum");
    model.result("pg4").run();

    model
         .title("\u5b8c\u7f8e\u5339\u914d\u5c42\u4e0a\u7684\u9ad8\u65af\u8109\u51b2\u5438\u6536\uff1a\u538b\u529b\u58f0\u5b66\uff0c\u77ac\u6001");

    model
         .description("\u672c\u6559\u7a0b\u662f\u4e00\u4e2a\u5b8c\u7f8e\u5339\u914d\u5c42 (PML) \u4f5c\u4e3a\u65f6\u57df\u4e2d\u7684\u5438\u6536\u8fb9\u754c\u6761\u4ef6\u7684\u6807\u51c6\u6d4b\u8bd5\u548c\u57fa\u51c6\u6a21\u578b\uff0c\u8fd8\u6d89\u53ca\u65e0\u6d41\u52a8\u60c5\u51b5\u4e0b\u77ac\u6001\u9ad8\u65af\u8109\u51b2\u7684\u4f20\u64ad\u3002\u538b\u529b\u58f0\u5b66\uff0c\u77ac\u6001 \u63a5\u53e3\u4e0e\u5b8c\u7f8e\u5339\u914d\u5c42\u7684\u7ed3\u5408\u4f7f\u7528\uff0c\u7f29\u5c0f\u4e86\u8ba1\u7b97\u57df\uff0c\u5e76\u6291\u5236\u4e86\u4eba\u5de5\u8fb9\u754c\u7684\u53cd\u5c04\u3002\n\n\u58f0\u8109\u51b2\u7531\u8ba1\u7b97\u57df\u4e2d\u5fc3\u7684\u521d\u59cb\u9ad8\u65af\u5206\u5e03\u751f\u6210\u3002\u6b64\u95ee\u9898\u7684\u89e3\u6790\u89e3\u7528\u4e8e\u9a8c\u8bc1\u8be5\u89e3\uff0c\u8868\u660e\u4e24\u4e2a\u89e3\u975e\u5e38\u4e00\u81f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("gaussian_pulse_perfectly_matched_layers.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
