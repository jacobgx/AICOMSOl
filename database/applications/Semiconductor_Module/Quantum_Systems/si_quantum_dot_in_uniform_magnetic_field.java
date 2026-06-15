/*
 * si_quantum_dot_in_uniform_magnetic_field.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:53 by COMSOL 6.3.0.290. */
public class si_quantum_dot_in_uniform_magnetic_field {

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
    model.component("comp1").physics("schr").field("dimensionless").field("psiu");
    model.component("comp1").physics("schr").field("dimensionless").component(new String[]{"psiu", "psid"});

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

    model.component("comp1").geom("geom1").lengthUnit("nm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{120, 80, 10});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new int[]{0, 0, -3});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", 2, 0);
    model.component("comp1").geom("geom1").feature("blk1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("blk1").set("layertop", true);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").camera().set("viewscaletype", "automatic");
    model.component("comp1").view("view1").camera().set("autocontext", "anisotropic");
    model.component("comp1").view("view1").camera().set("xweight", 1.2);
    model.component("comp1").view("view1").camera().set("yweight", 0.8);
    model.component("comp1").view("view1").camera().set("zweight", 0.5);

    model.param().set("mxy", "0.19*me_const");
    model.param().descr("mxy", "\u6a2a\u5411\u6709\u6548\u8d28\u91cf");
    model.param().set("mz", "0.98*me_const");
    model.param().descr("mz", "\u7ad6\u5411\u6709\u6548\u8d28\u91cf");
    model.param().set("wx", "1[meV]/hbar_const");
    model.param().descr("wx", "x \u65b9\u5411\u7684\u9677\u9631\u9891\u7387");
    model.param().set("wy", "3*wx");
    model.param().descr("wy", "y \u65b9\u5411\u7684\u9677\u9631\u9891\u7387");
    model.param().set("Fz", "10[MV/m]");
    model.param().descr("Fz", "\u7535\u573a");
    model.param().set("U0", "3[eV]");
    model.param().descr("U0", "\u6c27\u5316\u7269\u80fd\u5792");
    model.param().set("uB", "e_const*hbar_const/2/me_const");
    model.param().descr("uB", "Bohr \u78c1\u5b50");
    model.param().set("B", "1[T]");
    model.param().descr("B", "\u78c1\u573a");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("Ax", "z*B/2");
    model.component("comp1").variable("var1").descr("Ax", "\u77e2\u52bf");
    model.component("comp1").variable("var1").set("Ay", "0[Wb/m]");
    model.component("comp1").variable("var1").descr("Ay", "\u77e2\u52bf");
    model.component("comp1").variable("var1").set("Az", "-x*B/2");
    model.component("comp1").variable("var1").descr("Az", "\u77e2\u52bf");

