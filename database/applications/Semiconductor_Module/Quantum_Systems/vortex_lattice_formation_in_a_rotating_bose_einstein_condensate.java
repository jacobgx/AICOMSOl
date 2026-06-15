/*
 * vortex_lattice_formation_in_a_rotating_bose_einstein_condensate.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:53 by COMSOL 6.3.0.290. */
public class vortex_lattice_formation_in_a_rotating_bose_einstein_condensate {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Quantum_Systems");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("schr", "SchrodingerEquation", "geom1");

    model.study().create("std1");
    model.study("std1").create("eigv", "Eigenvalue");
    model.study("std1").feature("eigv").set("ftplistmethod", "manual");
    model.study("std1").feature("eigv").set("neigs", "3");
    model.study("std1").feature("eigv").set("eigunit", "");
    model.study("std1").feature("eigv").set("shift", "0.1");
    model.study("std1").feature("eigv").set("linpsolnum", "auto");
    model.study("std1").feature("eigv").set("solnum", "auto");
    model.study("std1").feature("eigv").set("notsolnum", "auto");
    model.study("std1").feature("eigv").set("outputmap", new String[]{});
    model.study("std1").feature("eigv").set("ngenAUX", "1");
    model.study("std1").feature("eigv").set("goalngenAUX", "1");
    model.study("std1").feature("eigv").set("ngenAUX", "1");
    model.study("std1").feature("eigv").set("goalngenAUX", "1");
    model.study("std1").feature("eigv").setSolveFor("/physics/schr", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");

    model.func().create("step1", "Step");
    model.func("step1").set("location", 0.5);
    model.func("step1").set("smooth", 0.95);

    model.param().set("a", "5.5[nm]");
    model.param().descr("a", "\u6563\u5c04\u957f\u5ea6");
    model.param().set("m", "86.909[g/mol]/N_A_const");
    model.param().descr("m", "\u539f\u5b50\u91cf");
    model.param().set("g", "4*pi*hbar_const^2*a/m");
    model.param().descr("g", "\u8026\u5408\u5e38\u6570");
    model.param().set("tau", "20[ms]");
    model.param().descr("tau", "\u659c\u5761\u6301\u7eed\u65f6\u95f4");
    model.param().set("t", "-tau");
    model.param().descr("t", "\u65f6\u95f4\u53c2\u6570");
    model.param().set("t_off", "300[ms]");
    model.param().descr("t_off", "\u7ed3\u675f\u65f6\u95f4");
    model.param().set("epst", "0.032*step1((t+tau)/tau)*(1-step1((t-t_off)/tau))");
    model.param().descr("epst", "\u692d\u5706\u7387\u53c2\u6570");
    model.param().set("epsX", "(epst+sqrt(0.03*0.09+epst^2-0.03*0.09*epst^2))/(1-epst)");
    model.param().descr("epsX", "\u5149\u5b66\u52bf\u53c2\u6570");
    model.param().set("epsY", "(-epst+sqrt(0.03*0.09+epst^2-0.03*0.09*epst^2))/(1+epst)");
    model.param().descr("epsY", "\u5149\u5b66\u52bf\u53c2\u6570");
    model.param().set("wz", "2*pi*11.8[Hz]");
    model.param().descr("wz", "\u7eb5\u5411\u9677\u9631\u9891\u7387");
    model.param().set("lambda", "9.2");
    model.param().descr("lambda", "\u9677\u9631\u7eb5\u6a2a\u6bd4");
    model.param().set("wt", "lambda*wz");
    model.param().descr("wt", "\u6a2a\u5411\u9677\u9631\u9891\u7387");
    model.param().set("wX2", "wt^2*(1+epsX)");
    model.param().descr("wX2", "\u6a2a\u5411\u9677\u9631\u9891\u7387\u5e73\u65b9");
    model.param().set("wY2", "wt^2*(1+epsY)");
    model.param().descr("wY2", "\u6a2a\u5411\u9677\u9631\u9891\u7387\u5e73\u65b9");
    model.param().set("wbar", "wt*sqrt((1.03+1.09)/2)");
    model.param().descr("wbar", "\u5e73\u5747\u6a2a\u5411\u9677\u9631\u9891\u7387");
    model.param().set("Omega", "0.7*wbar");
    model.param().descr("Omega", "\u6405\u62cc\u9891\u7387");
    model.param().set("N", "1.5e5");
    model.param().descr("N", "\u539f\u5b50\u6570");
    model.param().set("RrTF", "(15*g*wz*N/(4*pi*m*wbar^3))^0.2");
    model.param().descr("RrTF", "\u51dd\u805a\u4f53\u7684\u6a2a\u5411\u5c3a\u5bf8 (T-F)");
    model.param().set("RzTF", "(15*g*wbar^2*N/(4*pi*m*wz^4))^0.2");
    model.param().descr("RzTF", "\u51dd\u805a\u4f53\u7684\u7eb5\u5411\u5c3a\u5bf8 (T-F)");
    model.param().set("rho0TF", "15*N/(8*pi*RrTF^2*RzTF)");
    model.param().descr("rho0TF", "\u51dd\u805a\u4f53\u7684\u5cf0\u503c\u5bc6\u5ea6 (T-F)");
    model.param().set("L", "N/rho0TF/(pi*RrTF^2/2)");
    model.param().descr("L", "\u9762\u5916\u539a\u5ea6");
    model.param().set("kT", "k_B_const*100[nK]");
    model.param().descr("kT", "\u70ed\u80fd");
    model.param().set("muTF", "rho0TF*g");
    model.param().descr("muTF", "\u5316\u5b66\u52bf (T-F)");
    model.param().set("gamma", "4*m*(a*kT)^2/pi/hbar_const^3*exp(2*muTF/kT)*muTF/kT*besselk(1,muTF/kT)/wt");
    model.param().descr("gamma", "\u963b\u5c3c\u53c2\u6570");
    model.param().set("t_damp", "(1+gamma^2)/gamma/wt");
    model.param().descr("t_damp", "\u963b\u5c3c\u65f6\u95f4\u5c3a\u5ea6");
    model.param().set("t_trap", "2*pi*(1+gamma^2)/wt");
    model.param().descr("t_trap", "\u6a2a\u5411\u9677\u9631\u65f6\u95f4\u5c3a\u5ea6");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "1.6*RrTF");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("schr").prop("ModelProperties").set("lambda_scale", "hbar_const*wt");
    model.component("comp1").physics("schr").feature("meff1").label("\u539f\u5b50\u91cf");
    model.component("comp1").physics("schr").feature("meff1")
         .set("meffe_psi", new String[]{"m", "0", "0", "0", "m", "0", "0", "0", "m"});
    model.component("comp1").physics("schr").feature("ve1").label("\u9677\u9631\u52bf\u80fd");
    model.component("comp1").physics("schr").feature("ve1").set("Ve_src", "userdef");
    model.component("comp1").physics("schr").feature("ve1").set("Ve", "m*wt^2*(X^2+Y^2)/2");
    model.component("comp1").physics("schr").create("ve2", "ElectronPotentialEnergy", 2);
    model.component("comp1").physics("schr").feature("ve2").label("\u5149\u5b66\u52bf\u80fd");
    model.component("comp1").physics("schr").feature("ve2").selection().all();
    model.component("comp1").physics("schr").feature("ve2").set("Ve_src", "userdef");
    model.component("comp1").physics("schr").feature("ve2").set("Ve", "m*wt^2*(epsX*X^2+epsY*Y^2)/2");

