/*
 * superlattice_band_gap_tool.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:53 by COMSOL 6.3.0.290. */
public class superlattice_band_gap_tool {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Quantum_Systems");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

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

    model.component("comp1").geom("geom1").lengthUnit("nm");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("lw", "5[nm]", "\u9631\u5bbd\u5ea6");
    model.param().set("lb", "5[nm]", "\u52bf\u5792\u5bbd\u5ea6");
    model.param().set("mew", "0.063", "\u9631\u6750\u6599\u7684\u7535\u5b50\u6709\u6548\u8d28\u91cf");
    model.param().set("meb", "0.71", "\u52bf\u5792\u6750\u6599\u7684\u7535\u5b50\u6709\u6548\u8d28\u91cf");
    model.param().set("mhw", "0.51", "\u9631\u6750\u6599\u7684\u7a7a\u7a74\u6709\u6548\u8d28\u91cf");
    model.param().set("mhb", "0.76", "\u52bf\u5792\u6750\u6599\u7684\u7a7a\u7a74\u6709\u6548\u8d28\u91cf");
    model.param().set("Egw", "1.424[V]", "\u9631\u7684\u5e26\u9699");
    model.param().set("Egb", "2.168[V]", "\u52bf\u5792\u7684\u5e26\u9699");
    model.param().set("CBO", "0.283[V]", "\u5bfc\u5e26\u504f\u79fb\u91cf");
    model.param().set("VBO", "Egb-Egw-CBO", "\u4ef7\u5e26\u504f\u79fb\u91cf");
    model.param().set("hmax", "0.1[nm]", "\u6700\u5927\u7f51\u683c\u5355\u5143\u5927\u5c0f");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").set("specify", "len");
    model.component("comp1").geom("geom1").feature("i1").set("left", "-lw/2-lb/2");
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "lb/2", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "lw", 1);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "lb/2", 2);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("schr").label("\u859b\u5b9a\u8c14\u65b9\u7a0b\u7535\u5b50");
    model.component("comp1").physics("schr").tag("schre");
    model.component("comp1").physics("schre").field("dimensionless").component(1, "psie");
    model.component("comp1").physics("schre").feature("meff1")
         .set("meffe_psie", new String[]{"meb*me_const", "0", "0", "0", "meb*me_const", "0", "0", "0", "meb*me_const"});
    model.component("comp1").physics("schre").feature("ve1").set("Ve_src", "userdef");
    model.component("comp1").physics("schre").feature("ve1").set("Ve", "Egb*e_const");
    model.component("comp1").physics("schre").create("meff2", "EffectiveMass", 1);
    model.component("comp1").physics("schre").feature("meff2").selection().set(2);
    model.component("comp1").physics("schre").feature("meff2")
         .set("meffe_psie", new String[]{"mew*me_const", "0", "0", "0", "mew*me_const", "0", "0", "0", "mew*me_const"});
    model.component("comp1").physics("schre").create("ve2", "ElectronPotentialEnergy", 1);
    model.component("comp1").physics("schre").feature("ve2").selection().set(2);
    model.component("comp1").physics("schre").feature("ve2").set("Ve_src", "userdef");
    model.component("comp1").physics("schre").feature("ve2").set("Ve", "-CBO*e_const");
    model.component("comp1").physics("schre").create("pc1", "PeriodicCondition", 0);
    model.component("comp1").physics("schre").feature("pc1").selection().all();
    model.component("comp1").physics().create("schr", "SchrodingerEquation", "geom1");

    model.study("std1").feature("eigv").setSolveFor("/physics/schr", true);

    model.component("comp1").physics("schr").label("\u859b\u5b9a\u8c14\u65b9\u7a0b\u7a7a\u7a74");
    model.component("comp1").physics("schr").tag("schrh");
    model.component("comp1").physics("schrh").prop("ModelProperties").set("ParticleType", "Holes");
    model.component("comp1").physics("schrh").field("dimensionless").component(1, "psih");
    model.component("comp1").physics("schrh").feature("meff1")
         .set("meffh_psih", new String[]{"mhb*me_const", "0", "0", "0", "mhb*me_const", "0", "0", "0", "mhb*me_const"});
    model.component("comp1").physics("schrh").feature("ve1").set("Vh_src", "userdef");
    model.component("comp1").physics("schrh").feature("ve1").set("Vh", 0);
    model.component("comp1").physics("schrh").create("meff2", "EffectiveMass", 1);
    model.component("comp1").physics("schrh").feature("meff2").selection().set(2);
    model.component("comp1").physics("schrh").feature("meff2")
         .set("meffh_psih", new String[]{"mhw*me_const", "0", "0", "0", "mhw*me_const", "0", "0", "0", "mhw*me_const"});
    model.component("comp1").physics("schrh").create("ve2", "ElectronPotentialEnergy", 1);
    model.component("comp1").physics("schrh").feature("ve2").selection().set(2);
    model.component("comp1").physics("schrh").feature("ve2").set("Vh_src", "userdef");
    model.component("comp1").physics("schrh").feature("ve2").set("Vh", "VBO*e_const");
    model.component("comp1").physics("schrh").create("pc1", "PeriodicCondition", 0);
    model.component("comp1").physics("schrh").feature("pc1").selection().all();

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "hmax");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("eigv").set("neigs", 1);
    model.study("std1").feature("eigv").set("shift", "(Egb-CBO)[1/V]");
    model.study("std1").feature("eigv").setSolveFor("/physics/schrh", false);
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("eigv", "Eigenvalue");
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
    model.study("std2").feature("eigv").setSolveFor("/physics/schre", false);
    model.study("std2").feature("eigv").setSolveFor("/physics/schrh", true);
    model.study("std2").feature("eigv").set("neigs", 1);
    model.study("std2").feature("eigv").set("shift", "-VBO[1/V]");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("join1", "Join");
    model.result().dataset("join1").set("data", "dset1");
    model.result().dataset("join1").set("solutions", "one");
    model.result().dataset("join1").set("data2", "dset2");
    model.result().dataset("join1").set("solutions2", "one");
    model.result().dataset("join1").set("method", "explicit");
    model.result().dataset().create("arr1", "Array1D");
    model.result().dataset("arr1").set("data", "join1");
    model.result().dataset("arr1").set("fullsize", new int[]{3});
    model.result().dataset("arr1").set("hasvars", true);
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "join1");
    model.result().numerical("gev1").setIndex("expr", "data1(schre.Ei)/e_const-(Egb-CBO)", 0);
    model.result().numerical("gev1").setIndex("descr", "\u5bfc\u5e26\u8fb9\u504f\u79fb", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("data", "join1");
    model.result().numerical("gev2").setIndex("expr", "data2(schrh.Ei)/e_const+VBO", 0);
    model.result().numerical("gev2").setIndex("descr", "\u4ef7\u5e26\u8fb9\u504f\u79fb", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").set("data", "join1");
    model.result().numerical("gev3").setIndex("expr", "(data1(schre.Ei)+data2(schrh.Ei))/e_const", 0);
    model.result().numerical("gev3").setIndex("descr", "\u6709\u6548\u5e26\u9699", 0);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u5168\u5c40\u8ba1\u7b97 3");
    model.result().numerical("gev3").set("table", "tbl3");
    model.result().numerical("gev3").setResult();
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").set("data", "arr1");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "x (nm)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u80fd\u91cf (eV)");
    model.result("pg1").set("legendpos", "center");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr1").set("linewidth", "preference");
    model.result("pg1").feature("lngr1").label("Ve");
    model.result("pg1").feature("lngr1").set("expr", "data1(schre.V)/e_const");
    model.result("pg1").feature("lngr1").set("descractive", true);
    model.result("pg1").feature("lngr1").set("descr", "\u5bfc\u5e26\u8fb9");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "arr1x");
    model.result("pg1").feature("lngr1").set("linecolor", "black");
    model.result("pg1").feature("lngr1").set("linewidth", 2);
    model.result("pg1").feature("lngr1").set("smooth", "everywhere");
    model.result("pg1").feature("lngr1").set("resolution", "extrafine");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("legendmethod", "manual");
    model.result("pg1").feature("lngr1").setIndex("legends", "\u5bfc\u5e26\u8fb9", 0);
    model.result("pg1").feature().duplicate("lngr2", "lngr1");
    model.result("pg1").run();
    model.result("pg1").feature("lngr2").label("Vh");
    model.result("pg1").feature("lngr2").set("expr", "data2(-schrh.V)/e_const");
    model.result("pg1").feature("lngr2").set("descr", "\u4ef7\u5e26\u8fb9");
    model.result("pg1").feature("lngr2").set("linecolor", "gray");
    model.result("pg1").feature("lngr2").setIndex("legends", "\u4ef7\u5e26\u8fb9", 0);
    model.result("pg1").feature().duplicate("lngr3", "lngr2");
    model.result("pg1").run();
    model.result("pg1").feature("lngr3").label("psie");
    model.result("pg1").feature("lngr3").set("expr", "data1(schre.Psi*Egw/4/schre.plot_fac+schre.Ei/e_const)");
    model.result("pg1").feature("lngr3").set("descr", "\u7535\u5b50\u6ce2\u51fd\u6570");
    model.result("pg1").feature("lngr3").set("linecolor", "cycle");
    model.result("pg1").feature("lngr3")
         .setIndex("legends", "\u7535\u5b50\u6ce2\u51fd\u6570\uff08\u5b9e\u90e8\uff09", 0);
    model.result("pg1").feature().duplicate("lngr4", "lngr3");
    model.result("pg1").run();
    model.result("pg1").feature("lngr4").label("psih");
    model.result("pg1").feature("lngr4").set("expr", "data2(-schrh.Psi*Egw/4/schrh.plot_fac-schrh.Ei/e_const)");
    model.result("pg1").feature("lngr4").set("descr", "\u7a7a\u7a74\u6ce2\u51fd\u6570");
    model.result("pg1").feature("lngr4")
         .setIndex("legends", "\u7a7a\u7a74\u6ce2\u51fd\u6570\uff08\u5b9e\u90e8\uff09", 0);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("lngr5", "lngr3");
    model.result("pg1").run();
    model.result("pg1").feature("lngr5").label("Im(psie)");
    model.result("pg1").feature("lngr5").set("expr", "data1(imag(schre.Psi)*Egw/4/schre.plot_fac+schre.Ei/e_const)");
    model.result("pg1").feature("lngr5").set("linestyle", "dashed");
    model.result("pg1").feature("lngr5").set("linecolor", "blue");
    model.result("pg1").feature("lngr5")
         .setIndex("legends", "\u7535\u5b50\u6ce2\u51fd\u6570\uff08\u865a\u90e8\uff09", 0);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("lngr6", "lngr4");
    model.result("pg1").run();
    model.result("pg1").feature("lngr6").label("Im(psih)");
    model.result("pg1").feature("lngr6")
         .set("expr", "data2(-imag(schrh.Psi)*Egw/4/schrh.plot_fac-schrh.Ei/e_const)");
    model.result("pg1").feature("lngr6").set("linestyle", "dashed");
    model.result("pg1").feature("lngr6").set("linecolor", "green");
    model.result("pg1").feature("lngr6")
         .setIndex("legends", "\u7a7a\u7a74\u6ce2\u51fd\u6570\uff08\u865a\u90e8\uff09", 0);
    model.result("pg1").run();

    model.title("\u8d85\u6676\u683c\u5e26\u9699\u5de5\u5177");

    model
         .description("\u201c\u8d85\u6676\u683c\u5e26\u9699\u5de5\u5177\u201d\u6a21\u578b\u6709\u52a9\u4e8e\u8bbe\u8ba1\u7531\u4e24\u79cd\u4ea4\u66ff\u534a\u5bfc\u4f53\u6750\u6599\uff08\u8d85\u6676\u683c\uff09\u6784\u6210\u7684\u5468\u671f\u6027\u7ed3\u6784\u3002\u6b64\u6a21\u578b\u91c7\u7528\u6709\u6548\u8d28\u91cf\u859b\u5b9a\u8c14\u65b9\u7a0b\u6765\u4f30\u8ba1\u7ed9\u5b9a\u8d85\u6676\u683c\u7ed3\u6784\u4e2d\u7684\u7535\u5b50\u548c\u7a7a\u7a74\u57fa\u6001\u80fd\u7ea7\u3002\u5668\u4ef6\u5de5\u7a0b\u6280\u672f\u4eba\u5458\u53ef\u4ee5\u4f7f\u7528\u6b64\u6a21\u578b\u5feb\u901f\u8ba1\u7b97\u7ed9\u5b9a\u5468\u671f\u7ed3\u6784\u7684\u6709\u6548\u5e26\u9699\uff0c\u5e76\u5bf9\u8bbe\u8ba1\u53c2\u6570\u8fd0\u884c\u8fed\u4ee3\uff0c\u76f4\u5230\u8fbe\u5230\u6240\u9700\u7684\u5e26\u9699\u503c\u3002\n\n\u60a8\u53ef\u4ee5\u9605\u8bfb\u4ee5\u4e0b\u535a\u5ba2\u6587\u7ae0\uff0c\u4e86\u89e3\u6709\u5173\u8be5\u6a21\u578b\u7684\u66f4\u591a\u4fe1\u606f\uff1a\u201c\u4f7f\u7528\u859b\u5b9a\u8c14\u65b9\u7a0b\u8ba1\u7b97\u8d85\u6676\u683c\u5e26\u9699\u201d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("superlattice_band_gap_tool.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
