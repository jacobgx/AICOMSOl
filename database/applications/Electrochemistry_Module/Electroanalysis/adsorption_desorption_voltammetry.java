/*
 * adsorption_desorption_voltammetry.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:52 by COMSOL 6.3.0.290. */
public class adsorption_desorption_voltammetry {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrochemistry_Module\\Electroanalysis");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryElectroanalysis", "geom1");
    model.component("comp1").physics("tcd").field("concentration").field("c_A");
    model.component("comp1").physics("tcd").field("concentration").component(new String[]{"c_A"});

    model.study().create("std1");
    model.study("std1").create("cyclv", "CyclicVoltammetry");
    model.study("std1").feature("cyclv").set("ftplistmethod", "manual");
    model.study("std1").feature("cyclv").set("initialtime", "0");
    model.study("std1").feature("cyclv").set("solnum", "auto");
    model.study("std1").feature("cyclv").set("notsolnum", "auto");
    model.study("std1").feature("cyclv").set("outputmap", new String[]{});
    model.study("std1").feature("cyclv").setSolveFor("/physics/tcd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("sigma", "10", "\u65e0\u91cf\u7eb2\u626b\u63cf\u901f\u7387");
    model.param().set("k_a1_prime", "0.1", "\u65e0\u91cf\u7eb2\u5438\u9644\u7387\u5e38\u6570");
    model.param()
         .set("K_prime", "1e-5", "\u65e0\u91cf\u7eb2\u5438\u9644-\u89e3\u5438\u7387\u5e38\u6570\u6bd4\u7387");
    model.param().set("k_0_prime", "0.02", "\u65e0\u91cf\u7eb2\u7535\u5b50\u8f6c\u79fb\u901f\u7387\u5e38\u6570");
    model.param().set("beta", "1e-3", "\u65e0\u91cf\u7eb2\u9971\u548c\u5ea6\u53c2\u6570");
    model.param().set("theta_A_init_prime", "0", "\u521d\u59cb\u8868\u9762\u8986\u76d6\u7387");
    model.param().set("k_d1_prime", "k_a1_prime/K_prime", "\u65e0\u91cf\u7eb2\u89e3\u5438\u7387\u5e38\u6570");
    model.param().set("c_A_bulk", "1e-2[M]", "\u672c\u4f53\u6d53\u5ea6");
    model.param().set("r_d", "1e-3[cm]", "\u7535\u6781\u534a\u5f84");
    model.param().set("D_A", "1e-5[cm^2/s]", "\u6269\u6563\u7cfb\u6570");
    model.param().set("ka1", "k_a1_prime*D_A/r_d", "\u5438\u9644\u7387\u5e38\u6570");
    model.param().set("kd1", "k_d1_prime*c_A_bulk*D_A/r_d", "\u89e3\u5438\u7387\u5e38\u6570");
    model.param().set("k0", "D_A*k_0_prime/r_d^2", "\u7535\u5316\u5b66\u901f\u7387\u5e38\u6570");
    model.param().set("Gamma", "c_A_bulk*r_d/beta", "\u5355\u5c42\u9971\u548c\u8986\u76d6\u7387");
    model.param().set("nu", "sigma*R_const*T*D_A/F_const/r_d^2", "\u626b\u63cf\u901f\u7387");
    model.param().set("theta_A_init", "theta_A_eq*theta_A_init_prime", "\u521d\u59cb\u8868\u9762\u8986\u76d6\u7387");
    model.param().set("E_0", "0[V]", "\u8868\u89c2\u7535\u4f4d");
    model.param().set("K", "ka1/kd1", "\u5438\u9644-\u89e3\u5438\u901f\u7387\u5e38\u6570\u6bd4");
    model.param()
         .set("theta_A_eq", "K*c_A_bulk/(1+K*c_A_bulk)", "\u5e73\u8861\u72b6\u6001\u7684\u8868\u9762\u8986\u76d6\u7387");
    model.param().set("T", "298.15[K]", "\u6e29\u5ea6");
    model.param().set("E_start", "0.5[V]", "\u8d77\u59cb\u7535\u4f4d");
    model.param().set("E_vertex", "-0.5[V]", "\u9876\u70b9\u7535\u4f4d");
    model.param().set("L", "6*sqrt(D_A*2*abs(E_start-E_vertex)/nu)", "\u95f4\u9694\u957f\u5ea6");
    model.param().set("A_electrode", "pi*r_d^2", "\u7535\u6781\u9762\u79ef");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "L", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("tcd").prop("Ac").set("Ac", "A_electrode");
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_c_A", new String[]{"D_A", "0", "0", "0", "D_A", "0", "0", "0", "D_A"});
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_A_bulk", 0);
    model.component("comp1").physics("tcd").create("es1", "ElectrodeSurface", 0);
    model.component("comp1").physics("tcd").feature("es1").selection().set(1);
    model.component("comp1").physics("tcd").feature("es1").set("Gamma", "Gamma");
    model.component("comp1").physics("tcd").feature("es1").setIndex("AdsorbingDesorbingSpecies", "ads1", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Sigma", 1, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("AdsorbingDesorbingSpecies", "ads1", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Sigma", 1, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("AdsorbingDesorbingSpecies", "A_ads", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("AdsorbingDesorbingSpecies", "ads1", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Sigma", 1, 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Sigma", 1, 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("AdsorbingDesorbingSpecies", "ads1", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Sigma", 1, 1, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("AdsorbingDesorbingSpecies", "B_ads", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").set("BoundaryCondition", "CyclicVoltammetry");
    model.component("comp1").physics("tcd").feature("es1").set("sweeprate", "nu");
    model.component("comp1").physics("tcd").feature("es1").set("Evertex1", "E_start");
    model.component("comp1").physics("tcd").feature("es1").set("Evertex2", "E_vertex");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("minput_temperature_src", "userdef");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("minput_temperature", "T");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Viad", -1, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Viad", 1, 1, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("Eeq_mat", "userdef");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("Eeq", "E_0");
    model.component("comp1").physics("tcd").feature("es1").feature("er1")
         .set("ElectrodeKinetics", "ConcentrationDependentKinetics");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("i0", "k0*F_const*Gamma");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("CR", "tcd.theta_es1_B_ads");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("CO", "tcd.theta_es1_A_ads");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("r_ads", "ka1*tcd.thetafree_es1*c_A-kd1*tcd.theta_es1_A_ads");
    model.component("comp1").variable("var1").descr("r_ads", "\u5438\u9644\u7387");

    model.component("comp1").physics("tcd").feature("es1").create("nfr1", "NonFaradaicReactions", 0);
    model.component("comp1").physics("tcd").feature("es1").feature("nfr1").setIndex("species", true, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("nfr1").setIndex("J0", "-r_ads", 0);
    model.component("comp1").physics("tcd").feature("es1").feature("nfr1").setIndex("Rad", "r_ads", 0, 0);
    model.component("comp1").physics("tcd").feature("es1")
         .create("ivads1", "InitialValuesForAdsorbingDesorbingSpecies", 0);
    model.component("comp1").physics("tcd").feature("es1").feature("ivads1")
         .setIndex("theta0", "theta_A_init", 0, 0);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "L/10");
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.1);
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "L/10000");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "sigma", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "sigma", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "K_prime", 0);
    model.study("std1").feature("param").setIndex("plistarr", "1e5 1e-5 1e5", 0);
    model.study("std1").feature("param").setIndex("pname", "sigma", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "", 1);
    model.study("std1").feature("param").setIndex("pname", "sigma", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "", 1);
    model.study("std1").feature("param").setIndex("pname", "k_0_prime", 1);
    model.study("std1").feature("param").setIndex("plistarr", "1e2 1e2 1e-2", 1);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{""});
    model.result("pg1").feature("glob1").set("expr", new String[]{"tcd.Itot_es1 "});
    model.result("pg1").feature("glob1").set("descr", new String[]{""});
    model.result("pg1").label("\u5faa\u73af\u4f0f\u5b89\u56fe (tcd)");
    model.result("pg1").feature("glob1").set("titletype", "none");
    model.result("pg1").feature("glob1").set("xdata", "expr");
    model.result("pg1").feature("glob1").set("xdataexpr", "root.comp1.tcd.phis_es1 ");
    model.result("pg1").feature("glob1").set("xdatadescr", "\u7535\u52bf");
    model.result("pg1").feature("glob1").setIndex("descr", "\u603b\u7535\u6d41", 0);
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"tcd.phis_es1 "});
    model.result("pg2").feature("glob1").set("descr", new String[]{""});
    model.result("pg2").label("\u7535\u6781\u7535\u4f4d (tcd)");
    model.result("pg2").feature("glob1").setIndex("descr", "\u7535\u52bf", 0);
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("unit", new String[]{""});
    model.result("pg3").feature("glob1").set("expr", new String[]{"tcd.itotavg_es1 "});
    model.result("pg3").feature("glob1").set("descr", new String[]{""});
    model.result("pg3").label("\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6 (tcd)");
    model.result("pg3").feature("glob1").setIndex("descr", "\u7535\u6d41\u5bc6\u5ea6", 0);
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").label("\u6d53\u5ea6 (tcd)");
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("prefixintitle", "");
    model.result("pg4").set("expressionintitle", false);
    model.result("pg4").set("typeintitle", false);
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1);
    model.result("pg4").feature("lngr1").set("expr", new String[]{"c_A"});
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("legendmethod", "evaluated");
    model.result("pg1").feature("glob1").set("legendpattern", "K'=eval(K_prime), k<sub>0</sub>'=eval(k_0_prime)");
    model.result("pg1").run();
    model.result("pg1").set("legendpos", "upperleft");
    model.result("pg1").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u8868\u9762\u8986\u76d6\u7387\u548c\u6d53\u5ea6");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevelinput", "manual", 1);
    model.result("pg5").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").selection().set(1);
    model.result("pg5").feature("ptgr1").set("expr", "tcd.theta_es1_A_ads");
    model.result("pg5").feature("ptgr1")
         .set("descr", "\u5438\u9644-\u89e3\u5438\u7269\u8d28\u7684\u8868\u9762\u8986\u76d6\u7387\uff0c1 \u5206\u91cf");
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr1").setIndex("legends", "A<sub>ads</sub>", 0);
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr2").set("expr", "tcd.theta_es1_B_ads");
    model.result("pg5").feature("ptgr2")
         .set("descr", "\u5438\u9644-\u89e3\u5438\u7269\u8d28\u7684\u8868\u9762\u8986\u76d6\u7387\uff0c2 \u5206\u91cf");
    model.result("pg5").feature("ptgr2").setIndex("legends", "B<sub>ads</sub>", 0);
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr3").set("expr", "c_A");
    model.result("pg5").feature("ptgr3").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0cc_A");
    model.result("pg5").feature("ptgr3").setIndex("legends", "A", 0);
    model.result("pg5").run();
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "K'=eval(K_prime), k<sub>0</sub>'=eval(k_0_prime)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u8868\u9762\u8986\u76d6\u7387 (1)");
    model.result("pg5").set("twoyaxes", true);
    model.result("pg5").setIndex("plotonsecyaxis", true, 2, 1);
    model.result("pg5").set("legendpos", "uppermiddle");
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", new int[]{3}, 1);
    model.result("pg5").run();

    model.title("\u5438\u9644-\u8131\u9644\u4f0f\u5b89\u6cd5");

    model
         .description("\u5728\u7535\u5316\u5b66\u53cd\u5e94\u4e2d\uff0c\u53cd\u5e94\u7269\u901a\u5e38\u9700\u8981\u5148\u5438\u9644\u5230\u7535\u6781\u8868\u9762\uff0c\u624d\u80fd\u53d1\u751f\u8fd8\u539f\u6216\u6c27\u5316\u53cd\u5e94\uff0c\u4e4b\u540e\u751f\u6210\u7684\u4ea7\u7269\u518d\u4ece\u7535\u6781\u8868\u9762\u8131\u9644\u56de\u5230\u7535\u89e3\u8d28\u4e2d\u3002\n\n\u5982\u679c\u5438\u9644\u6216\u8131\u9644\u7684\u901f\u7387\u76f8\u5bf9\u4e8e\u7535\u5316\u5b66\u7535\u8377\u8f6c\u79fb\u6b65\u9aa4\u6765\u8bf4\u8f83\u6162\uff0c\u5219\u53ef\u80fd\u9700\u8981\u5728\u6a21\u578b\u4e2d\u8003\u8651\u5438\u9644-\u8131\u9644\u73b0\u8c61\u3002\n\n\u672c\u4f8b\u7814\u7a76\u5728\u5e73\u9762\u7535\u6781\u4e0a\u8fdb\u884c\u5faa\u73af\u4f0f\u5b89\u6cd5\u65f6\uff0c\u5438\u9644\u3001\u8131\u9644\u548c\u7535\u5b50\u8f6c\u79fb\u7684\u5404\u79cd\u52a8\u529b\u5b66\u53c2\u6570\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("adsorption_desorption_voltammetry.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
