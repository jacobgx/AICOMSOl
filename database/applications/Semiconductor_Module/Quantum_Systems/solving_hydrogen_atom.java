/*
 * solving_hydrogen_atom.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:53 by COMSOL 6.3.0.290. */
public class solving_hydrogen_atom {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Quantum_Systems");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

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

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("A0", "15*a0_const", "\u4eff\u771f\u57df\u534a\u5f84");
    model.param().set("kC", "1/(4*pi*epsilon0_const)", "\u5e93\u4ed1\u5e38\u6570");
    model.param().set("h0", "0.2*a0_const", "\u539f\u70b9\u5904\u7684\u7f51\u683c\u5927\u5c0f");
    model.param().set("hg", "1/(2*a0_const)", "\u7f51\u683c\u589e\u957f\u6bd4\u4f8b");
    model.param()
         .set("RH", "me_const*e_const^4/(8*h_const^2*epsilon0_const^2)/(1[eV])", "\u91cc\u5fb7\u4f2f\u5e38\u6570\uff08\u5355\u4f4d eV\uff09");
    model.param().set("E1", "-RH", "n=1 \u7279\u5f81\u80fd\u91cf (eV)");
    model.param().set("E2", "-RH/4", "n=2 \u7279\u5f81\u80fd\u91cf (eV)");
    model.param().set("Eext", "0.01[V]/a0_const", "\u5916\u90e8\u7535\u573a");
    model.param()
         .set("E2StarkLow", "E2-3*e_const*Eext*a0_const/1[eV]", "n=2 (eV) \u65f6\u7684\u65af\u5854\u514b\u5206\u88c2\u4f4e\u7279\u5f81\u80fd\u91cf");
    model.param()
         .set("E2StarkHigh", "E2+3*e_const*Eext*a0_const/1[eV]", "n=2 (eV) \u65f6\u7684\u65af\u5854\u514b\u5206\u88c2\u9ad8\u7279\u5f81\u80fd\u91cf");

    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "A0+0.1*A0");
    model.component("comp1").geom("geom1").feature("sph1").setIndex("layer", "0.1*A0", 0);
    model.component("comp1").geom("geom1").run("sph1");
    model.component("comp1").geom("geom1").create("pt1", "Point");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("Psi100", "exp(-sys2.r/a0_const)/(sqrt(pi*a0_const^3))");
    model.component("comp1").variable("var1").descr("Psi100", "\u57fa\u6001");
    model.component("comp1").variable("var1")
         .set("Psi200", "0.25*(2-sys2.r/a0_const)/(sqrt(2*pi*a0_const^3))*exp(-sys2.r/a0_const/2)");
    model.component("comp1").variable("var1").descr("Psi200", "n=2\uff0cl,m=0 \u7279\u5f81\u6001");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u4eff\u771f\u57df");
    model.component("comp1").selection("sel1").set(5);
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("\u65e0\u9650\u5143\u57df");
    model.component("comp1").selection("com1").set("input", new String[]{"sel1"});
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("adj1").set("input", new String[]{"sel1", "com1"});
    model.component("comp1").selection().create("adj2", "Adjacent");
    model.component("comp1").selection("adj2").label("\u5185\u90e8\u8fb9\u754c");
    model.component("comp1").selection("adj2").set("input", new String[]{"sel1"});

    model.component("comp1").coordSystem().create("sys2", "Spherical");
    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");
    model.component("comp1").coordSystem("ie1").selection().named("com1");
    model.component("comp1").coordSystem("ie1").set("ScalingType", "Spherical");

    model.component("comp1").physics("schr").prop("ModelProperties").set("E", "-15[eV]");
    model.component("comp1").physics("schr").feature("meff1")
         .set("meffe_psi", new String[]{"me_const", "0", "0", "0", "me_const", "0", "0", "0", "me_const"});
    model.component("comp1").physics("schr").feature("ve1").label("\u539f\u5b50\u6838");
    model.component("comp1").physics("schr").feature("ve1").set("Ve_src", "userdef");
    model.component("comp1").physics("schr").feature("ve1").set("Ve", "-kC*e_const^2/sys2.r");
    model.component("comp1").physics("schr").create("zprb1", "ZeroProbability", 2);
    model.component("comp1").physics("schr").feature("zprb1").selection().named("adj1");
    model.component("comp1").physics("schr").create("ve2", "ElectronPotentialEnergy", 3);
    model.component("comp1").physics("schr").feature("ve2").label("\u6cbf z \u8f74\u7684\u5916\u90e8\u573a");
    model.component("comp1").physics("schr").feature("ve2").selection().all();
    model.component("comp1").physics("schr").feature("ve2").set("Ve_src", "userdef");
    model.component("comp1").physics("schr").feature("ve2").set("Ve", "e_const*Eext*z");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "10*h0");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "0.1*h0");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("se1", "SizeExpression");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("se1")
         .set("sizeexpr", "(1+sqrt(x^2+y^2+z^2)*hg)*h0");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("com1");
    model.component("comp1").mesh("mesh1").feature("swe1").selection("sourceface").named("adj2");
    model.component("comp1").mesh("mesh1").feature("swe1").selection("targetface").named("adj1");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");

    model.study("std1").label("\u65e0\u5916\u90e8\u573a");
    model.study("std1").feature("eigv").set("neigs", 5);
    model.study("std1").feature("eigv").set("shift", "-15");
    model.study("std1").feature("eigv").set("useadvanceddisable", true);
    model.study("std1").feature("eigv").set("disabledphysics", new String[]{"schr/ve2"});
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u7279\u5f81\u503c");
    model.result().numerical("gev1").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result().numerical("gev1").set("expr", new String[]{"schr.Ei", "schr.int(schr.Pr)"});
    model.result().numerical("gev1").setIndex("unit", "eV", 0);
    model.result().numerical("gev1").set("descr", new String[]{"\u7279\u5f81\u80fd\u91cf", "\u603b\u6982\u7387"});
    model.result().numerical("gev1").setIndex("unit", "eV", 0);
    model.result().numerical("gev1").setIndex("unit", "eV", 0);
    model.result().numerical("gev1").setIndex("unit", "eV", 0);
    model.result().numerical("gev1").setIndex("unit", "eV", 0);
    model.result().numerical("gev1").set("data", "dset1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u5f52\u4e00\u5316\u6ce2\u51fd\u6570 (schr)");
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", "schr.Psi_psi");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u5f52\u4e00\u5316\u6ce2\u51fd\u6570 (schr) 1");
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", "imag(schr.Psi_psi)");
    model.result("pg2").feature("mslc1").set("smooth", "internal");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6982\u7387\u5bc6\u5ea6 (schr)");
    model.result("pg3").feature().create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").set("expr", "schr.Pr");
    model.result("pg3").feature("mslc1").set("smooth", "internal");
    model.result("pg3").feature("mslc1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u52bf\u80fd (schr)");
    model.result("pg4").feature().create("mslc1", "Multislice");
    model.result("pg4").feature("mslc1").set("expr", "schr.V_psi");
    model.result("pg4").feature("mslc1").set("unit", "eV");
    model.result("pg4").feature("mslc1").set("smooth", "internal");
    model.result("pg4").feature("mslc1").set("data", "parent");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u6709\u6548\u8d28\u91cf (schr)");
    model.result("pg5").feature().create("mslc1", "Multislice");
    model.result("pg5").feature("mslc1").set("expr", "schr.meff_psiXX/me_const");
    model.result("pg5").feature("mslc1").set("smooth", "internal");
    model.result("pg5").feature("mslc1").set("data", "parent");
    model.result("pg1").run();
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", 0, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "A0", 1, 2);
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("1s \u5f84\u5411\u6982\u7387");
    model.result("pg6").set("data", "cln1");
    model.result("pg6").setIndex("looplevelinput", "first", 0);
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "r/a<sub>0</sub>");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6")
         .set("ylabel", "\u5f84\u5411\u6982\u7387\uff1a4\\pi r<sup>2</sup>|\\psi<sub>100</sub>|<sup>2</sup>");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").set("expr", "4*pi*sys2.r^2*(comp1.Psi100)^2");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "sys2.r/a0_const");
    model.result("pg6").feature("lngr1").set("linewidth", 3);
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").set("legendmethod", "manual");
    model.result("pg6").feature("lngr1").setIndex("legends", "\u89e3\u6790", 0);
    model.result("pg6").feature("lngr1").set("resolution", "finer");
    model.result("pg6").run();
    model.result("pg6").create("lngr2", "LineGraph");
    model.result("pg6").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr2").set("linewidth", "preference");
    model.result("pg6").feature("lngr2").set("expr", "4*pi*sys2.r^2*schr.Pr");
    model.result("pg6").feature("lngr2").set("xdata", "expr");
    model.result("pg6").feature("lngr2").set("xdataexpr", "sys2.r/a0_const");
    model.result("pg6").feature("lngr2").set("linestyle", "none");
    model.result("pg6").feature("lngr2").set("linewidth", 3);
    model.result("pg6").feature("lngr2").set("linemarker", "point");
    model.result("pg6").feature("lngr2").set("markerpos", "interp");
    model.result("pg6").feature("lngr2").set("markers", 50);
    model.result("pg6").feature("lngr2").set("legend", true);
    model.result("pg6").feature("lngr2").set("legendmethod", "manual");
    model.result("pg6").feature("lngr2").setIndex("legends", "\u4eff\u771f", 0);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("2s \u5f84\u5411\u6982\u7387");
    model.result("pg7").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg7").setIndex("looplevelindices", 2, 0);
    model.result("pg7")
         .set("ylabel", "\u5f84\u5411\u6982\u7387\uff1a4\\pi r<sup>2</sup>|\\psi<sub>200</sub>|<sup>2</sup>");
    model.result("pg7").run();
    model.result("pg7").feature("lngr1").set("expr", "4*pi*sys2.r^2*(comp1.Psi200)^2");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").run();
    model.result("pg8").label("\u672a\u53d7\u6270\u52a8\u7684\u8f68\u9053\u5f62\u72b6");
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").set("title", "\u672a\u53d7\u6270\u52a8\u7684\u8f68\u9053\u5f62\u72b6");
    model.result("pg8").set("edges", false);
    model.result("pg8").set("showlegends", false);
    model.result("pg8").create("iso1", "Isosurface");
    model.result("pg8").feature("iso1").set("expr", "schr.Pr");
    model.result("pg8").run();
    model.result("pg8").create("iso2", "Isosurface");
    model.result("pg8").feature("iso2").set("data", "dset1");
    model.result("pg8").feature("iso2").set("looplevel", new int[]{2});
    model.result("pg8").feature("iso2").set("expr", "schr.Pr");
    model.result("pg8").feature("iso2").set("interactive", true);
    model.result("pg8").feature("iso2").set("shift", -2.45E28);
    model.result("pg8").feature("iso2").create("trn1", "Transformation");
    model.result("pg8").run();
    model.result("pg8").feature("iso2").feature("trn1").set("move", new String[]{"7E-10", "0", "0"});
    model.result("pg8").run();
    model.result("pg8").create("iso3", "Isosurface");
    model.result("pg8").feature("iso3").set("data", "dset1");
    model.result("pg8").feature("iso3").set("looplevel", new int[]{3});
    model.result("pg8").feature("iso3").set("expr", "schr.Pr");
    model.result("pg8").feature("iso3").create("trn1", "Transformation");
    model.result("pg8").run();
    model.result("pg8").feature("iso3").feature("trn1").set("move", new String[]{"15E-10", "0", "0"});
    model.result("pg8").run();
    model.result("pg8").create("iso4", "Isosurface");
    model.result("pg8").feature("iso4").set("data", "dset1");
    model.result("pg8").feature("iso4").set("looplevel", new int[]{4});
    model.result("pg8").feature("iso4").set("expr", "schr.Pr");
    model.result("pg8").feature("iso4").create("trn1", "Transformation");
    model.result("pg8").run();
    model.result("pg8").feature("iso4").feature("trn1").set("move", new String[]{"15E-10", "0", "7.5E-10"});
    model.result("pg8").run();
    model.result("pg8").create("iso5", "Isosurface");
    model.result("pg8").feature("iso5").set("data", "dset1");
    model.result("pg8").feature("iso5").set("looplevel", new int[]{5});
    model.result("pg8").feature("iso5").set("expr", "schr.Pr");
    model.result("pg8").feature("iso5").create("trn1", "Transformation");
    model.result("pg8").run();
    model.result("pg8").feature("iso5").feature("trn1").set("move", new String[]{"15E-10", "0", "-7.5E-10"});
    model.result("pg8").run();
    model.result("pg8").create("ann1", "Annotation");
    model.result("pg8").feature("ann1").set("text", "1s");
    model.result("pg8").feature("ann1").set("poszexpr", "2E-10");
    model.result("pg8").feature("ann1").set("showpoint", false);
    model.result("pg8").feature("ann1").set("anchorpoint", "center");
    model.result("pg8").run();
    model.result("pg8").create("ann2", "Annotation");
    model.result("pg8").feature("ann2").set("text", "2s");
    model.result("pg8").feature("ann2").set("posxexpr", "7E-10");
    model.result("pg8").feature("ann2").set("poszexpr", "5E-10");
    model.result("pg8").feature("ann2").set("showpoint", false);
    model.result("pg8").feature("ann2").set("anchorpoint", "center");
    model.result("pg8").run();
    model.result("pg8").create("ann3", "Annotation");
    model.result("pg8").feature("ann3").set("text", "2p");
    model.result("pg8").feature("ann3").set("posxexpr", "15E-10");
    model.result("pg8").feature("ann3").set("poszexpr", "12.5E-10");
    model.result("pg8").feature("ann3").set("showpoint", false);
    model.result("pg8").feature("ann3").set("anchorpoint", "center");

    model.component("comp1").view("view1").set("transparency", true);

    model.result("pg8").run();

    model.study().create("std2");
    model.study("std2").create("eigv", "Eigenvalue");
    model.study("std2").feature("eigv").set("plotgroup", "Default");
    model.study("std2").feature("eigv").set("ftplistmethod", "manual");
    model.study("std2").feature("eigv").set("neigs", "3");
    model.study("std2").feature("eigv").set("eigunit", "");
    model.study("std2").feature("eigv").set("shift", "0.1");
    model.study("std2").feature("eigv").set("linpsolnum", "auto");
    model.study("std2").feature("eigv").set("solnum", "auto");
    model.study("std2").feature("eigv").set("notsolnum", "auto");
    model.study("std2").feature("eigv").set("outputmap", new String[]{});
    model.study("std2").feature("eigv").set("ngenAUX", "1");
    model.study("std2").feature("eigv").set("goalngenAUX", "1");
    model.study("std2").feature("eigv").set("ngenAUX", "1");
    model.study("std2").feature("eigv").set("goalngenAUX", "1");
    model.study("std2").feature("eigv").setSolveFor("/physics/schr", true);
    model.study("std2").label("Stark \u6548\u5e94");
    model.study("std2").feature("eigv").set("neigs", 5);
    model.study("std2").feature("eigv").set("shift", "-15");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("\u7279\u5f81\u503c 1");
    model.result().numerical("gev2").set("data", "dset2");
    model.result().numerical("gev2").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result().numerical("gev2").set("expr", new String[]{"schr.Ei", "schr.int(schr.Pr)"});
    model.result().numerical("gev2").setIndex("unit", "eV", 0);
    model.result().numerical("gev2").set("descr", new String[]{"\u7279\u5f81\u80fd\u91cf", "\u603b\u6982\u7387"});
    model.result().numerical("gev2").setIndex("unit", "eV", 0);
    model.result().numerical("gev2").setIndex("unit", "eV", 0);
    model.result().numerical("gev2").setIndex("unit", "eV", 0);
    model.result().numerical("gev2").setIndex("unit", "eV", 0);
    model.result().numerical("gev2").set("data", "dset2");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("\u5f52\u4e00\u5316\u6ce2\u51fd\u6570 (schr) 2");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").setIndex("looplevel", 1, 0);
    model.result("pg9").feature().create("mslc1", "Multislice");
    model.result("pg9").feature("mslc1").set("expr", "schr.Psi_psi");
    model.result("pg9").feature("mslc1").set("smooth", "internal");
    model.result("pg9").feature("mslc1").set("data", "parent");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").label("\u5f52\u4e00\u5316\u6ce2\u51fd\u6570 (schr) 3");
    model.result("pg10").set("data", "dset2");
    model.result("pg10").setIndex("looplevel", 1, 0);
    model.result("pg10").feature().create("mslc1", "Multislice");
    model.result("pg10").feature("mslc1").set("expr", "imag(schr.Psi_psi)");
    model.result("pg10").feature("mslc1").set("smooth", "internal");
    model.result("pg10").feature("mslc1").set("data", "parent");
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").label("\u6982\u7387\u5bc6\u5ea6 (schr) 1");
    model.result("pg11").set("data", "dset2");
    model.result("pg11").setIndex("looplevel", 1, 0);
    model.result("pg11").feature().create("mslc1", "Multislice");
    model.result("pg11").feature("mslc1").set("expr", "schr.Pr");
    model.result("pg11").feature("mslc1").set("smooth", "internal");
    model.result("pg11").feature("mslc1").set("data", "parent");
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").label("\u52bf\u80fd (schr) 1");
    model.result("pg12").set("data", "dset2");
    model.result("pg12").setIndex("looplevel", 1, 0);
    model.result("pg12").feature().create("mslc1", "Multislice");
    model.result("pg12").feature("mslc1").set("expr", "schr.V_psi");
    model.result("pg12").feature("mslc1").set("unit", "eV");
    model.result("pg12").feature("mslc1").set("smooth", "internal");
    model.result("pg12").feature("mslc1").set("data", "parent");
    model.result().create("pg13", "PlotGroup3D");
    model.result("pg13").label("\u6709\u6548\u8d28\u91cf (schr) 1");
    model.result("pg13").set("data", "dset2");
    model.result("pg13").setIndex("looplevel", 1, 0);
    model.result("pg13").feature().create("mslc1", "Multislice");
    model.result("pg13").feature("mslc1").set("expr", "schr.meff_psiXX/me_const");
    model.result("pg13").feature("mslc1").set("smooth", "internal");
    model.result("pg13").feature("mslc1").set("data", "parent");
    model.result("pg9").run();
    model.result("pg8").run();
    model.result().duplicate("pg14", "pg8");
    model.result("pg14").run();
    model.result("pg14").label("Stark \u6548\u5e94\u8f68\u9053\u5f62\u72b6");
    model.result("pg14").set("data", "dset2");
    model.result("pg14").set("title", "Stark \u6548\u5e94\u8f68\u9053\u5f62\u72b6");
    model.result("pg14").run();
    model.result("pg14").feature("iso2").set("data", "dset2");
    model.result("pg14").feature("iso2").set("looplevel", new int[]{3});
    model.result("pg14").feature("iso2").set("interactive", false);
    model.result("pg14").run();
    model.result("pg14").feature("iso3").set("data", "dset2");
    model.result("pg14").feature("iso3").set("looplevel", new int[]{4});
    model.result("pg14").run();
    model.result("pg14").feature("iso4").set("data", "dset2");
    model.result("pg14").feature("iso4").set("looplevel", new int[]{5});
    model.result("pg14").run();
    model.result("pg14").feature("iso4").feature("trn1").set("move", new String[]{"11E-10", "0", "5E-10"});
    model.result("pg14").run();
    model.result("pg14").feature("iso5").set("data", "dset2");
    model.result("pg14").feature("iso5").set("looplevel", new int[]{2});
    model.result("pg14").run();
    model.result("pg14").feature("iso5").feature("trn1").set("move", new String[]{"11E-10", "0", "-5E-10"});
    model.result("pg14").run();
    model.result("pg14").feature("ann1").set("text", "\\unicode{1s\uff08\u65e0\u5206\u88c2\uff09}");
    model.result("pg14").feature("ann1").set("latexmarkup", true);
    model.result("pg14").run();
    model.result("pg14").feature("ann2")
         .set("text", "\\unicode{2px, 2py} \\\\ \\unicode{\uff08\u65e0\u5206\u88c2\uff09}");
    model.result("pg14").feature("ann2").set("latexmarkup", true);
    model.result("pg14").feature("ann2").set("posxexpr", "22E-10");
    model.result("pg14").feature("ann2").set("poszexpr", 0);
    model.result("pg14").run();
    model.result("pg14").feature("ann3")
         .set("text", "\\unicode{\u6df7\u5408 2s+2pz} \\\\ \\unicode{\uff08+0.03 eV Stark \u5206\u88c2\uff09}");
    model.result("pg14").feature("ann3").set("latexmarkup", true);
    model.result("pg14").feature("ann3").set("posxexpr", "11E-10");
    model.result("pg14").feature("ann3").set("poszexpr", "10E-10");
    model.result("pg14").run();
    model.result("pg14").create("ann4", "Annotation");
    model.result("pg14").feature("ann4")
         .set("text", "\\unicode{\u6df7\u5408 2s+2pz} \\\\ \\unicode{\uff08-0.03 eV Stark \u5206\u88c2\uff09}");
    model.result("pg14").feature("ann4").set("latexmarkup", true);
    model.result("pg14").feature("ann4").set("posxexpr", "11E-10");
    model.result("pg14").feature("ann4").set("poszexpr", "-10E-10");
    model.result("pg14").feature("ann4").set("showpoint", false);
    model.result("pg14").feature("ann4").set("anchorpoint", "center");
    model.result("pg14").run();
    model.result().numerical("gev1").label("\u975e\u6270\u52a8\u7279\u5f81\u80fd\u91cf");
    model.result().numerical("gev1").setIndex("looplevelinput", "manualindices", 0);
    model.result().numerical("gev1").setIndex("looplevelindices", "1,2", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u975e\u6270\u52a8\u7279\u5f81\u80fd\u91cf");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical("gev2").label("Stark \u6548\u5e94\u7279\u5f81\u80fd\u91cf");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("Stark \u6548\u5e94\u7279\u5f81\u80fd\u91cf");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result("pg14").run();

    model.title("\u6c42\u89e3\u6c22\u539f\u5b50");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u534a\u5bfc\u4f53\u6a21\u5757\u201d\uff08COMSOL Multiphysics \u7684\u9644\u52a0\u6a21\u5757\uff09\u4e2d\u7684\u201c\u859b\u5b9a\u8c14\u65b9\u7a0b\u201d\u63a5\u53e3\u6c42\u89e3\u6c22\u539f\u5b50\uff0c\u5e76\u5b9e\u73b0\u4e86\u5bf9\u6c22\u539f\u5b50\u8f68\u9053\u7684\u53ef\u89c6\u5316\u3002\u6a21\u62df\u7684\u7279\u5f81\u80fd\u548c\u6982\u7387\u5bc6\u5ea6\u4e0e\u89e3\u6790\u7ed3\u679c\u7684\u671f\u671b\u503c\u9ad8\u5ea6\u543b\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("solving_hydrogen_atom.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
