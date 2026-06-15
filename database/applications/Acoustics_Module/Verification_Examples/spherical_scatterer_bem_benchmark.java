/*
 * spherical_scatterer_bem_benchmark.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:41 by COMSOL 6.3.0.290. */
public class spherical_scatterer_bem_benchmark {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("pabe", "PressureAcousticsBoundaryElements", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/pabe", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f0", "1500[Hz]", "\u6700\u5927\u9891\u7387");
    model.param().set("c0", "343[m/s]", "\u58f0\u901f");
    model.param().set("rho0", "1.225[kg/m^3]", "\u5bc6\u5ea6");
    model.param().set("lam0", "c0/f0", "f0 \u7684\u6ce2\u957f");
    model.param().set("R0", "1[m]", "\u7403\u4f53\u534a\u5f84");

    model.variable().create("var1");
    model.variable("var1").label("\u53d8\u91cf - \u89e3\u6790");

//    To import content from file, use:
//    model.variable("var1").loadFile("FILENAME");
    model.variable("var1").set("k0", "2*pi*freq/c0", "\u6ce2\u6570");
    model.variable("var1").set("kR", "k0*R0", "k*R");
    model.variable("var1").set("r", "sqrt(x^2+y^2+z^2)", "\u5230 (x,y,z) \u7684\u8ddd\u79bb");
    model.variable("var1").set("ryz", "sqrt(y^2+z^2)", "yz \u5e73\u9762\u4e2d\u7684\u8ddd\u79bb");
    model.variable("var1").set("phi", "atan2(ryz,x)", "\u6781\u89d2");
    model.variable("var1").set("n", "1", "\u6c42\u548c\u521d\u59cb\u5316");
    model.variable("var1")
         .set("dJ", "d(J(n,kR),kR)", "\u7403\uff08\u7b2c\u4e00\u7c7b\uff09\u8d1d\u585e\u5c14\u51fd\u6570\u7684\u5bfc\u6570");
    model.variable("var1")
         .set("dN", "d(N(n,kR),kR)", "\u7403\uff08\u7b2c\u4e8c\u7c7b\uff09\u8d1d\u585e\u5c14\u51fd\u6570\u7684\u5bfc\u6570");
    model.variable("var1")
         .set("dH", "d(J(n,kR),kR)+i*d(N(n,kR),kR)", "\u7403 Hankel \u51fd\u6570\u7684\u5bfc\u6570");
    model.variable("var1").set("H", "J(n,k0*r)+i*N(n,k0*r)", "\u7403 Hankel \u51fd\u6570");
    model.variable("var1")
         .set("pana", "conj(sum(-i^n*(2*n+1)*dJ/dH*legendre(n,cos(phi))*H,n,0,100))", "\u6563\u5c04\u538b\u529b\u89e3\u6790\u8868\u8fbe\u5f0f");

    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "J");
    model.func("an1").set("expr", "sqrt(pi/(2*x))*besselj(n+0.5,x)");
    model.func("an1").set("args", "n, x");
    model.func("an1").setIndex("argunit", 1, 0);
    model.func("an1").set("fununit", "1");
    model.func().create("an2", "Analytic");
    model.func("an2").set("funcname", "N");
    model.func("an2").set("expr", "sqrt(pi/(2*x))*bessely(n+0.5,x)");
    model.func("an2").set("args", "n, x");
    model.func("an2").setIndex("argunit", 1, 0);
    model.func("an2").set("fununit", "1");

    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "R0");
    model.component("comp1").geom("geom1").feature("sph1").set("type", "surface");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().all();
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "min(0.3,lam0/4)");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "min(0.3,lam0/4)");
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.5);
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 2);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").physics("pabe").selection().set(0);
    model.component("comp1").physics("pabe").feature("bpam1").set("c_mat", "userdef");
    model.component("comp1").physics("pabe").feature("bpam1").set("c", "c0");
    model.component("comp1").physics("pabe").feature("bpam1").set("rho_mat", "userdef");
    model.component("comp1").physics("pabe").feature("bpam1").set("rho", "rho0");
    model.component("comp1").physics("pabe").create("bpf1", "BackgroundPressureField", 3);
    model.component("comp1").physics("pabe").feature("bpf1").set("pamp", 1);

    model.study("std1").feature("freq").set("plist", "range(50,50,f0)");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("grid1", "Grid3D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").set("parmin1", -3);
    model.result().dataset("grid1").set("parmax1", 5);
    model.result().dataset("grid1").set("parmin2", -3);
    model.result().dataset("grid1").set("parmax2", 3);
    model.result().dataset("grid1").set("parmin3", -3);
    model.result().dataset("grid1").set("parmax3", 3);
    model.result().dataset("grid1").set("res1", 100);
    model.result().dataset("grid1").set("res2", 40);
    model.result().dataset("grid1").set("res3", 100);
    model.result().dataset().create("pc1", "ParCurve3D");
    model.result().dataset("pc1").set("exprx", "R0+5*R0*s");
    model.result().dataset("pc1").set("global", true);
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("data", "grid1");
    model.result().dataset("cpt1").set("pointx", "2*R0");
    model.result().dataset("cpt1").set("pointy", 0);
    model.result().dataset("cpt1").set("pointz", 0);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u58f0\u538b\uff0c\u8fb9\u754c (pabe)");
    model.result("pg1").setIndex("looplevel", 20, 0);
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u603b\u58f0\u538b (Pa)");
    model.result("pg1").set("paramindicator", "f = eval(freq) Hz");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "pabe.p_t_bnd");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u58f0\u538b (pabe)");
    model.result("pg2").setIndex("looplevel", 10, 0);
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("paramindicator", "f = eval(freq) Hz");
    model.result("pg2").set("title", "\u603b\u58f0\u538b\u573a (Pa)");
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("xnumber", "0");
    model.result("pg2").feature("mslc1").set("colorscalemode", "linearsymmetric");
    model.result("pg2").feature("mslc1").set("colortable", "Wave");
    model.result("pg2").run();
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", "1");
    model.result("pg2").feature("line1").set("coloring", "uniform");
    model.result("pg2").feature("line1").set("color", "black");
    model.result("pg2").run();
    model.result("pg2").set("data", "grid1");
    model.result("pg2").run();
    model.result("pg2").feature("line1").set("data", "dset1");
    model.result("pg2").feature("line1").set("solutionparams", "parent");
    model.result("pg2").feature("line1").set("titletype", "none");
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "pabe.p_t_bnd");
    model.result("pg2").feature("surf1").set("inheritplot", "mslc1");
    model.result("pg2").feature("surf1").set("data", "dset1");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u58f0\u538b\u7ea7 (pabe)");
    model.result("pg3").set("data", "grid1");
    model.result("pg3").setIndex("looplevel", 20, 0);
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("paramindicator", "f = eval(freq) Hz");
    model.result("pg3").set("title", "\u58f0\u538b\u7ea7 (dB)");
    model.result("pg3").create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").set("expr", "pabe.Lp_t");
    model.result("pg3").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("mslc1").set("xcoord", 2);
    model.result("pg3").feature("mslc1").set("znumber", "0");
    model.result("pg3").run();
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("data", "dset1");
    model.result("pg3").feature("line1").set("solutionparams", "parent");
    model.result("pg3").feature("line1").set("expr", "1");
    model.result("pg3").feature("line1").set("coloring", "uniform");
    model.result("pg3").feature("line1").set("color", "black");
    model.result("pg3").feature("line1").set("titletype", "none");
    model.result("pg3").run();
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "pabe.Lp_t_bnd");
    model.result("pg3").feature("surf1").set("inheritplot", "mslc1");
    model.result("pg3").feature("surf1").set("data", "dset1");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").run();
    model.result().create("pg4", "PolarGroup");
    model.result("pg4").run();
    model.result("pg4").label("xy \u5e73\u9762\u5185\u7684\u6563\u5c04\u538b\u529b");
    model.result("pg4").setIndex("looplevelinput", "manual", 0);
    model.result("pg4").setIndex("looplevel", new int[]{4}, 0);
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("titlenumberformat", "auto");
    model.result("pg4").set("titleprecision", 4);
    model.result("pg4").set("title", "\u6563\u5c04\u538b\u529b\uff0cf = eval(freq) Hz");
    model.result("pg4").create("rp1", "RadiationPattern");
    model.result("pg4").feature("rp1").set("markerpos", "datapoints");
    model.result("pg4").feature("rp1").set("linewidth", "preference");
    model.result("pg4").feature("rp1").set("expr", "real(pabe.p_s)");
    model.result("pg4").feature("rp1").set("phidisc", 360);
    model.result("pg4").feature("rp1").set("radius", 10);
    model.result("pg4").feature("rp1").set("legend", true);
    model.result("pg4").feature("rp1").set("legendmethod", "manual");
    model.result("pg4").feature("rp1").setIndex("legends", "BEM: real(p_s)", 0);
    model.result("pg4").feature("rp1").set("linecolor", "blue");
    model.result("pg4").run();
    model.result("pg4").create("rp2", "RadiationPattern");
    model.result("pg4").feature("rp2").set("markerpos", "datapoints");
    model.result("pg4").feature("rp2").set("linewidth", "preference");
    model.result("pg4").feature("rp2").set("expr", "imag(pabe.p_s)");
    model.result("pg4").feature("rp2").set("phidisc", 360);
    model.result("pg4").feature("rp2").set("radius", 10);
    model.result("pg4").feature("rp2").set("legend", true);
    model.result("pg4").feature("rp2").set("legendmethod", "manual");
    model.result("pg4").feature("rp2").setIndex("legends", "BEM: imag(p_s)", 0);
    model.result("pg4").feature("rp2").set("linecolor", "red");
    model.result("pg4").run();
    model.result("pg4").create("rp3", "RadiationPattern");
    model.result("pg4").feature("rp3").set("markerpos", "datapoints");
    model.result("pg4").feature("rp3").set("linewidth", "preference");
    model.result("pg4").feature("rp3").set("expr", "real(pana)");
    model.result("pg4").feature("rp3").set("phidisc", 90);
    model.result("pg4").feature("rp3").set("radius", 10);
    model.result("pg4").feature("rp3").set("legend", true);
    model.result("pg4").feature("rp3").set("legendmethod", "manual");
    model.result("pg4").feature("rp3").setIndex("legends", "\u89e3\u6790\uff1areal(p_s)", 0);
    model.result("pg4").feature("rp3").set("linestyle", "none");
    model.result("pg4").feature("rp3").set("linecolor", "blue");
    model.result("pg4").feature("rp3").set("linemarker", "point");
    model.result("pg4").run();
    model.result("pg4").create("rp4", "RadiationPattern");
    model.result("pg4").feature("rp4").set("markerpos", "datapoints");
    model.result("pg4").feature("rp4").set("linewidth", "preference");
    model.result("pg4").feature("rp4").set("expr", "imag(pana)");
    model.result("pg4").feature("rp4").set("phidisc", 90);
    model.result("pg4").feature("rp4").set("radius", 10);
    model.result("pg4").feature("rp4").set("legend", true);
    model.result("pg4").feature("rp4").set("legendmethod", "manual");
    model.result("pg4").feature("rp4").setIndex("legends", "\u89e3\u6790\uff1aimag(p_s)", 0);
    model.result("pg4").feature("rp4").set("linestyle", "none");
    model.result("pg4").feature("rp4").set("linecolor", "red");
    model.result("pg4").feature("rp4").set("linemarker", "point");
    model.result("pg4").run();
    model.result().create("pg5", "PolarGroup");
    model.result("pg5").run();
    model.result("pg5").label("xy \u5e73\u9762\u5185\u7684\u6563\u5c04 SPL");
    model.result("pg5").setIndex("looplevelinput", "manual", 0);
    model.result("pg5").setIndex("looplevel", new int[]{10}, 0);
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("titlenumberformat", "auto");
    model.result("pg5").set("titleprecision", 4);
    model.result("pg5").set("title", "\u6563\u5c04 SPL\uff0cf = eval(freq) Hz");
    model.result("pg5").create("rp1", "RadiationPattern");
    model.result("pg5").feature("rp1").set("markerpos", "datapoints");
    model.result("pg5").feature("rp1").set("linewidth", "preference");
    model.result("pg5").feature("rp1").set("expr", "pabe.Lp_s");
    model.result("pg5").feature("rp1").set("phidisc", 360);
    model.result("pg5").feature("rp1").set("radius", 2);
    model.result("pg5").feature("rp1").set("legend", true);
    model.result("pg5").feature("rp1").set("legendmethod", "manual");
    model.result("pg5").feature("rp1").setIndex("legends", "BEM", 0);
    model.result("pg5").feature("rp1").set("linecolor", "blue");
    model.result("pg5").run();
    model.result("pg5").create("rp2", "RadiationPattern");
    model.result("pg5").feature("rp2").set("markerpos", "datapoints");
    model.result("pg5").feature("rp2").set("linewidth", "preference");
    model.result("pg5").feature("rp2").set("expr", "20*log10(sqrt(0.5)*abs(pana)/pabe.pref_SPL)");
    model.result("pg5").feature("rp2").set("phidisc", 90);
    model.result("pg5").feature("rp2").set("radius", 2);
    model.result("pg5").feature("rp2").set("legend", true);
    model.result("pg5").feature("rp2").set("legendmethod", "manual");
    model.result("pg5").feature("rp2").setIndex("legends", "\u89e3\u6790", 0);
    model.result("pg5").feature("rp2").set("linestyle", "none");
    model.result("pg5").feature("rp2").set("linecolor", "blue");
    model.result("pg5").feature("rp2").set("linemarker", "point");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u6cbf x \u8f74\u7684\u6563\u5c04\u538b\u529b");
    model.result("pg6").set("data", "pc1");
    model.result("pg6").setIndex("looplevelinput", "manual", 0);
    model.result("pg6").setIndex("looplevel", new int[]{20}, 0);
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("titlenumberformat", "auto");
    model.result("pg6").set("titleprecision", 4);
    model.result("pg6").set("title", "\u8f74\u4e0a\u6563\u5c04\u538b\u529b\uff0cf = eval(freq) Hz");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").set("expr", "pabe.p_s");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").set("linecolor", "blue");
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").set("legendmethod", "manual");
    model.result("pg6").feature("lngr1").setIndex("legends", "BEM", 0);
    model.result("pg6").run();
    model.result("pg6").create("lngr2", "LineGraph");
    model.result("pg6").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr2").set("linewidth", "preference");
    model.result("pg6").feature("lngr2").set("expr", "pana");
    model.result("pg6").feature("lngr2").set("xdata", "expr");
    model.result("pg6").feature("lngr2").set("xdataexpr", "x");
    model.result("pg6").feature("lngr2").set("linecolor", "blue");
    model.result("pg6").feature("lngr2").set("linestyle", "none");
    model.result("pg6").feature("lngr2").set("linemarker", "point");
    model.result("pg6").feature("lngr2").set("markerpos", "interp");
    model.result("pg6").feature("lngr2").set("markers", 300);
    model.result("pg6").feature("lngr2").set("legend", true);
    model.result("pg6").feature("lngr2").set("legendmethod", "manual");
    model.result("pg6").feature("lngr2").setIndex("legends", "\u89e3\u6790", 0);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u70b9\u4e0a\u7684\u7edd\u5bf9\u603b\u538b");
    model.result("pg7").set("data", "cpt1");
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "(x,y,z) = (2*R0,0,0) \u5904\u7684\u7edd\u5bf9\u603b\u538b");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "f (Hz)");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "|p<sub>t</sub>| (Pa)");
    model.result("pg7").set("axislimits", true);
    model.result("pg7").set("xmin", 40);
    model.result("pg7").set("xmax", 1500);
    model.result("pg7").set("ymin", 0.6);
    model.result("pg7").set("ymax", 1.6);
    model.result("pg7").create("ptgr1", "PointGraph");
    model.result("pg7").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr1").set("linewidth", "preference");
    model.result("pg7").feature("ptgr1").set("expr", "abs(pabe.p_t)");
    model.result("pg7").feature("ptgr1").set("legend", true);
    model.result("pg7").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg7").feature("ptgr1").setIndex("legends", "BEM", 0);
    model.result("pg7").feature("ptgr1").set("linecolor", "blue");
    model.result("pg7").run();
    model.result("pg7").create("ptgr2", "PointGraph");
    model.result("pg7").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr2").set("linewidth", "preference");
    model.result("pg7").feature("ptgr2").set("expr", "abs(pana+pabe.p_b)");
    model.result("pg7").feature("ptgr2").set("legend", true);
    model.result("pg7").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg7").feature("ptgr2").setIndex("legends", "\u89e3\u6790", 0);
    model.result("pg7").feature("ptgr2").set("linestyle", "none");
    model.result("pg7").feature("ptgr2").set("linecolor", "blue");
    model.result("pg7").feature("ptgr2").set("linemarker", "point");
    model.result("pg7").run();

    model.title("\u7403\u5f62\u6563\u5c04\u4f53\uff1aBEM \u57fa\u51c6\u6a21\u578b");

    model
         .description("\u5728\u8fd9\u4e2a\u7ecf\u5178\u57fa\u51c6\u6a21\u578b\u4e2d\uff0c\u7403\u5f62\u6563\u5c04\u4f53\u88ab\u653e\u7f6e\u5728\u4e00\u4e2a\u5e73\u9762\u6ce2\u80cc\u666f\u573a\u4e2d\u3002\u5f53\u6211\u4eec\u5c06\u7403\u4f53\u4f5c\u4e3a\u786c\u58f0\u573a\u5efa\u6a21\u65f6\uff0c\u6709\u89e3\u6790\u89e3\u3002\u6a21\u578b\u4e2d\u4f7f\u7528\u201c\u538b\u529b\u58f0\u5b66\uff0c\u8fb9\u754c\u5143\u201d\u63a5\u53e3\u5f97\u5230\u591a\u4e2a\u9891\u7387\u4e0b\u7684\u4eff\u771f\u7ed3\u679c\uff0c\u5e76\u5c06\u5176\u4e0e\u89e3\u6790\u89e3\u8fdb\u884c\u6bd4\u8f83\uff0c\u7ed3\u679c\u663e\u793a\u4e24\u8005\u9ad8\u5ea6\u4e00\u81f4\uff0c\u6a21\u578b\u7ed3\u679c\u6ca1\u6709\u663e\u793a\u4efb\u4f55\u4e0d\u89c4\u5219\u6a21\u5f0f\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("spherical_scatterer_bem_benchmark.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