    model.component("comp1").mesh("mesh1").create("se1", "SizeExpression");
    model.component("comp1").mesh("mesh1").feature("se1").set("sizeexpr", "if(X^2+Y^2<(RrTF*1.3)^2,RrTF/10,RrTF/7)");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").set("method", "del");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u521d\u59cb\u6761\u4ef6\u7684\u7279\u5f81\u503c");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("eigv").set("neigs", 1);
    model.study("std1").feature("eigv").set("shift", "1");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.component("comp1").physics("schr").create("ve3", "ElectronPotentialEnergy", 2);
    model.component("comp1").physics("schr").feature("ve3").selection().all();
    model.component("comp1").physics("schr").feature("ve3").set("Ve_src", "userdef");
    model.component("comp1").physics("schr").feature("ve3").set("Ve", "g*schr.Pr/L");
    model.component("comp1").physics("schr").feature("ve3").label("\u76f8\u4e92\u4f5c\u7528\u80fd");
    model.component("comp1").physics("schr").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("schr").feature("ge1")
         .label("\u5168\u5c40\u65b9\u7a0b - \u7a33\u6001\u7814\u7a76\u7684\u5f52\u4e00\u5316");
    model.component("comp1").physics("schr").feature("ge1").setIndex("name", "E0", 0, 0);
    model.component("comp1").physics("schr").feature("ge1").setIndex("equation", "schr.int(schr.Pr)/N-1", 0, 0);
    model.component("comp1").physics("schr").prop("ModelProperties").set("E", "E0*muTF");

    model.nodeGroup().create("grp1", "Physics", "schr");
    model.nodeGroup("grp1").placeAfter("ve3");
    model.nodeGroup("grp1").add("ge1");
    model.nodeGroup("grp1").label("\u7a33\u6001\u7814\u7a76");

    model.component("comp1").physics("schr").create("init2", "init", 2);

    model.nodeGroup("grp1").add("init2");

    model.component("comp1").physics("schr").feature("init2").selection().all();
    model.component("comp1").physics("schr").feature("init2").set("psi", "schr.Psi");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/schr", true);
    model.study("std2").feature("stat").set("probesel", "none");
    model.study("std2").feature("stat").set("useinitsol", true);
    model.study("std2").feature("stat").set("initstudy", "std1");
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "a", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "a", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "N", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "1 10 N", 0);
    model.study("std2").label("\u7814\u7a76 2 - \u51dd\u805a\u4f53\u7684\u7a33\u6001");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").set("data", "dset2");
    model.result().dataset("cln1").set("bounded", false);
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1")
         .label("\u7a33\u6001\u5206\u5e03 - \u4e0e T-F \u8fd1\u4f3c\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002");
    model.result("pg1").set("data", "cln1");
    model.result("pg1").setIndex("looplevelinput", "last", 0);
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr1").set("linewidth", "preference");
    model.result("pg1").feature("lngr1").label("\u6570\u503c\u89e3");
    model.result("pg1").feature("lngr1").set("expr", "schr.Pr/L");
    model.result("pg1").feature("lngr1").set("unit", "1/um^3");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "X");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("autoplotlabel", true);
    model.result("pg1").feature("lngr1").set("autosolution", false);
    model.result("pg1").feature().duplicate("lngr2", "lngr1");
    model.result("pg1").run();
    model.result("pg1").feature("lngr2").label("T-F \u8fd1\u4f3c\u89e3");
    model.result("pg1").feature("lngr2").set("expr", "rho0TF*max(0,1-X^2/RrTF^2)");
    model.result("pg1").run();

    model.component("comp1").physics("schr").create("rotf1", "RotatingFrameSchrod", 2);
    model.component("comp1").physics("schr").feature("rotf1").selection().all();
    model.component("comp1").physics("schr").feature("rotf1").set("Ov", "Omega");

    model.nodeGroup().create("grp2", "Physics", "schr");
    model.nodeGroup("grp2").placeAfter("ve3");
    model.nodeGroup("grp2").add("rotf1");
    model.nodeGroup("grp2").label("\u77ac\u6001\u7814\u7a76");

    model.component("comp1").physics("schr").create("diss1", "DissipationSchrod", 2);

    model.nodeGroup("grp2").add("diss1");

    model.component("comp1").physics("schr").feature("diss1").selection().all();
    model.component("comp1").physics("schr").feature("diss1").set("gamma", "gamma");
    model.component("comp1").physics("schr").create("ve4", "ElectronPotentialEnergy", 2);

    model.nodeGroup("grp2").add("ve4");

    model.component("comp1").physics("schr").feature("ve4").label("\u5316\u5b66\u52bf");
    model.component("comp1").physics("schr").feature("ve4").selection().all();
    model.component("comp1").physics("schr").feature("ve4").set("Ve_src", "userdef");
    model.component("comp1").physics("schr").feature("ve4").set("Ve", "-mu*muTF");
    model.component("comp1").physics("schr").create("ge2", "GlobalEquations", -1);

    model.nodeGroup("grp2").add("ge2");

    model.component("comp1").physics("schr").feature("ge2").setIndex("name", "mu", 0, 0);
    model.component("comp1").physics("schr").feature("ge2").setIndex("equation", "schr.int(schr.Pr)/N-1", 0, 0);
    model.component("comp1").physics("schr").feature("ge2")
         .setIndex("initialValueU", "withsol('sol2',E0,setind(N,-1))", 0, 0);
    model.component("comp1").physics("schr").create("init3", "init", 2);

    model.nodeGroup("grp2").add("init3");

    model.component("comp1").physics("schr").feature("init3").selection().all();
    model.component("comp1").physics("schr").feature("init3").set("psi", "psi");

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/schr", true);
    model.study("std3").feature("time").set("tunit", "ms");
    model.study("std3").feature("time")
         .set("tlist", "range(-tau,5[ms],250[ms]) range(252[ms],2[ms],450[ms]) range(455[ms],5[ms],600[ms])");
    model.study("std3").feature("time").set("usertol", true);
    model.study("std3").feature("time").set("rtol", "5e-4");
    model.study("std3").feature("time").set("useadvanceddisable", true);
    model.study("std3").feature("time").set("disabledphysics", new String[]{"schr/ge1", "schr/init2"});
    model.study("std3").feature("time").set("useinitsol", true);
    model.study("std3").feature("time").set("initstudy", "std2");
    model.study("std3").feature("time").set("solnum", "last");
    model.study("std3").label("\u7814\u7a76 3 - \u6da1\u683c\u5f62\u6210\u7684\u77ac\u6001");
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").feature("t1").set("maxstepconstraintgenalpha", "expr");
    model.sol("sol3").feature("t1").set("maxstepexpressiongenalpha", "t_trap/9");
    model.sol("sol3").feature("t1").set("consistent", false);
    model.sol("sol3").feature("t1").set("estrat", "exclude");
    model.sol("sol3").feature("t1").feature("fc1").set("dtech", "auto");
    model.sol("sol3").feature("t1").feature("fc1").set("maxiter", 100);

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").label("\u5168\u5c40\u53d8\u91cf\u63a2\u9488 1 - Lz");
    model.component("comp1").probe("var1").set("probename", "Lz_probe");
    model.component("comp1").probe("var1").set("expr", "schr.L_avZ/hbar_const/N");
    model.component("comp1").probe("var1").set("descractive", true);
    model.component("comp1").probe("var1").set("descr", "\u6bcf\u4e2a\u539f\u5b50\u7684\u89d2\u52a8\u91cf");

    model.study("std3").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");

    model.sol("sol3").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u6ce2\u51fd\u6570 (schr)");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 185, 0);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u5b9e\u90e8");
    model.result("pg3").feature("surf1").set("expr", "psi");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature("surf1").feature().create("hght1", "Height");
    model.result("pg3").feature().create("surf2", "Surface");
    model.result("pg3").feature("surf2").label("\u865a\u90e8");
    model.result("pg3").feature("surf2").set("expr", "imag(psi)");
    model.result("pg3").feature("surf2").set("smooth", "internal");
    model.result("pg3").feature("surf2").set("data", "parent");
    model.result("pg3").feature("surf2").set("inheritplot", "surf1");
    model.result("pg3").feature("surf2").feature().create("hght1", "Height");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u6982\u7387\u5bc6\u5ea6 (schr)");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 185, 0);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "schr.Pr");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature("surf1").feature().create("hght1", "Height");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u52bf\u80fd (schr)");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 185, 0);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "schr.V_psi");
    model.result("pg5").feature("surf1").set("unit", "eV");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature("surf1").feature().create("hght1", "Height");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u6709\u6548\u8d28\u91cf (schr)");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").setIndex("looplevel", 185, 0);
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "schr.meff_psiXX/me_const");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").feature("surf1").feature().create("hght1", "Height");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("hght1").active(false);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("colortable", "GrayScale");
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
    model.result().export("anim1").set("plotgroup", "pg3");
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").run();

    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 2 - \u4f18\u5316");
    model.param("par2").set("index", "1");
    model.param("par2").descr("index", "\u89e3\u7d22\u5f15");
    model.param("par2").set("RXfit", "RrTF");
    model.param("par2").descr("RXfit", "\u8981\u62df\u5408\u7684\u7b2c\u4e00\u4e2a\u957f/\u77ed\u8f74");
    model.param("par2").set("RYfit", "RrTF");
    model.param("par2").descr("RYfit", "\u8981\u62df\u5408\u7684\u7b2c\u4e8c\u4e2a\u957f/\u77ed\u8f74");
    model.param("par2").set("thetafit", "0[rad]");
    model.param("par2").descr("thetafit", "\u8981\u62df\u5408\u7684\u503e\u89d2");
    model.param("par2").set("rho0fit", "rho0TF");
    model.param("par2").descr("rho0fit", "\u8981\u62df\u5408\u7684\u5cf0\u503c\u5bc6\u5ea6");
    model.param("par2").set("alphafit", "abs(Omega*(RXfit^2-RYfit^2)/(RXfit^2+RYfit^2)/wbar)");
    model.param("par2").descr("alphafit", "\u692d\u5706\u7387\u53c2\u6570");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().all();

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1")
         .set("fit_fn", "max(0,1-(cos(thetafit)*X+sin(thetafit)*Y)^2/RXfit^2-(cos(thetafit)*Y-sin(thetafit)*X)^2/RYfit^2)*rho0fit");
    model.component("comp1").variable("var1")
         .descr("fit_fn", "\u57fa\u4e8e TF \u5bc6\u5ea6\u5206\u5e03\u7684\u62df\u5408\u51fd\u6570");
    model.component("comp1").variable("var1").set("residual", "realdot(psi,psi)[m^-2]/L-fit_fn");
    model.component("comp1").variable("var1")
         .descr("residual", "\u62df\u5408\u51fd\u6570\u4e0e\u8ba1\u7b97\u7684\u6570\u636e\u4e4b\u95f4\u7684\u5dee");
    model.component("comp1").variable("var1").set("q0", "aveop1(realdot(residual,residual)/rho0TF^2)");
    model.component("comp1").variable("var1")
         .descr("q0", "\u76ee\u6807 - \u62df\u5408\u51fd\u6570\u4e0e\u8ba1\u7b97\u7684\u6570\u636e\u4e4b\u95f4\u7684\u6309\u6bd4\u4f8b\u7f29\u653e\u7684\u5e73\u5747\u5dee");

    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").setSolveFor("/physics/schr", false);
    model.study("std4").feature("stat").set("usesol", true);
    model.study("std4").feature("stat").set("notsolmethod", "sol");
    model.study("std4").feature("stat").set("notstudy", "std3");
    model.study("std4").feature("stat").set("notsolnum", "manual");
    model.study("std4").feature("stat").set("notmanualsolnum", "index");
    model.study("std4").label("\u7814\u7a76 4 - \u53c2\u6570\u4f30\u8ba1\u7684\u4f18\u5316");
    model.study("std4").create("param", "Parametric");
    model.study("std4").feature("param").setIndex("pname", "a", 0);
    model.study("std4").feature("param").setIndex("plistarr", "", 0);
    model.study("std4").feature("param").setIndex("punit", "m", 0);
    model.study("std4").feature("param").setIndex("pname", "a", 0);
    model.study("std4").feature("param").setIndex("plistarr", "", 0);
    model.study("std4").feature("param").setIndex("punit", "m", 0);
    model.study("std4").feature("param").setIndex("pname", "index", 0);
    model.study("std4").feature("param").setIndex("plistarr", "range(1,185)", 0);
    model.study("std4").create("opt", "Optimization");
    model.study("std4").feature("opt").set("optsolver", "ipopt");
    model.study("std4").feature("opt").set("optobj", new String[]{"comp1.q0"});
    model.study("std4").feature("opt")
         .set("descr", new String[]{"\u76ee\u6807 - \u62df\u5408\u51fd\u6570\u4e0e\u8ba1\u7b97\u7684\u6570\u636e\u4e4b\u95f4\u7684\u6309\u6bd4\u4f8b\u7f29\u653e\u7684\u5e73\u5747\u5dee"});
    model.study("std4").feature("opt").setIndex("pname", "a", 0);
    model.study("std4").feature("opt").setIndex("initval", "5.5[nm]", 0);
    model.study("std4").feature("opt").setIndex("scale", 1, 0);
    model.study("std4").feature("opt").setIndex("lbound", "", 0);
    model.study("std4").feature("opt").setIndex("ubound", "", 0);
    model.study("std4").feature("opt").setIndex("pname", "a", 0);
    model.study("std4").feature("opt").setIndex("initval", "5.5[nm]", 0);
    model.study("std4").feature("opt").setIndex("scale", 1, 0);
    model.study("std4").feature("opt").setIndex("lbound", "", 0);
    model.study("std4").feature("opt").setIndex("ubound", "", 0);
    model.study("std4").feature("opt").setIndex("pname", "alphafit", 1);
    model.study("std4").feature("opt").setIndex("initval", "abs(Omega*(RXfit^2-RYfit^2)/(RXfit^2+RYfit^2)/wbar)", 1);
    model.study("std4").feature("opt").setIndex("scale", 1, 1);
    model.study("std4").feature("opt").setIndex("lbound", "", 1);
    model.study("std4").feature("opt").setIndex("ubound", "", 1);
    model.study("std4").feature("opt").setIndex("pname", "alphafit", 1);
    model.study("std4").feature("opt").setIndex("initval", "abs(Omega*(RXfit^2-RYfit^2)/(RXfit^2+RYfit^2)/wbar)", 1);
    model.study("std4").feature("opt").setIndex("scale", 1, 1);
    model.study("std4").feature("opt").setIndex("lbound", "", 1);
    model.study("std4").feature("opt").setIndex("ubound", "", 1);
    model.study("std4").feature("opt").setIndex("pname", "epst", 2);
    model.study("std4").feature("opt").setIndex("initval", "0.032*step1((t+tau)/tau)*(1-step1((t-t_off)/tau))", 2);
    model.study("std4").feature("opt").setIndex("scale", 1, 2);
    model.study("std4").feature("opt").setIndex("lbound", "", 2);
    model.study("std4").feature("opt").setIndex("ubound", "", 2);
    model.study("std4").feature("opt").setIndex("pname", "epst", 2);
    model.study("std4").feature("opt").setIndex("initval", "0.032*step1((t+tau)/tau)*(1-step1((t-t_off)/tau))", 2);
    model.study("std4").feature("opt").setIndex("scale", 1, 2);
    model.study("std4").feature("opt").setIndex("lbound", "", 2);
    model.study("std4").feature("opt").setIndex("ubound", "", 2);
    model.study("std4").feature("opt").setIndex("pname", "epsX", 3);
    model.study("std4").feature("opt")
         .setIndex("initval", "(epst+sqrt(0.03*0.09+epst^2-0.03*0.09*epst^2))/(1-epst)", 3);
    model.study("std4").feature("opt").setIndex("scale", 1, 3);
    model.study("std4").feature("opt").setIndex("lbound", "", 3);
    model.study("std4").feature("opt").setIndex("ubound", "", 3);
    model.study("std4").feature("opt").setIndex("pname", "epsX", 3);
    model.study("std4").feature("opt")
         .setIndex("initval", "(epst+sqrt(0.03*0.09+epst^2-0.03*0.09*epst^2))/(1-epst)", 3);
    model.study("std4").feature("opt").setIndex("scale", 1, 3);
    model.study("std4").feature("opt").setIndex("lbound", "", 3);
    model.study("std4").feature("opt").setIndex("ubound", "", 3);
    model.study("std4").feature("opt").setIndex("pname", "RXfit", 0);
    model.study("std4").feature("opt").setIndex("scale", "RrTF", 0);
    model.study("std4").feature("opt").setIndex("lbound", "RrTF/4", 0);
    model.study("std4").feature("opt").setIndex("ubound", "RrTF*4", 0);
    model.study("std4").feature("opt").setIndex("pname", "RYfit", 1);
    model.study("std4").feature("opt").setIndex("scale", "RrTF", 1);
    model.study("std4").feature("opt").setIndex("lbound", "RrTF/4", 1);
    model.study("std4").feature("opt").setIndex("ubound", "RrTF*4", 1);
    model.study("std4").feature("opt").setIndex("pname", "thetafit", 2);
    model.study("std4").feature("opt").setIndex("scale", "pi", 2);
    model.study("std4").feature("opt").setIndex("lbound", "-pi", 2);
    model.study("std4").feature("opt").setIndex("ubound", "pi", 2);
    model.study("std4").feature("opt").setIndex("pname", "rho0fit", 3);
    model.study("std4").feature("opt").setIndex("scale", "rho0TF", 3);
    model.study("std4").feature("opt").setIndex("lbound", "rho0TF/8", 3);
    model.study("std4").feature("opt").setIndex("ubound", "rho0TF*3", 3);
    model.study("std4").feature("opt").set("probesel", "none");
    model.study("std4").createAutoSequences("all");

    model.sol().create("sol5");
    model.sol("sol5").study("std4");
    model.sol("sol5").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol5");
    model.batch("p1").run("compute");

    model.study("std4").feature("opt").set("probewindow", "");

    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u692d\u5706\u7387\u53c2\u6570\u548c\u89d2\u52a8\u91cf");
    model.result("pg7").set("data", "dset6");
    model.result("pg7").set("legendpos", "middleright");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "alphafit", 0);
    model.result("pg7").feature("glob1").set("xdata", "expr");
    model.result("pg7").feature("glob1").set("xdataexpr", "withsol('sol3',t,setind(t,index))");
    model.result("pg7").feature("glob1").set("xdataunit", "ms");
    model.result("pg7").feature("glob1").set("linestyle", "none");
    model.result("pg7").feature("glob1").set("linemarker", "point");
    model.result("pg7").run();
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result("pg7").run();
    model.result("pg7").feature().copy("tblp1", "pg2/tblp1");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("twoyaxes", true);
    model.result("pg7").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg7").run();
    model.result("pg4").run();
    model.result().duplicate("pg8", "pg4");
    model.result("pg8").run();
    model.result("pg8").label("\u6982\u7387\u5bc6\u5ea6 vs. \u65f6\u95f4");
    model.result("pg8").set("data", "none");
    model.result("pg8").set("titletype", "label");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").feature().remove("hght1");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").set("data", "dset3");
    model.result("pg8").feature("surf1").setIndex("looplevel", 1, 0);
    model.result("pg8").feature("surf1").set("expr", "schr.Pr/L");
    model.result("pg8").feature("surf1").set("unit", "1/um^3");
    model.result("pg8").run();
    model.result("pg8").create("ann1", "Annotation");
    model.result("pg8").feature("ann1").set("data", "dset3");
    model.result("pg8").feature("ann1").setIndex("looplevel", 1, 0);
    model.result("pg8").feature("ann1").set("text", "t=eval(t,ms) ms");
    model.result("pg8").feature("ann1").set("posyexpr", "-1.59*RrTF");
    model.result("pg8").feature("ann1").set("showpoint", false);
    model.result("pg8").feature("ann1").set("anchorpoint", "uppermiddle");
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf2", "surf1");
    model.result("pg8").run();
    model.result("pg8").feature("surf2").setIndex("looplevel", 11, 0);
    model.result("pg8").feature("surf2").set("inheritplot", "surf1");
    model.result("pg8").feature("surf2").create("def1", "Deform");
    model.result("pg8").run();
    model.result("pg8").feature("surf2").feature("def1").set("expr", new String[]{"3.3*RrTF", "0"});
    model.result("pg8").feature("surf2").feature("def1").set("scaleactive", true);
    model.result("pg8").feature("surf2").feature("def1").set("scale", 1);
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("ann2", "ann1");
    model.result("pg8").run();
    model.result("pg8").feature("ann2").setIndex("looplevel", 11, 0);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("ann2").feature().copy("def1", "pg8/surf2/def1");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf3", "surf2");
    model.result("pg8").run();

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg8").feature("surf3").setIndex("looplevel", 80, 0);
    model.result("pg8").run();
    model.result("pg8").feature("surf3").feature("def1").set("expr", new String[]{"6.6*RrTF", "0"});
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("ann3", "ann2");
    model.result("pg8").run();
    model.result("pg8").feature("ann3").setIndex("looplevel", 80, 0);
    model.result("pg8").run();
    model.result("pg8").feature("ann3").feature("def1").set("expr", new String[]{"6.6*RrTF", "0"});
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf4", "surf3");
    model.result("pg8").run();
    model.result("pg8").feature("surf4").setIndex("looplevel", 90, 0);
    model.result("pg8").run();
    model.result("pg8").feature("surf4").feature("def1").set("expr", new String[]{"0", "-3.6*RrTF"});
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("ann4", "ann3");
    model.result("pg8").run();
    model.result("pg8").feature("ann4").setIndex("looplevel", 90, 0);
    model.result("pg8").run();
    model.result("pg8").feature("ann4").feature("def1").set("expr", new String[]{"0", "-3.6*RrTF"});
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf5", "surf2");
    model.result("pg8").run();
    model.result("pg8").feature("surf5").setIndex("looplevel", 100, 0);
    model.result("pg8").run();
    model.result("pg8").feature("surf5").feature("def1").set("expr", new String[]{"3.3*RrTF", "-3.6*RrTF"});
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("ann5", "ann2");
    model.result("pg8").run();
    model.result("pg8").feature("ann5").setIndex("looplevel", 100, 0);
    model.result("pg8").run();
    model.result("pg8").feature("ann5").feature("def1").set("expr", new String[]{"3.3*RrTF", "-3.6*RrTF"});
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf6", "surf3");
    model.result("pg8").run();
    model.result("pg8").feature("surf6").setIndex("looplevel", 185, 0);
    model.result("pg8").run();
    model.result("pg8").feature("surf6").feature("def1").set("expr", new String[]{"6.6*RrTF", "-3.6*RrTF"});
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("ann6", "ann3");
    model.result("pg8").run();
    model.result("pg8").feature("ann6").setIndex("looplevel", 185, 0);
    model.result("pg8").run();
    model.result("pg8").feature("ann6").feature("def1").set("expr", new String[]{"6.6*RrTF", "-3.6*RrTF"});
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").run();
    model.result("pg9").label("\u68c0\u67e5\u62df\u5408\u5ea6");
    model.result("pg9").set("data", "dset6");
    model.result("pg9").setIndex("looplevel", 11, 0);
    model.result("pg9").create("con1", "Contour");
    model.result("pg9").feature("con1").label("\u7b49\u503c\u7ebf 1 - \u6570\u636e");
    model.result("pg9").feature("con1").set("expr", "(psi*conj(psi)[m^-2])/L");
    model.result("pg9").feature("con1").set("unit", "1/um^3");
    model.result("pg9").feature("con1").set("levelmethod", "levels");
    model.result("pg9").feature("con1").set("levels", "range(10,20,110)");
    model.result("pg9").feature("con1").set("coloring", "gradient");
    model.result("pg9").feature("con1").set("topcolor", "black");
    model.result("pg9").feature("con1").set("bottomcolor", "gray");
    model.result("pg9").feature().duplicate("con2", "con1");
    model.result("pg9").run();
    model.result("pg9").feature("con2").label("\u7b49\u503c\u7ebf 2 - \u62df\u5408");
    model.result("pg9").feature("con2").set("expr", "fit_fn");
    model.result("pg9").feature("con2").set("coloring", "colortable");
    model.result("pg9").run();

    model.study("std1").feature("eigv").set("useadvanceddisable", true);
    model.study("std1").feature("eigv")
         .set("disabledphysics", new String[]{"schr/ve3", "schr/ge1", "schr/init2", "schr/rotf1", "schr/diss1", "schr/ve4", "schr/ge2", "schr/init3"});
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat")
         .set("disabledphysics", new String[]{"schr/rotf1", "schr/diss1", "schr/ve4", "schr/ge2", "schr/init3"});

    model
         .title("\u65cb\u8f6c\u73bb\u8272-\u7231\u56e0\u65af\u5766\u51dd\u805a\u4f53\u4e2d\u7684\u6da1\u683c\u5f62\u6210");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u6c42\u89e3\u7531\u7b80\u8c10\u52bf\u9631\u675f\u7f1a\u7684\u65cb\u8f6c\u73bb\u8272-\u7231\u56e0\u65af\u5766\u51dd\u805a\u4f53\u4e2d\u6da1\u683c\u5f62\u6210\u7684 Gross-Pitaevskii \u65b9\u7a0b\u3002\u8be5\u65b9\u7a0b\u672c\u8d28\u4e0a\u662f\u975e\u7ebf\u6027\u5355\u7c92\u5b50\u859b\u5b9a\u8c14\u65b9\u7a0b\uff0c\u7c92\u5b50\u95f4\u7684\u76f8\u4e92\u4f5c\u7528\u901a\u8fc7\u4e0e\u5c40\u90e8\u7c92\u5b50\u5bc6\u5ea6\u6210\u6bd4\u4f8b\u7684\u52bf\u80fd\u8d21\u732e\u8868\u793a\u3002\u5177\u6709\u73b0\u8c61\u5b66\u963b\u5c3c\u7684\u65cb\u8f6c\u5750\u6807\u7cfb\u4e2d\u7684\u65f6\u95f4\u6f14\u5316\u901a\u8fc7\u201c\u859b\u5b9a\u8c14\u65b9\u7a0b\u201d\u7269\u7406\u573a\u63a5\u53e3\u7684\u5185\u7f6e\u7279\u5f81\u8fdb\u884c\u914d\u7f6e\u3002\u4ece\u6a21\u578b\u4e2d\u53ef\u4ee5\u770b\u5230\uff0c\u6da1\u65cb\u6838\u4ece\u51dd\u805a\u4f53\u7684\u5916\u56f4\u5f00\u59cb\u5f62\u6210\u3002\u968f\u540e\uff0c\u8be5\u7cfb\u7edf\u7ecf\u5386\u4e86\u4e00\u6bb5\u660e\u663e\u7684\u52a8\u529b\u5b66\u4e0d\u7a33\u5b9a\u671f\uff0c\u7136\u540e\u8fdb\u5165\u6da1\u683c\u7684\u4f4e\u80fd\u6001\u3002\u672c\u4f8b\u4f7f\u7528\u201c\u4f18\u5316\u6a21\u5757\u201d\u5bf9\u6570\u503c\u7ed3\u679c\u8fdb\u884c\u53c2\u6570\u4f30\u8ba1\u3002\u4eff\u771f\u5f97\u5230\u7684\u521d\u59cb\u632f\u8361\u7684\u65f6\u95f4\u5c3a\u5ea6\u548c\u692d\u5706\u7387\u53c2\u6570\u7684\u6700\u7ec8\u574d\u584c\u4e0e Madison \u7b49\u53d1\u8868\u7684\u5b9e\u9a8c\u6570\u636e\u543b\u5408\u826f\u597d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();
    model.sol("sol18").clearSolutionData();
    model.sol("sol19").clearSolutionData();
    model.sol("sol20").clearSolutionData();
    model.sol("sol21").clearSolutionData();
    model.sol("sol22").clearSolutionData();
    model.sol("sol23").clearSolutionData();
    model.sol("sol24").clearSolutionData();
    model.sol("sol25").clearSolutionData();
    model.sol("sol26").clearSolutionData();
    model.sol("sol27").clearSolutionData();
    model.sol("sol28").clearSolutionData();
    model.sol("sol29").clearSolutionData();
    model.sol("sol30").clearSolutionData();
    model.sol("sol31").clearSolutionData();
    model.sol("sol32").clearSolutionData();
    model.sol("sol33").clearSolutionData();
    model.sol("sol34").clearSolutionData();
    model.sol("sol35").clearSolutionData();
    model.sol("sol36").clearSolutionData();
    model.sol("sol37").clearSolutionData();
    model.sol("sol38").clearSolutionData();
    model.sol("sol39").clearSolutionData();
    model.sol("sol40").clearSolutionData();
    model.sol("sol41").clearSolutionData();
    model.sol("sol42").clearSolutionData();
    model.sol("sol43").clearSolutionData();
    model.sol("sol44").clearSolutionData();
    model.sol("sol45").clearSolutionData();
    model.sol("sol46").clearSolutionData();
    model.sol("sol47").clearSolutionData();
    model.sol("sol48").clearSolutionData();
    model.sol("sol49").clearSolutionData();
    model.sol("sol50").clearSolutionData();
    model.sol("sol51").clearSolutionData();
    model.sol("sol52").clearSolutionData();
    model.sol("sol53").clearSolutionData();
    model.sol("sol54").clearSolutionData();
    model.sol("sol55").clearSolutionData();
    model.sol("sol56").clearSolutionData();
    model.sol("sol57").clearSolutionData();
    model.sol("sol58").clearSolutionData();
    model.sol("sol59").clearSolutionData();
    model.sol("sol60").clearSolutionData();
    model.sol("sol61").clearSolutionData();
    model.sol("sol62").clearSolutionData();
    model.sol("sol63").clearSolutionData();
    model.sol("sol64").clearSolutionData();
    model.sol("sol65").clearSolutionData();
    model.sol("sol66").clearSolutionData();
    model.sol("sol67").clearSolutionData();
    model.sol("sol68").clearSolutionData();
    model.sol("sol69").clearSolutionData();
    model.sol("sol70").clearSolutionData();
    model.sol("sol71").clearSolutionData();
    model.sol("sol72").clearSolutionData();
    model.sol("sol73").clearSolutionData();
    model.sol("sol74").clearSolutionData();
    model.sol("sol75").clearSolutionData();
    model.sol("sol76").clearSolutionData();
    model.sol("sol77").clearSolutionData();
    model.sol("sol78").clearSolutionData();
    model.sol("sol79").clearSolutionData();
    model.sol("sol80").clearSolutionData();
    model.sol("sol81").clearSolutionData();
    model.sol("sol82").clearSolutionData();
    model.sol("sol83").clearSolutionData();
    model.sol("sol84").clearSolutionData();
    model.sol("sol85").clearSolutionData();
    model.sol("sol86").clearSolutionData();
    model.sol("sol87").clearSolutionData();
    model.sol("sol88").clearSolutionData();
    model.sol("sol89").clearSolutionData();
    model.sol("sol90").clearSolutionData();
    model.sol("sol91").clearSolutionData();
    model.sol("sol92").clearSolutionData();
    model.sol("sol93").clearSolutionData();
    model.sol("sol94").clearSolutionData();
    model.sol("sol95").clearSolutionData();
    model.sol("sol96").clearSolutionData();
    model.sol("sol97").clearSolutionData();
    model.sol("sol98").clearSolutionData();
    model.sol("sol99").clearSolutionData();
    model.sol("sol100").clearSolutionData();
    model.sol("sol101").clearSolutionData();
    model.sol("sol102").clearSolutionData();
    model.sol("sol103").clearSolutionData();
    model.sol("sol104").clearSolutionData();
    model.sol("sol105").clearSolutionData();
    model.sol("sol106").clearSolutionData();
    model.sol("sol107").clearSolutionData();
    model.sol("sol108").clearSolutionData();
    model.sol("sol109").clearSolutionData();
    model.sol("sol110").clearSolutionData();
    model.sol("sol111").clearSolutionData();
    model.sol("sol112").clearSolutionData();
    model.sol("sol113").clearSolutionData();
    model.sol("sol114").clearSolutionData();
    model.sol("sol115").clearSolutionData();
    model.sol("sol116").clearSolutionData();
    model.sol("sol117").clearSolutionData();
    model.sol("sol118").clearSolutionData();
    model.sol("sol119").clearSolutionData();
    model.sol("sol120").clearSolutionData();
    model.sol("sol121").clearSolutionData();
    model.sol("sol122").clearSolutionData();
    model.sol("sol123").clearSolutionData();
    model.sol("sol124").clearSolutionData();
    model.sol("sol125").clearSolutionData();
    model.sol("sol126").clearSolutionData();
    model.sol("sol127").clearSolutionData();
    model.sol("sol128").clearSolutionData();
    model.sol("sol129").clearSolutionData();
    model.sol("sol130").clearSolutionData();
    model.sol("sol131").clearSolutionData();
    model.sol("sol132").clearSolutionData();
    model.sol("sol133").clearSolutionData();
    model.sol("sol134").clearSolutionData();
    model.sol("sol135").clearSolutionData();
    model.sol("sol136").clearSolutionData();
    model.sol("sol137").clearSolutionData();
    model.sol("sol138").clearSolutionData();
    model.sol("sol139").clearSolutionData();
    model.sol("sol140").clearSolutionData();
    model.sol("sol141").clearSolutionData();
    model.sol("sol142").clearSolutionData();
    model.sol("sol143").clearSolutionData();
    model.sol("sol144").clearSolutionData();
    model.sol("sol145").clearSolutionData();
    model.sol("sol146").clearSolutionData();
    model.sol("sol147").clearSolutionData();
    model.sol("sol148").clearSolutionData();
    model.sol("sol149").clearSolutionData();
    model.sol("sol150").clearSolutionData();
    model.sol("sol151").clearSolutionData();
    model.sol("sol152").clearSolutionData();
    model.sol("sol153").clearSolutionData();
    model.sol("sol154").clearSolutionData();
    model.sol("sol155").clearSolutionData();
    model.sol("sol156").clearSolutionData();
    model.sol("sol157").clearSolutionData();
    model.sol("sol158").clearSolutionData();
    model.sol("sol159").clearSolutionData();
    model.sol("sol160").clearSolutionData();
    model.sol("sol161").clearSolutionData();
    model.sol("sol162").clearSolutionData();
    model.sol("sol163").clearSolutionData();
    model.sol("sol164").clearSolutionData();
    model.sol("sol165").clearSolutionData();
    model.sol("sol166").clearSolutionData();
    model.sol("sol167").clearSolutionData();
    model.sol("sol168").clearSolutionData();
    model.sol("sol169").clearSolutionData();
    model.sol("sol170").clearSolutionData();
    model.sol("sol171").clearSolutionData();
    model.sol("sol172").clearSolutionData();
    model.sol("sol173").clearSolutionData();
    model.sol("sol174").clearSolutionData();
    model.sol("sol175").clearSolutionData();
    model.sol("sol176").clearSolutionData();
    model.sol("sol177").clearSolutionData();
    model.sol("sol178").clearSolutionData();
    model.sol("sol179").clearSolutionData();
    model.sol("sol180").clearSolutionData();
    model.sol("sol181").clearSolutionData();
    model.sol("sol182").clearSolutionData();
    model.sol("sol183").clearSolutionData();
    model.sol("sol184").clearSolutionData();
    model.sol("sol185").clearSolutionData();
    model.sol("sol186").clearSolutionData();
    model.sol("sol187").clearSolutionData();
    model.sol("sol188").clearSolutionData();
    model.sol("sol189").clearSolutionData();
    model.sol("sol190").clearSolutionData();

    model.label("vortex_lattice_formation_in_a_rotating_bose_einstein_condensate.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
