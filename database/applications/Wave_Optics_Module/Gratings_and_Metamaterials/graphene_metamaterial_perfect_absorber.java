/*
 * graphene_metamaterial_perfect_absorber.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:38 by COMSOL 6.3.0.290. */
public class graphene_metamaterial_perfect_absorber {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Gratings_and_Metamaterials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("ftplistmethod", "manual");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/ewfd", true);

    model.param().set("T", "300[K]");
    model.param().descr("T", "\u6e29\u5ea6");
    model.param().set("Ef", "0.2[eV]");
    model.param().descr("Ef", "\u8d39\u7c73\u80fd\u7ea7");
    model.param().set("tau", "1e-13[s]");
    model.param().descr("tau", "\u5f1b\u8c6b\u65f6\u95f4");
    model.param().set("d_eff", "1[nm]");
    model.param().descr("d_eff", "\u77f3\u58a8\u70ef\u7684\u6709\u6548\u539a\u5ea6");
    model.param().set("a", "15[um]");
    model.param().descr("a", "\u57fa\u672c\u5355\u5143\u957f\u5ea6");
    model.param().set("b", "2[um]");
    model.param().descr("b", "\u51e0\u4f55\u53c2\u6570 1");
    model.param().set("d_sub", "17.6[um]");
    model.param().descr("d_sub", "\u57fa\u5e95\u539a\u5ea6");
    model.param().set("d_domain", "40[um]");
    model.param().descr("d_domain", "\u4eff\u771f\u57df\u9ad8\u5ea6");
    model.param().set("w", "12[um]");
    model.param().descr("w", "\u51e0\u4f55\u53c2\u6570 2");
    model.param().set("n_bg", "1.53");
    model.param().descr("n_bg", "\u57fa\u5e95\u6298\u5c04\u7387");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").label("H");
    model.component("comp1").func("an1").set("funcname", "H");
    model.component("comp1").func("an1")
         .set("expr", "sinh(hbar_const*x/(k_B_const*T))/(cosh(hbar_const*x/(k_B_const*T))+cosh(Ef/(k_B_const*T)))");
    model.component("comp1").func("an1").setIndex("argunit", "rad/s", 0);
    model.component("comp1").func("an1").setIndex("plotargs", "1e16", 0, 2);

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("Omega", "1[rad/s]");
    model.component("comp1").variable("var1").descr("Omega", "\u79ef\u5206\u53d8\u91cf");
    model.component("comp1").variable("var1")
         .set("integral", "integrate((H(Omega)-H(ewfd.omega/2))/(ewfd.omega^2-4*Omega^2),Omega,0[rad/s],1e16[rad/s])");
    model.component("comp1").variable("var1")
         .descr("integral", "\u5e26\u95f4\u7535\u5bfc\u7387\u65b9\u7a0b\u4e2d\u7684\u79ef\u5206");
    model.component("comp1").variable("var1")
         .set("sigma_intra", "((2*k_B_const*T*e_const^2)/(pi*hbar_const^2))*(log(2*cosh(Ef/(2*k_B_const*T)))*(-j/(ewfd.omega-j/tau)))");
    model.component("comp1").variable("var1").descr("sigma_intra", "\u5e26\u5185\u7535\u5bfc\u7387");
    model.component("comp1").variable("var1")
         .set("sigma_inter", "(e_const^2/(4*hbar_const))*(H(ewfd.omega/2)-(j*4*ewfd.omega/pi)*integral)");
    model.component("comp1").variable("var1").descr("sigma_inter", "\u5e26\u95f4\u7535\u5bfc\u7387");
    model.component("comp1").variable("var1").set("sigma", "sigma_intra+sigma_inter");
    model.component("comp1").variable("var1").descr("sigma", "\u603b\u77f3\u58a8\u70ef\u7535\u5bfc\u7387");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"a/2", "a/2", "d_domain"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "d_sub");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", "w/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"b/2", "(a-w)/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new String[]{"0", "w/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"(a-w)/2", "b/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("pos", new String[]{"w/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u80cc\u666f");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"n_bg"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u77f3\u58a8\u70ef");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma/d_eff"});
    model.component("comp1").material("mat2").selection().geom("geom1", 2);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").material("mat2").selection().set(6, 8, 10);

    model.component("comp1").physics("ewfd").create("pmc1", "PerfectMagneticConductor", 2);
    model.component("comp1").physics("ewfd").feature("pmc1").selection().set(2, 5, 9);
    model.component("comp1").physics("ewfd").create("port1", "Port", 2);
    model.component("comp1").physics("ewfd").feature("port1").selection().set(7);
    model.component("comp1").physics("ewfd").create("trans1", "TransitionBoundaryCondition", 2);
    model.component("comp1").physics("ewfd").feature("trans1").selection().set(6, 8, 10);
    model.component("comp1").physics("ewfd").feature("trans1").set("DisplacementFieldModel", "RelativePermittivity");
    model.component("comp1").physics("ewfd").feature("trans1").set("epsilonr_mat", "userdef");
    model.component("comp1").physics("ewfd").feature("trans1").set("murbnd_mat", "userdef");
    model.component("comp1").physics("ewfd").feature("trans1").set("d", "2*d_eff");

    model.component("comp1").mesh("mesh1").autoMeshSize(1);

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "T", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "K", 0);
    model.study("std1").feature("param").setIndex("pname", "T", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "K", 0);
    model.study("std1").feature("param").setIndex("pname", "Ef", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0 0.1 0.2 0.5", 0);
    model.study("std1").feature("param").setIndex("punit", "eV", 0);
    model.study("std1").feature("freq").set("plist", "range(0.1,0.1,5)");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 50, 0);
    model.result("pg1").setIndex("looplevel", 4, 1);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"ewfd.Rport_1"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u53cd\u5c04\u7387\uff0c\u7aef\u53e3 1"});
    model.result("pg2").label("\u53cd\u5c04\u7387 (ewfd)");
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u53cd\u5c04\u7387 (1)");
    model.result("pg2").feature("glob1").set("xdataexpr", "freq");
    model.result("pg2").feature("glob1").set("xdataunit", "THz");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "all");
    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u5438\u6536\u7387");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u9891\u7387 (THz)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u5438\u6536\u7387");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u4e0d\u540c\u8d39\u7c73\u80fd\u7ea7\u4e0b\u7684\u5438\u6536\u7387");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"ewfd.Atotal"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u5438\u6536\u7387"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u7535\u5bfc\u7387");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u9891\u7387 (THz)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\\sigma (S)");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4")
         .set("title", "\u4e0d\u540c\u8d39\u7c73\u80fd\u7ea7\u4e0b\u7684\u8868\u9762\u7535\u5bfc\u7387");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "real(sigma)", 0);
    model.result("pg4").run();
    model.result("pg4").create("glob2", "Global");
    model.result("pg4").feature("glob2").set("markerpos", "datapoints");
    model.result("pg4").feature("glob2").set("linewidth", "preference");
    model.result("pg4").feature("glob2").setIndex("expr", "-imag(sigma)", 0);
    model.result("pg4").feature("glob2").set("linestyle", "dotted");
    model.result("pg4").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg4").run();

    model.title("\u77f3\u58a8\u70ef\u8d85\u6750\u6599\u5b8c\u7f8e\u5438\u6536\u5668");

    model
         .description("\u81ea\u5927\u7ea6\u4e8c\u5341\u5e74\u524d\u7684\u5b9e\u9a8c\u53d1\u73b0\u4ee5\u6765\uff0c\u77f3\u58a8\u70ef\u8fd9\u79cd\u7531\u78b3\u539f\u5b50\u4ee5\u4e8c\u7ef4\u516d\u65b9\u6676\u683c\u6392\u5217\u800c\u6210\u7684\u6750\u6599\u4fbf\u6fc0\u53d1\u4e86\u4eba\u4eec\u5e7f\u6cdb\u7684\u7814\u7a76\u5174\u8da3\uff0c\u5e76\u5177\u6709\u5e7f\u9614\u7684\u5e94\u7528\u524d\u666f\u3002\u9664\u4e86\u5176\u8d85\u8584\u7279\u6027\uff0c\u8fd9\u79cd\u795e\u5947\u7684\u6750\u6599\u8fd8\u8868\u73b0\u51fa\u8bb8\u591a\u6709\u8da3\u7684\u5c5e\u6027\uff0c\u5305\u62ec\u9ad8\u7535\u5bfc\u7387\u548c\u70ed\u5bfc\u7387\u3001\u9ad8\u5f39\u6027\u3001\u9ad8\u673a\u68b0\u5f3a\u5ea6\uff0c\u7b49\u7b49\u3002\u5728\u4f17\u591a\u5e94\u7528\u9886\u57df\u4e2d\uff0c\u57fa\u4e8e\u77f3\u58a8\u70ef\u7684\u7535\u5149\u8bbe\u5907\u5c24\u4e3a\u5f15\u4eba\u6ce8\u76ee\uff0c\u4f8b\u5982\u5149\u7535\u63a2\u6d4b\u5668\u3001\u5149\u7535\u4e8c\u6781\u7ba1\u548c\u8d85\u6750\u6599\u3002\u77f3\u58a8\u70ef\u7684\u53e6\u4e00\u4e2a\u7406\u60f3\u7279\u6027\u5728\u4e8e\uff0c\u901a\u8fc7\u7535\u95e8\u63a7\u6539\u53d8\u5176\u8d39\u7c73\u80fd\u7ea7\uff0c\u53ef\u4ee5\u4e3b\u52a8\u63a7\u5236\u5176\u5149\u5b66\u54cd\u5e94\u3002\u672c\u6a21\u578b\u9996\u5148\u6f14\u793a\u5982\u4f55\u4f7f\u7528 Kubo \u516c\u5f0f\u8ba1\u7b97\u77f3\u58a8\u70ef\u7684\u5149\u5bfc\u7387\uff0c\u7136\u540e\u4f7f\u7528\u8ba1\u7b97\u51fa\u7684\u5149\u5bfc\u7387\u6765\u6a21\u62df\u57fa\u4e8e\u77f3\u58a8\u70ef\u7684\u592a\u8d6b\u5179\u8d85\u6750\u6599\u5438\u6536\u5668\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("graphene_metamaterial_perfect_absorber.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