    model.component("comp1").physics("schr").feature("meff1")
         .set("meffe_psiu", new String[]{"mxy", "0", "0", "0", "mxy", "0", "0", "0", "mz"});
    model.component("comp1").physics("schr").feature("meff1")
         .set("meffe_psid", new String[]{"mxy", "0", "0", "0", "mxy", "0", "0", "0", "mz"});
    model.component("comp1").physics("schr").feature("ve1")
         .label("\u7535\u5b50\u52bf\u80fd 1 - \u6a2a\u5411\u8c10\u6ce2\u9650\u5236");
    model.component("comp1").physics("schr").feature("ve1").set("Ve_psiu", "0.5*mxy*(wx^2*x^2+wy^2*y^2)");
    model.component("comp1").physics("schr").feature("ve1").set("Ve_psid", "0.5*mxy*(wx^2*x^2+wy^2*y^2)");
    model.component("comp1").physics("schr").create("ve2", "ElectronPotentialEnergy", 3);
    model.component("comp1").physics("schr").feature("ve2").label("\u7535\u5b50\u52bf\u80fd 2 - E \u573a");
    model.component("comp1").physics("schr").feature("ve2").selection().all();
    model.component("comp1").physics("schr").feature("ve2").set("Ve_psiu", "schr.q*Fz*z");
    model.component("comp1").physics("schr").feature("ve2").set("Ve_psid", "schr.q*Fz*z");
    model.component("comp1").physics("schr").create("ve3", "ElectronPotentialEnergy", 3);
    model.component("comp1").physics("schr").feature("ve3")
         .label("\u7535\u5b50\u52bf\u80fd 3 - \u6c27\u5316\u7269\u80fd\u5792");
    model.component("comp1").physics("schr").feature("ve3").selection().set(2);
    model.component("comp1").physics("schr").feature("ve3").set("Ve_psiu", "U0");
    model.component("comp1").physics("schr").feature("ve3").set("Ve_psid", "U0");
    model.component("comp1").physics("schr").create("lorf1", "LorentzForce_schr", 3);
    model.component("comp1").physics("schr").feature("lorf1").selection().all();
    model.component("comp1").physics("schr").feature("lorf1").set("A", new String[]{"Ax", "Ay", "Az"});
    model.component("comp1").physics("schr").create("H0th1", "ZerothOrderHamiltonianSemicond", 3);
    model.component("comp1").physics("schr").feature("H0th1")
         .label("\u96f6\u9636\u54c8\u5bc6\u987f 1 - \u81ea\u65cb");
    model.component("comp1").physics("schr").feature("H0th1").selection().all();
    model.component("comp1").physics("schr").feature("H0th1").setIndex("n", 2, 0, 0);
    model.component("comp1").physics("schr").feature("H0th1")
         .setIndex("H0", "-i*uB*B/(hbar_const^2/2/me_const)", 0, 0);
    model.component("comp1").physics("schr").feature("H0th1").setIndex("desc", "H12", 0, 0);
    model.component("comp1").physics("schr").feature("H0th1").setIndex("m", 1, 1, 0);
    model.component("comp1").physics("schr").feature("H0th1").setIndex("n", 1, 1, 0);
    model.component("comp1").physics("schr").feature("H0th1").setIndex("H0", 0, 1, 0);
    model.component("comp1").physics("schr").feature("H0th1").setIndex("desc", "Description", 1, 0);
    model.component("comp1").physics("schr").feature("H0th1").setIndex("n", 1, 1, 0);
    model.component("comp1").physics("schr").feature("H0th1").setIndex("H0", 0, 1, 0);
    model.component("comp1").physics("schr").feature("H0th1").setIndex("desc", "Description", 1, 0);
    model.component("comp1").physics("schr").feature("H0th1").setIndex("m", 1, 1, 0);
    model.component("comp1").physics("schr").feature("H0th1").setIndex("n", 1, 1, 0);
    model.component("comp1").physics("schr").feature("H0th1").setIndex("H0", 0, 1, 0);
    model.component("comp1").physics("schr").feature("H0th1").setIndex("desc", "Description", 1, 0);
    model.component("comp1").physics("schr").feature("H0th1").setIndex("m", 2, 1, 0);
    model.component("comp1").physics("schr").feature("H0th1")
         .setIndex("H0", "+i*uB*B/(hbar_const^2/2/me_const)", 1, 0);
    model.component("comp1").physics("schr").feature("H0th1").setIndex("desc", "H21", 1, 0);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(7);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 20);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("numelem", 10);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("eigv").set("neigs", 9);
    model.study("std1").feature("eigv").set("shift", "0.037");
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
    model.result("pg1").feature("mslc1").set("expr", "schr.Psi_psiu");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u5f52\u4e00\u5316\u6ce2\u51fd\u6570 (schr) 1");
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", "imag(schr.Psi_psiu)");
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
    model.result("pg4").feature("mslc1").set("expr", "schr.V_psiu");
    model.result("pg4").feature("mslc1").set("unit", "eV");
    model.result("pg4").feature("mslc1").set("smooth", "internal");
    model.result("pg4").feature("mslc1").set("data", "parent");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u6709\u6548\u8d28\u91cf (schr)");
    model.result("pg5").feature().create("mslc1", "Multislice");
    model.result("pg5").feature("mslc1").set("expr", "schr.meff_psiuXX/me_const");
    model.result("pg5").feature("mslc1").set("smooth", "internal");
    model.result("pg5").feature("mslc1").set("data", "parent");
    model.result("pg1").run();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u7279\u5f81\u503c");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result("pg1").run();
    model.result("pg1").stepNext(0);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").stepNext(0);
    model.result("pg2").run();
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("pointx", 0);
    model.result().dataset("cpt1").set("pointy", 0);
    model.result().dataset("cpt1").set("pointz", -2);
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u8ba1\u7b97\u7ec4 1 - \u6ce2\u51fd\u6570");
    model.result().evaluationGroup("eg1").set("data", "cpt1");
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "manualindices", 0);
    model.result().evaluationGroup("eg1").setIndex("looplevelindices", "1 2", 0);
    model.result().evaluationGroup("eg1").create("pev1", "EvalPoint");
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("expr", "schr.Psi_psiu/3.56575008131e11", 0);
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("descr", "\u5411\u4e0a", 0);
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("expr", "schr.Psi_psid/3.56575008131e11", 1);
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("descr", "\u5411\u4e0b", 1);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").label("\u8ba1\u7b97\u7ec4 2 - \u6bd4\u8f83\u80fd\u91cf\u5dee");
    model.result().evaluationGroup("eg2").setIndex("looplevelinput", "first", 0);
    model.result().evaluationGroup("eg2").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg2").feature("gev1")
         .setIndex("expr", "withsol('sol1',schr.Ei,setind(lambda,2))-schr.Ei", 0);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("unit", "meV", 0);
    model.result().evaluationGroup("eg2").feature("gev1")
         .setIndex("descr", "\u8ba1\u7b97\u51fa\u7684\u80fd\u91cf\u5dee", 0);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "2*uB*B", 1);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("unit", "meV", 1);
    model.result().evaluationGroup("eg2").feature("gev1")
         .setIndex("descr", "\u9884\u8ba1\u7684\u80fd\u91cf\u5dee", 1);
    model.result().evaluationGroup("eg2").run();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("quickplane", "xz");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").label("\u8865\u5145\u56fe 1");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "schr.Pr");
    model.result("pg6").feature("surf1").set("unit", "1/cm^3");
    model.result("pg6").feature("surf1").set("coloring", "gradient");
    model.result("pg6").feature("surf1").set("topcolor", "blue");
    model.result("pg6").feature("surf1").set("bottomcolor", "gray");
    model.result("pg6").run();
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("planecoordsys", "cartesian");
    model.result("pg6").feature("arws1").set("expr", new String[]{"", "", ""});
    model.result("pg6").feature("arws1").set("descr", "");
    model.result("pg6").feature("arws1").set("expr", new String[]{"schr.PiX", "schr.PiY", "schr.PiZ"});
    model.result("pg6").feature("arws1").set("descr", "\u8fd0\u52a8\u5b66\u52a8\u91cf\u5bc6\u5ea6");
    model.result("pg6").feature("arws1").set("xnumber", 30);
    model.result("pg6").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg6").feature("arws1").set("scaleactive", true);
    model.result("pg6").feature("arws1").set("scale", "1e5");

    model.view("view2").axis().set("viewscaletype", "automatic");

    model.result("pg6").run();
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u7b2c\u516b\u6fc0\u53d1\u6001");
    model.result("pg7").stepLast(0);
    model.result("pg7").run();
    model.result("pg7").set("edges", false);

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg7").set("showlegends", false);
    model.result("pg7").create("iso1", "Isosurface");
    model.result("pg7").feature("iso1").set("expr", "schr.Pr");
    model.result("pg7").feature("iso1").set("unit", "1/cm^3");
    model.result("pg7").feature("iso1").set("levelmethod", "levels");
    model.result("pg7").feature("iso1").set("levels", "range(2.0e16,7.5e16,3.2e17)");
    model.result("pg7").feature("iso1").create("filt1", "Filter");
    model.result("pg7").run();
    model.result("pg7").feature("iso1").feature("filt1").set("expr", "x>0.1[nm] || z<-2[nm]");
    model.result("pg7").run();
    model.result("pg7").feature("iso1").create("tran1", "Transparency");
    model.result("pg7").run();
    model.result("pg7").feature("iso1").feature("tran1").set("transparency", 0.4);
    model.result("pg7").run();
    model.result("pg7").create("arwv1", "ArrowVolume");
    model.result("pg7").feature("arwv1").set("expr", new String[]{"schr.PiX", "schr.PiY", "schr.PiZ"});
    model.result("pg7").feature("arwv1").set("descr", "\u8fd0\u52a8\u5b66\u52a8\u91cf\u5bc6\u5ea6");
    model.result("pg7").feature("arwv1").set("xnumber", 26);
    model.result("pg7").feature("arwv1").set("arrowymethod", "coord");
    model.result("pg7").feature("arwv1").set("ycoord", "-30 30");
    model.result("pg7").feature("arwv1").set("znumber", 15);
    model.result("pg7").feature("arwv1").set("scaleactive", true);
    model.result("pg7").feature("arwv1").set("scale", "1e7");
    model.result("pg7").feature("arwv1").set("color", "magenta");
    model.result("pg7").run();

    model.title("\u5747\u5300\u78c1\u573a\u4e2d\u7684\u7845\u91cf\u5b50\u70b9");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u57fa\u4e8e Jock \u7b49\u4eba\u5173\u4e8e\u81ea\u65cb\u8f68\u9053\u91cf\u5b50\u4f4d\u4e3b\u9898\u7684\u8bba\u6587\uff0c\u6c42\u89e3\u5747\u5300\u78c1\u573a\u4e2d\u7b80\u5355\u7845\u91cf\u5b50\u70b9\u7279\u5f81\u6001\u7684\u53cc\u5206\u91cf\u859b\u5b9a\u8c14\u65b9\u7a0b\u3002\u5176\u4e2d\u4f7f\u7528\u201c\u859b\u5b9a\u8c14\u65b9\u7a0b\u201d\u63a5\u53e3\u7684\u5185\u7f6e\u57df\u6761\u4ef6\u201c\u6d1b\u4f26\u5179\u529b\u201d\u6765\u89e3\u91ca\u77e2\u52bf\u5bf9\u8fd0\u52a8\u5b66\u52a8\u91cf\u7684\u8d21\u732e\uff0c\u5e76\u5229\u7528\u5185\u7f6e\u7684\u57df\u6761\u4ef6\u201c\u96f6\u9636\u54c8\u5bc6\u987f\u201d\u6765\u5b9e\u73b0\u81ea\u65cb\u5411\u4e0a\u548c\u81ea\u65cb\u5411\u4e0b\u5206\u91cf\u7684\u8026\u5408\u3002\u8fd9\u4e9b\u793a\u4f8b\u4e0e\u201c\u4f7f\u7528 k.p \u6cd5\u5206\u6790\u5e94\u53d8\u7ea4\u950c\u77ff GaN \u80fd\u5e26\u7ed3\u6784\u201d\u57fa\u51c6\u6a21\u578b\u4e00\u8d77\u6f14\u793a\u4e86\u5982\u4f55\u4f7f\u7528\u201c\u859b\u5b9a\u8c14\u65b9\u7a0b\u201d\u63a5\u53e3\u6765\u8bbe\u7f6e\u591a\u4e2a\u6ce2\u51fd\u6570\u5206\u91cf\u3002\u901a\u8fc7\u5c06\u8ba1\u7b97\u5f97\u5230\u7684\u57fa\u6001\u7684\u6982\u7387\u5bc6\u5ea6\u548c\u8fd0\u52a8\u5b66\u52a8\u91cf\u5bc6\u5ea6\u4e0e\u8bba\u6587\u4e2d\u7684\u8865\u5145\u56fe\u00a01 \u8fdb\u884c\u6bd4\u8f83\uff0c\u7ed3\u679c\u663e\u793a\u51fa\u826f\u597d\u7684\u4e00\u81f4\u6027\u3002\u6b64\u5916\uff0c\u8ba1\u7b97\u7684\u524d\u4e24\u4e2a\u7279\u5f81\u6001\u4e4b\u95f4\u7684\u80fd\u91cf\u5dee\u4e0e\u76f4\u89c2\u89e3\u6790\u8ba1\u7b97\u5f97\u5230\u7684\u9884\u671f\u503c\u975e\u5e38\u543b\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("si_quantum_dot_in_uniform_magnetic_field.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
