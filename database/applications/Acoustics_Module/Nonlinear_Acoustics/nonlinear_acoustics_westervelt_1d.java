/*
 * nonlinear_acoustics_westervelt_1d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class nonlinear_acoustics_westervelt_1d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Nonlinear_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("actd", "TransientPressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/actd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("P0", "5e6[Pa]", "\u6e90\u538b\u529b\u5e45\u503c");
    model.param().set("v0", "P0/(rho0*c0)", "\u6e90\u901f\u5ea6\u5e45\u503c");
    model.param().set("f0", "1e5[Hz]", "\u6e90\u9891\u7387");
    model.param().set("T0", "1/f0", "\u6e90\u5468\u671f");
    model.param().set("omega0", "2*pi*f0", "\u89d2\u9891\u7387");
    model.param().set("beta", "10", "\u975e\u7ebf\u6027\u7cfb\u6570");
    model.param().set("c0", "1481.44[m/s]", "\u58f0\u901f");
    model.param().set("rho0", "999.6[kg/m^3]", "\u5bc6\u5ea6");
    model.param().set("mu0", "1.0016e-3[Pa*s]", "\u9ecf\u5ea6");
    model.param().set("d_diff", "4/3*mu0/rho0", "\u58f0\u6269\u6563\u7cfb\u6570");
    model.param().set("Gamma", "2*beta*P0/(omega0*rho0*d_diff)", "Goldberg \u6570");
    model.param().set("x_sh", "rho0*c0^3/(beta*P0*omega0)", "\u6fc0\u6ce2\u5f62\u6210\u8ddd\u79bb");
    model.param().set("L", "4.5*x_sh", "\u8ba1\u7b97\u57df\u957f\u5ea6");
    model.param().set("Nt", "ceil(L/(c0*T0))", "\u5faa\u73af\u6b21\u6570");
    model.param().set("N0", "8", "\u8981\u89e3\u6790\u7684\u8c10\u6ce2\u6570");

    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "Pn_fubini");
    model.func("an1").set("expr", "1/n*besselj(n, n*sigma)*sin(n*omega0*(t - sigma*x_sh/c0))");
    model.func("an1").set("args", "sigma, t, n");
    model.func("an1").setIndex("argunit", 1, 0);
    model.func("an1").setIndex("argunit", "s", 1);
    model.func("an1").setIndex("argunit", 1, 2);
    model.func("an1").set("fununit", "1");
    model.func().create("an2", "Analytic");
    model.func("an2").set("funcname", "Pn_fay");
    model.func("an2").set("expr", "1/sinh(n*(sigma + 1)/Gamma)*sin(n*omega0*(t - sigma*x_sh/c0))");
    model.func("an2").set("args", "sigma, t, n");
    model.func("an2").setIndex("argunit", 1, 0);
    model.func("an2").setIndex("argunit", "s", 1);
    model.func("an2").setIndex("argunit", 1, 2);
    model.func("an2").set("fununit", "1");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("sigma", "x/x_sh");
    model.component("comp1").variable("var1").descr("sigma", "\u76f8\u5bf9\u8ddd\u79bb");
    model.component("comp1").variable("var1").set("p_fubini", "2*P0/sigma*sum(Pn_fubini(sigma, t, n), n, 1, 100)");
    model.component("comp1").variable("var1").descr("p_fubini", "Fubini \u89e3");
    model.component("comp1").variable("var1").set("p_fay", "2*P0/Gamma*sum(Pn_fay(sigma, t, n), n, 1, 100)");
    model.component("comp1").variable("var1").descr("p_fay", "Fay \u89e3");
    model.component("comp1").variable("var1").set("p_linear", "P0*sin(omega0*(t - x/c0))");
    model.component("comp1").variable("var1").descr("p_linear", "\u7ebf\u6027\u89e3");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "L", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("actd").prop("TransientSettings").set("fmax", "N0*f0");
    model.component("comp1").physics("actd").feature("tpam1").set("FluidModel", "GeneralDissipation");
    model.component("comp1").physics("actd").feature("tpam1").set("c_mat", "userdef");
    model.component("comp1").physics("actd").feature("tpam1").set("c", "c0");
    model.component("comp1").physics("actd").feature("tpam1").set("rho_mat", "userdef");
    model.component("comp1").physics("actd").feature("tpam1").set("rho", "rho0");
    model.component("comp1").physics("actd").feature("tpam1").set("delta_diff_mat", "userdef");
    model.component("comp1").physics("actd").feature("tpam1").set("delta_diff", "d_diff");
    model.component("comp1").physics("actd").create("nlaw1", "NonlinearAcousticsWestervelt", 1);
    model.component("comp1").physics("actd").feature("nlaw1").selection().set(1);
    model.component("comp1").physics("actd").feature("nlaw1").set("MaterialDataOption", "beta");
    model.component("comp1").physics("actd").feature("nlaw1").set("beta", "beta");
    model.component("comp1").physics("actd").feature("nlaw1").set("qLaplacianRelaxation", true);
    model.component("comp1").physics("actd").feature("nlaw1").set("q", 1.35);
    model.component("comp1").physics("actd").feature("nlaw1").set("kappa", 0.01);
    model.component("comp1").physics("actd").create("pr1", "Pressure", 0);
    model.component("comp1").physics("actd").feature("pr1").selection().set(1);
    model.component("comp1").physics("actd").feature("pr1").set("p0", "P0*sin(omega0*t)");
    model.component("comp1").physics("actd").create("pwr1", "PlaneWaveRadiation", 0);
    model.component("comp1").physics("actd").feature("pwr1").selection().set(2);
    model.component("comp1").physics("actd").prop("MeshControl").set("ElementsPerWavelength", "UserDefined");
    model.component("comp1").physics("actd").prop("MeshControl").set("nperlambda", 6);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,T0/50,Nt*T0)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("expr", new String[]{"actd.p_t"});
    model.result("pg1").label("\u58f0\u538b (actd)");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevelinput", "last", 0);
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u538b\u529b (Pa)");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u603b\u58f0\u538b\u573a (Pa)");
    model.result("pg1").run();
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("legendmethod", "manual");
    model.result("pg1").feature("lngr1").setIndex("legends", "\u975e\u7ebf\u6027\u89e3", 0);
    model.result("pg1").run();
    model.result("pg1").create("lngr2", "LineGraph");
    model.result("pg1").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr2").set("linewidth", "preference");
    model.result("pg1").feature("lngr2").selection().set(1);
    model.result("pg1").feature("lngr2").set("expr", "p_linear");
    model.result("pg1").feature("lngr2").set("legend", true);
    model.result("pg1").feature("lngr2").set("legendmethod", "manual");
    model.result("pg1").feature("lngr2").setIndex("legends", "\u7ebf\u6027\u89e3", 0);
    model.result("pg1").feature("lngr2").set("xdata", "expr");
    model.result("pg1").feature("lngr2").set("xdataexpr", "x");
    model.result("pg1").run();
    model.result("pg1").create("lngr3", "LineGraph");
    model.result("pg1").feature("lngr3").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr3").set("linewidth", "preference");
    model.result("pg1").feature("lngr3").selection().set(1);
    model.result("pg1").feature("lngr3").set("xdata", "expr");
    model.result("pg1").feature("lngr3").set("xdataexpr", "x_sh");
    model.result("pg1").feature("lngr3").set("linewidth", 2);
    model.result("pg1").feature("lngr3").set("legend", true);
    model.result("pg1").feature("lngr3").set("legendmethod", "manual");
    model.result("pg1").feature("lngr3").setIndex("legends", "\u6fc0\u6ce2\u5f62\u6210\u8ddd\u79bb", 0);
    model.result("pg1").run();
    model.result().dataset().create("cpt1", "CutPoint1D");
    model.result().dataset("cpt1").set("pointx", "0.5*x_sh");
    model.result().dataset("cpt1").label("\u622a\u70b9 - 0.5 \u500d\u7684\u6fc0\u6ce2\u5f62\u6210\u8ddd\u79bb");
    model.result().dataset().create("cpt2", "CutPoint1D");
    model.result().dataset("cpt2").set("pointx", "x_sh");
    model.result().dataset("cpt2").label("\u622a\u70b9 - 1 \u500d\u7684\u6fc0\u6ce2\u5f62\u6210\u8ddd\u79bb");
    model.result().dataset().create("cpt3", "CutPoint1D");
    model.result().dataset("cpt3").set("pointx", "3.5*x_sh");
    model.result().dataset("cpt3").label("\u622a\u70b9 - 3.5 \u500d\u7684\u6fc0\u6ce2\u5f62\u6210\u8ddd\u79bb");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("sigma = 0.5 \u65f6\u7684\u58f0\u538b");
    model.result("pg2").set("data", "cpt1");
    model.result("pg2").setIndex("looplevelinput", "interp", 0);
    model.result("pg2").setIndex("interp", "range((Nt - 5)*T0, T0/50, Nt*T0)", 0);
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u538b\u529b (Pa)");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u4e0e sigma = 0.5 \u65f6\u7684\u89e3\u6790\u89e3\u8fdb\u884c\u6bd4\u8f83");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg2").feature("ptgr1").setIndex("legends", "\u6a21\u578b", 0);
    model.result("pg2").run();
    model.result("pg2").create("ptgr2", "PointGraph");
    model.result("pg2").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr2").set("linewidth", "preference");
    model.result("pg2").feature("ptgr2").set("expr", "p_fubini");
    model.result("pg2").feature("ptgr2").set("legend", true);
    model.result("pg2").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg2").feature("ptgr2").setIndex("legends", "Fubini \u89e3", 0);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("sigma = 1 \u65f6\u7684\u58f0\u538b");
    model.result("pg3").set("data", "cpt2");
    model.result("pg3").setIndex("looplevelinput", "interp", 0);
    model.result("pg3").setIndex("interp", "range((Nt - 5)*T0, T0/50, Nt*T0)", 0);
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u538b\u529b (Pa)");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u4e0e sigma = 1 \u65f6\u7684\u89e3\u6790\u89e3\u8fdb\u884c\u6bd4\u8f83");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").set("legend", true);
    model.result("pg3").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg3").feature("ptgr1").setIndex("legends", "\u6a21\u578b", 0);
    model.result("pg3").run();
    model.result("pg3").create("ptgr2", "PointGraph");
    model.result("pg3").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr2").set("linewidth", "preference");
    model.result("pg3").feature("ptgr2").set("expr", "p_fubini");
    model.result("pg3").feature("ptgr2").set("legend", true);
    model.result("pg3").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg3").feature("ptgr2").setIndex("legends", "Fubini \u89e3", 0);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("sigma = 3.5 \u65f6\u7684\u58f0\u538b");
    model.result("pg4").set("data", "cpt3");
    model.result("pg4").setIndex("looplevelinput", "interp", 0);
    model.result("pg4").setIndex("interp", "range((Nt - 5)*T0, T0/50, Nt*T0)", 0);
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u538b\u529b (Pa)");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u4e0e sigma = 3.5 \u65f6\u7684\u89e3\u6790\u89e3\u8fdb\u884c\u6bd4\u8f83");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").set("legend", true);
    model.result("pg4").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg4").feature("ptgr1").setIndex("legends", "\u6a21\u578b", 0);
    model.result("pg4").run();
    model.result("pg4").create("ptgr2", "PointGraph");
    model.result("pg4").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr2").set("linewidth", "preference");
    model.result("pg4").feature("ptgr2").set("expr", "p_fay");
    model.result("pg4").feature("ptgr2").set("legend", true);
    model.result("pg4").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg4").feature("ptgr2").setIndex("legends", "Fay \u89e3", 0);
    model.result("pg4").run();
    model.result("pg2").run();
    model.result().duplicate("pg5", "pg2");
    model.result("pg5").run();
    model.result("pg5").label("sigma = 0.5 \u65f6\u7684\u58f0\u538b\u8c31");
    model.result("pg5").set("title", "sigma = 0.5 \u65f6\u7684\u9891\u8c31");
    model.result("pg5").set("xlog", true);
    model.result("pg5").run();
    model.result("pg5").feature("ptgr1").set("xdata", "fourier");
    model.result("pg5").feature("ptgr1").set("fouriershow", "spectrum");
    model.result("pg5").feature("ptgr1").set("scale", "multiplyperiod");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr2").set("xdata", "fourier");
    model.result("pg5").feature("ptgr2").set("fouriershow", "spectrum");
    model.result("pg5").feature("ptgr2").set("scale", "multiplyperiod");
    model.result("pg5").feature("ptgr2").set("linestyle", "none");
    model.result("pg5").feature("ptgr2").set("linemarker", "point");
    model.result("pg5").run();
    model.result("pg3").run();
    model.result().duplicate("pg6", "pg3");
    model.result("pg6").run();
    model.result("pg6").label("sigma = 1 \u65f6\u7684\u58f0\u538b\u8c31");
    model.result("pg6").set("title", "sigma = 1 \u65f6\u7684\u9891\u8c31");
    model.result("pg6").set("xlog", true);
    model.result("pg6").run();
    model.result("pg6").feature("ptgr1").set("xdata", "fourier");
    model.result("pg6").feature("ptgr1").set("fouriershow", "spectrum");
    model.result("pg6").feature("ptgr1").set("scale", "multiplyperiod");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr2").set("xdata", "fourier");
    model.result("pg6").feature("ptgr2").set("fouriershow", "spectrum");
    model.result("pg6").feature("ptgr2").set("scale", "multiplyperiod");
    model.result("pg6").feature("ptgr2").set("linestyle", "none");
    model.result("pg6").feature("ptgr2").set("linemarker", "point");
    model.result("pg6").run();
    model.result("pg4").run();
    model.result().duplicate("pg7", "pg4");
    model.result("pg7").run();
    model.result("pg7").label("sigma = 3.5 \u5904\u7684\u58f0\u538b\u8c31");
    model.result("pg7").set("title", "sigma = 3.5 \u5904\u7684\u9891\u8c31");
    model.result("pg7").set("xlog", true);
    model.result("pg7").run();
    model.result("pg7").feature("ptgr1").set("xdata", "fourier");
    model.result("pg7").feature("ptgr1").set("fouriershow", "spectrum");
    model.result("pg7").feature("ptgr1").set("scale", "multiplyperiod");
    model.result("pg7").run();
    model.result("pg7").feature("ptgr2").set("xdata", "fourier");
    model.result("pg7").feature("ptgr2").set("fouriershow", "spectrum");
    model.result("pg7").feature("ptgr2").set("scale", "multiplyperiod");
    model.result("pg7").feature("ptgr2").set("linestyle", "none");
    model.result("pg7").feature("ptgr2").set("linemarker", "point");
    model.result("pg7").run();
    model.result("pg1").run();
    model.result().duplicate("pg8", "pg1");
    model.result("pg8").run();
    model.result("pg8").label("\u58f0\u538b(actd)\uff0c\u5df2\u653e\u5927");
    model.result("pg8").set("showlegends", false);
    model.result("pg8").set("axislimits", true);
    model.result("pg8").set("xmin", 0.065);
    model.result("pg8").set("xmax", 0.165);
    model.result("pg8").run();

    model.title("\u975e\u7ebf\u6027\u58f0\u5b66 - \u4e00\u7ef4 Westervelt \u65b9\u7a0b\u5efa\u6a21");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u58f0\u5b66\u6a21\u5757\u201d\u6a21\u62df\u6d41\u4f53\u4e2d\u6709\u9650\u632f\u5e45\u58f0\u6ce2\u7684\u77ac\u6001\u975e\u7ebf\u6027\u4f20\u64ad\u3002\u5176\u4e2d\u4f7f\u7528\u538b\u529b\u58f0\u5b66\uff0c\u77ac\u6001 \u63a5\u53e3\u6c42\u89e3 Westervelt \u65b9\u7a0b\uff0c\u8fd9\u662f\u4e00\u79cd\u5bf9\u5b8c\u6574\u4e8c\u9636\u975e\u7ebf\u6027\u6ce2\u52a8\u65b9\u7a0b\u7684\u8fd1\u4f3c\uff0c\u9002\u7528\u4e8e\u7d2f\u79ef\u7684\u975e\u7ebf\u6027\u6548\u5e94\u6bd4\u5c40\u90e8\u975e\u7ebf\u6027\u6548\u5e94\u66f4\u4e3a\u663e\u8457\u7684\u60c5\u51b5\u3002\u672c\u4f8b\u5728\u4e00\u7ef4\u6a21\u5f0f\u4e0b\u6a21\u62df\u6709\u9650\u632f\u5e45\u6ce2\u7684\u4f20\u64ad\u8ddd\u79bb\u8fdc\u5927\u4e8e\u6fc0\u6ce2\u5f62\u6210\u8ddd\u79bb\u7684\u60c5\u5f62\uff0c\u5e76\u5c06\u6fc0\u6ce2\u5f62\u6210\u524d\u540e\u7684\u6570\u503c\u89e3\u4e0e\u89e3\u6790\u89e3\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("nonlinear_acoustics_westervelt_1d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
