/*
 * gross_pitaevskii_equation_for_bose_einstein_condensation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:52 by COMSOL 6.3.0.290. */
public class gross_pitaevskii_equation_for_bose_einstein_condensation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Quantum_Systems");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

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

    model.param().set("w0", "2*pi*10[Hz]");
    model.param().descr("w0", "\u9677\u9631\u9891\u7387");
    model.param().set("ma", "86.909[g/mol]/N_A_const");
    model.param().descr("ma", "Rb-87 \u7684\u539f\u5b50\u91cf");
    model.param().set("as", "5.2[nm]");
    model.param().descr("as", "\u6563\u5c04\u957f\u5ea6");
    model.param().set("U0", "4*pi*hbar_const^2*as/ma");
    model.param().descr("U0", "\u76f8\u4e92\u4f5c\u7528\u5f3a\u5ea6");
    model.param().set("N0", "1");
    model.param().descr("N0", "\u539f\u5b50\u6570");
    model.param().set("wr", "w0*sqrt(3)");
    model.param().descr("wr", "\u6a2a\u5411\u9677\u9631\u9891\u7387");
    model.param().set("Rr0", "(15*U0*w0*N0/(4*pi*ma*wr^3))^0.2");
    model.param().descr("Rr0", "\u6a2a\u5411 T-F \u5927\u5c0f");
    model.param().set("Rz0", "(15*U0*wr^2*N0/(4*pi*ma*w0^4))^0.2");
    model.param().descr("Rz0", "\u7eb5\u5411 T-F \u5927\u5c0f");
    model.param().set("rho0", "15*N0/(8*pi*Rr0^2*Rz0)");
    model.param().descr("rho0", "T-F \u5cf0\u503c\u5bc6\u5ea6");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 50);
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("rot", -90);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("schr").prop("ModelProperties").set("lambda_scale", "1[nK]*k_B_const");
    model.component("comp1").physics("schr").feature("meff1").label("\u539f\u5b50\u91cf");
    model.component("comp1").physics("schr").feature("meff1")
         .set("meffe_psi", new String[]{"ma", "0", "0", "0", "ma", "0", "0", "0", "ma"});
    model.component("comp1").physics("schr").feature("ve1").label("\u9677\u9631\u52bf\u80fd");
    model.component("comp1").physics("schr").feature("ve1").set("Ve_src", "userdef");
    model.component("comp1").physics("schr").feature("ve1").set("Ve", "0.5*ma*w0^2*(3*r^2+z^2)");

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1\uff1a\u521d\u59cb\u6761\u4ef6\u7684\u7279\u5f81\u503c");
    model.study("std1").feature("eigv").set("neigs", 1);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u7279\u5f81\u503c");
    model.result().numerical("gev1").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result().numerical("gev1").set("expr", new String[]{"schr.Ei", "schr.int(2*schr.Pr*pi*R)"});
    model.result().numerical("gev1").setIndex("unit", "eV", 0);
    model.result().numerical("gev1").set("descr", new String[]{"\u7279\u5f81\u80fd\u91cf", "\u603b\u6982\u7387"});
    model.result().numerical("gev1").setIndex("unit", "eV", 0);
    model.result().numerical("gev1").setIndex("unit", "eV", 0);
    model.result().numerical("gev1").setIndex("unit", "eV", 0);
    model.result().numerical("gev1").setIndex("unit", "eV", 0);
    model.result().numerical("gev1").set("data", "dset1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u5f52\u4e00\u5316\u6ce2\u51fd\u6570 (schr)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u5b9e\u90e8");
    model.result("pg1").feature("surf1").set("expr", "schr.Psi_psi");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("hght1", "Height");
    model.result("pg1").feature().create("surf2", "Surface");
    model.result("pg1").feature("surf2").label("\u865a\u90e8");
    model.result("pg1").feature("surf2").set("expr", "imag(schr.Psi_psi)");
    model.result("pg1").feature("surf2").set("smooth", "internal");
    model.result("pg1").feature("surf2").set("data", "parent");
    model.result("pg1").feature("surf2").set("inheritplot", "surf1");
    model.result("pg1").feature("surf2").feature().create("hght1", "Height");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u6982\u7387\u5bc6\u5ea6 (schr)");
    model.result("pg2").set("dataisaxisym", "off");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "schr.Pr");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("hght1", "Height");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u52bf\u80fd (schr)");
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "schr.V_psi");
    model.result("pg3").feature("surf1").set("unit", "eV");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature("surf1").feature().create("hght1", "Height");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u6709\u6548\u8d28\u91cf (schr)");
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "schr.meff_psiRR/me_const");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature("surf1").feature().create("hght1", "Height");
    model.result("pg1").run();

    model.component("comp1").physics("schr").feature().duplicate("ve2", "ve1");
    model.component("comp1").physics("schr").feature("ve2").label("\u76f8\u4e92\u4f5c\u7528\u80fd");
    model.component("comp1").physics("schr").feature("ve2").set("Ve", "N0*U0*schr.Pr");
    model.component("comp1").physics("schr").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("schr").feature("ge1").setIndex("name", "E0", 0, 0);
    model.component("comp1").physics("schr").feature("ge1")
         .setIndex("equation", "(1-schr.int(2*pi*r*schr.Pr))", 0, 0);
    model.component("comp1").physics("schr").feature("ge1").setIndex("initialValueU", 1, 0, 0);
    model.component("comp1").physics("schr").prop("ModelProperties").set("E", "E0*1[nK]*k_B_const");
    model.component("comp1").physics("schr").feature().duplicate("init2", "init1");
    model.component("comp1").physics("schr").feature("init2").set("psi", "schr.Psi");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/schr", true);
    model.study("std2").feature("stat").set("useinitsol", true);
    model.study("std2").feature("stat").set("initstudy", "std1");
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "w0", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "Hz", 0);
    model.study("std2").feature("stat").setIndex("pname", "w0", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "Hz", 0);
    model.study("std2").feature("stat").setIndex("pname", "N0", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "1 100 1e4 1e6", 0);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u51dd\u805a\u4f53\u7684\u7a33\u6001");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u6ce2\u51fd\u6570 (schr)");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 4, 0);
    model.result("pg5").set("dataisaxisym", "off");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u5b9e\u90e8");
    model.result("pg5").feature("surf1").set("expr", "psi");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature("surf1").feature().create("hght1", "Height");
    model.result("pg5").feature().create("surf2", "Surface");
    model.result("pg5").feature("surf2").label("\u865a\u90e8");
    model.result("pg5").feature("surf2").set("expr", "imag(psi)");
    model.result("pg5").feature("surf2").set("smooth", "internal");
    model.result("pg5").feature("surf2").set("data", "parent");
    model.result("pg5").feature("surf2").set("inheritplot", "surf1");
    model.result("pg5").feature("surf2").feature().create("hght1", "Height");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u6982\u7387\u5bc6\u5ea6 (schr) 1");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 4, 0);
    model.result("pg6").set("dataisaxisym", "off");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "schr.Pr");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").feature("surf1").feature().create("hght1", "Height");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u52bf\u80fd (schr) 1");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevel", 4, 0);
    model.result("pg7").set("dataisaxisym", "off");
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "schr.V_psi");
    model.result("pg7").feature("surf1").set("unit", "eV");
    model.result("pg7").feature("surf1").set("smooth", "internal");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result("pg7").feature("surf1").feature().create("hght1", "Height");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").label("\u6709\u6548\u8d28\u91cf (schr) 1");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").setIndex("looplevel", 4, 0);
    model.result("pg8").set("dataisaxisym", "off");
    model.result("pg8").feature().create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "schr.meff_psiRR/me_const");
    model.result("pg8").feature("surf1").set("smooth", "internal");
    model.result("pg8").feature("surf1").set("data", "parent");
    model.result("pg8").feature("surf1").feature().create("hght1", "Height");
    model.result("pg5").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u4e0e Thomas\u2013Fermi \u8fd1\u4f3c\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").setIndex("looplevelinput", "last", 0);
    model.result("pg9").set("titletype", "manual");
    model.result("pg9")
         .set("title", "\u5c06\u8f74\u5411\u548c\u5f84\u5411\u4e0a\u6982\u7387\u5bc6\u5ea6\u7684\u8ba1\u7b97\u7ed3\u679c\u4e0e Thomas\u2013Fermi \u8fd1\u4f3c\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83");
    model.result("pg9").set("xlabelactive", true);
    model.result("pg9").set("xlabel", "\u8ddd\u51dd\u805a\u4f53\u4e2d\u5fc3\u7684\u8ddd\u79bb (um)");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u6982\u7387\u5bc6\u5ea6 (1/um<sup>3</sup>)");
    model.result("pg9").create("lngr1", "LineGraph");
    model.result("pg9").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg9").feature("lngr1").set("linewidth", "preference");
    model.result("pg9").feature("lngr1").selection().set(1, 2);
    model.result("pg9").feature("lngr1").set("expr", "schr.Pr");
    model.result("pg9").feature("lngr1").set("unit", "1/um^3");
    model.result("pg9").feature("lngr1").set("descractive", true);
    model.result("pg9").feature("lngr1").set("descr", "\u8f74\u5411");
    model.result("pg9").feature("lngr1").set("xdata", "expr");
    model.result("pg9").feature("lngr1").set("xdataexpr", "z");
    model.result("pg9").feature("lngr1").set("legend", true);
    model.result("pg9").feature("lngr1").set("autodescr", true);
    model.result("pg9").feature().duplicate("lngr2", "lngr1");
    model.result("pg9").run();
    model.result("pg9").feature("lngr2").set("expr", "max(0,1-z^2/Rz0^2)*rho0/N0");
    model.result("pg9").feature("lngr2").set("descr", "\u8f74\u5411 TF");
    model.result("pg9").feature("lngr2").set("linestyle", "dashed");
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").set("data", "dset2");
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").set("data", "mir1");
    model.result().dataset("cln1").set("bounded", false);
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("lngr3", "lngr1");
    model.result("pg9").run();
    model.result("pg9").feature("lngr3").set("data", "cln1");
    model.result("pg9").feature("lngr3").setIndex("looplevelinput", "last", 0);
    model.result("pg9").feature("lngr3").set("descr", "\u5f84\u5411");
    model.result("pg9").feature("lngr3").set("xdataexpr", "cln1x");
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("lngr4", "lngr2");
    model.result("pg9").run();
    model.result("pg9").feature("lngr4").set("data", "cln1");
    model.result("pg9").feature("lngr4").setIndex("looplevelinput", "last", 0);
    model.result("pg9").feature("lngr4").set("expr", "max(0,1-r^2/Rr0^2)*rho0/N0");
    model.result("pg9").feature("lngr4").set("descr", "\u5f84\u5411 TF");
    model.result("pg9").feature("lngr4").set("xdataexpr", "cln1x");
    model.result("pg9").run();
    model.result("pg5").run();
    model.result().duplicate("pg10", "pg5");
    model.result("pg10").run();
    model.result("pg10").label("\u6c47\u603b\u56fe");
    model.result("pg10").run();
    model.result("pg10").feature().remove("surf2");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg7").run();
    model.result("pg10").run();
    model.result("pg10").feature().copy("surf2", "pg7/surf1");
    model.result("pg10").run();
    model.result("pg10").feature("surf2").set("coloring", "uniform");
    model.result("pg10").feature("surf2").set("color", "gray");
    model.result("pg10").run();

    model.study("std1").feature("eigv").set("useadvanceddisable", true);
    model.study("std1").feature("eigv").set("disabledphysics", new String[]{"schr/ve2", "schr/ge1", "schr/init2"});

    model.result("pg10").run();

    model.title("\u73bb\u8272-\u7231\u56e0\u65af\u5766\u51dd\u805a Gross-Pitaevskii \u65b9\u7a0b");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u4f7f\u7528\u201c\u534a\u5bfc\u4f53\u6a21\u5757\u201d\u4e2d\u7684\u201c\u859b\u5b9a\u8c14\u65b9\u7a0b\u201d\u7269\u7406\u573a\u63a5\u53e3\u6c42\u89e3\u8c10\u632f\u52bf\u9631\u4e2d\u73bb\u8272-\u7231\u56e0\u65af\u5766\u51dd\u805a\u57fa\u6001\u7684 Gross-Pitaevskii \u65b9\u7a0b\uff0c\u6b64\u65b9\u7a0b\u672c\u8d28\u4e0a\u662f\u975e\u7ebf\u6027\u5355\u7c92\u5b50\u859b\u5b9a\u8c14\u65b9\u7a0b\uff0c\u5176\u52bf\u80fd\u8d21\u732e\u4e0e\u5c40\u90e8\u7c92\u5b50\u5bc6\u5ea6\u6210\u6b63\u6bd4\u3002\u7279\u5f81\u503c\u7814\u7a76\u4e0d\u9002\u5408\u6c42\u89e3\u6b64\u7c7b\u975e\u7ebf\u6027\u7279\u5f81\u503c\u95ee\u9898\uff0c\u56e0\u800c\u8fd9\u91cc\u4f7f\u7528\u7a33\u6001\u7814\u7a76\u4ee5\u53ca\u4e00\u4e2a\u6267\u884c\u6ce2\u51fd\u6570\u5f52\u4e00\u5316\u7684\u5168\u5c40\u65b9\u7a0b\u6765\u6c42\u89e3\u57fa\u6001\u89e3\u3002\u6b63\u5982\u6211\u4eec\u9884\u8ba1\u7684\uff0c\u5927\u91cf\u7c92\u5b50\u7684\u7ed3\u679c\u4e0e Thomas-Fermi \u8fd1\u4f3c\u76f8\u5f53\u4e00\u81f4\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("gross_pitaevskii_equation_for_bose_einstein_condensation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
