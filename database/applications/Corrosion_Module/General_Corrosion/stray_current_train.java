/*
 * stray_current_train.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:45 by COMSOL 6.3.0.290. */
public class stray_current_train {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Corrosion_Module\\General_Corrosion");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("cp", "CathodicProtection", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/cp", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("rho_pond", "10[ohm*m]", "\u6de1\u6c34\u6c60\u5858\u7684\u7535\u963b\u7387");
    model.param().set("rho_clay", "50[ohm*m]", "\u7c98\u571f\u7684\u7535\u963b\u7387");
    model.param().set("rho_sand", "400[ohm*m]", "\u7802\u8d28\u7c98\u571f\u7684\u7535\u963b\u7387");
    model.param().set("rho_gravel", "500[ohm*m]", "\u783e\u77f3\u7684\u7535\u963b\u7387");
    model.param().set("rho_tie", "250[ohm*m]", "\u6df7\u51dd\u571f\u8f7b\u8f68\u7684\u7535\u963b\u7387");
    model.param().set("r_rail", "0.05[m]", "\u8f68\u9053\u534a\u5f84\uff0c\u5706\u67f1\u622a\u9762\u8fd1\u4f3c");
    model.param().set("t_train", "0[s]", "\u5217\u8f66\u901a\u884c\u65f6\u95f4");
    model.param().set("r_pipe", "0.2[m]", "\u7ba1\u534a\u5f84");
    model.param()
         .set("L_rail", "1240[m]", "\u94c1\u8f68\u957f\u5ea6\u6216\u7275\u5f15\u53d8\u7535\u7ad9\u4e4b\u95f4\u7684\u8ddd\u79bb");
    model.param().set("sigma_steel", "1e7[S/m]", "\u94c1\u7ba1\u58c1\u7535\u5bfc\u7387");
    model.param()
         .set("rho_tiex", "rho_tie*1/3+rho_gravel*2/3", "x \u65b9\u5411\u624e\u5e26\u7684\u7535\u963b\u7387\uff0c\u5047\u8bbe 1/3 \u548c 2/3 \u624e\u5e26");
    model.param().set("ro_pipe", "7850[kg/m^3]", "\u7ba1\u6750\u5bc6\u5ea6");
    model.param().set("M_pipe", "55.85[g/mol]", "\u7ba1\u6750\u6469\u5c14\u8d28\u91cf");
    model.param().set("L_rePos", "0[m]", "\u91cd\u5b9a\u4f4d\u7ba1\u9053");
    model.param().set("R_railins", "1e-3[ohm*m^2]", "\u8f68\u9053\u7684\u7edd\u7f18\u7535\u963b");
    model.param()
         .set("z_pipe", "-2.5[m]", "\u7ba1\u9053\u6240\u5728\u4f4d\u7f6e\u571f\u58e4\u4e2d\u7684\u6df1\u5ea6");

    model.func().create("gp1", "GaussianPulse");
    model.func("gp1").set("funcname", "shape_train");
    model.func("gp1").set("sigma", 4);
    model.func().create("int1", "Interpolation");
    model.func("int1").set("funcname", "I_train");
    model.func("int1")
         .set("table", new String[][]{{"0", "0"}, 
         {"12", "3000"}, 
         {"14", "3000"}, 
         {"16", "2900"}, 
         {"18", "2900"}, 
         {"22", "2500"}, 
         {"30", "100"}, 
         {"46", "100"}, 
         {"48", "1000"}, 
         {"52", "2000"}, 
         {"56", "2000"}, 
         {"60", "1500"}, 
         {"68", "1500"}, 
         {"72", "1000"}, 
         {"82", "1"}, 
         {"90", "1"}});
    model.func("int1").setIndex("fununit", "A", 0);
    model.func("int1").setIndex("argunit", "s", 0);
    model.func().create("int2", "Interpolation");
    model.func("int2").set("funcname", "loc_train");
    model.func("int2")
         .set("table", new String[][]{{"0", "2.506325147422217E-18"}, 
         {"1", "0.0013031328472963405"}, 
         {"2", "0.01729337959232767"}, 
         {"3", "0.08155518678585076"}, 
         {"4", "0.245527659027634"}, 
         {"5", "0.5752887776816231"}, 
         {"6", "1.149987743236385"}, 
         {"7", "2.0548407664070507"}, 
         {"8", "3.3800695160846743"}, 
         {"9", "5.218569603635264"}, 
         {"10", "7.6635172593263645"}, 
         {"11", "10.803112411719338"}, 
         {"12", "14.725254331584072"}, 
         {"13", "19.506312266634556"}, 
         {"14", "25.22202483307691"}, 
         {"15", "31.933517226980992"}, 
         {"16", "39.69106924185735"}, 
         {"17", "48.543113449935824"}, 
         {"18", "58.5089012347614"}, 
         {"19", "69.60924711499953"}, 
         {"20", "81.84958927914525"}, 
         {"21", "95.21973153737872"}, 
         {"22", "109.69795570036727"}, 
         {"23", "125.25184382730396"}, 
         {"24", "141.83619873631042"}, 
         {"25", "159.3947920511208"}, 
         {"26", "177.8574171823701"}, 
         {"27", "197.1422336883931"}, 
         {"28", "217.1995842383581"}, 
         {"29", "237.93126374022413"}, 
         {"30", "259.23668812858864"}, 
         {"31", "281.00885523288395"}, 
         {"32", "303.14890571560557"}, 
         {"33", "325.70903919272416"}, 
         {"34", "348.5241359866733"}, 
         {"35", "371.50518368983813"}, 
         {"36", "394.5610881952027"}, 
         {"37", "417.666674219605"}, 
         {"38", "440.9045913625406"}, 
         {"39", "464.1428458630448"}, 
         {"40", "487.3441960049409"}, 
         {"41", "510.476489580709"}, 
         {"42", "533.5538662308257"}, 
         {"43", "556.5924496642132"}, 
         {"44", "579.5706762900573"}, 
         {"45", "602.4973625169728"}, 
         {"46", "625.3886472237699"}, 
         {"47", "648.1954942882991"}, 
         {"48", "670.9349816968875"}, 
         {"49", "693.6386082930309"}, 
         {"50", "716.3269746649103"}, 
         {"51", "739.0253746607711"}, 
         {"52", "761.6276837735982"}, 
         {"53", "784.1997564678825"}, 
         {"54", "806.7522457042555"}, 
         {"55", "829.2945498197862"}, 
         {"56", "851.7235666204463"}, 
         {"57", "873.5199130911229"}, 
         {"58", "895.0677761621954"}, 
         {"59", "916.3671558336637"}, 
         {"60", "937.4180521055276"}, 
         {"61", "958.2204649777875"}, 
         {"62", "978.4455197559984"}, 
         {"63", "997.3758226107062"}, 
         {"64", "1015.6898508757763"}, 
         {"65", "1033.3876045512086"}, 
         {"66", "1050.4690836370032"}, 
         {"67", "1066.9342881331602"}, 
         {"68", "1082.3698907627947"}, 
         {"69", "1096.3054939908477"}, 
         {"70", "1109.2869614148894"}, 
         {"71", "1121.3142930349202"}, 
         {"72", "1132.3874888509401"}, 
         {"73", "1142.5065488629489"}, 
         {"74", "1151.6202230558565"}, 
         {"75", "1159.7018138347623"}, 
         {"76", "1166.7964699866234"}, 
         {"77", "1172.9041915114394"}, 
         {"78", "1178.024978409211"}, 
         {"79", "1182.1588306799372"}, 
         {"80", "1185.902960703433"}, 
         {"81", "1189.3247337401308"}, 
         {"82", "1192.0719599631645"}, 
         {"83", "1194.144639372534"}, 
         {"84", "1195.5427719682393"}, 
         {"85", "1196.2663577502806"}, 
         {"86", "1197.3644388393634"}, 
         {"87", "1198.421983358235"}, 
         {"88", "1199.2065629023834"}, 
         {"89", "1199.718177471809"}, 
         {"90", "1199.9568270665113"}});
    model.func("int2").setIndex("fununit", "m", 0);
    model.func("int2").setIndex("argunit", "s", 0);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "stray_current_train_geometry.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "z_pipe");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 50, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "100+L_rePos", 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 300, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "-150+L_rePos", 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 800, 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "-250+L_rePos", 2, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 1100, 3, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "-250+L_rePos", 3, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").set(4, 7, 9);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").set(3, 6, 8);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").set(1);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").set(2);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").set(5);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").geom(1);
    model.component("comp1").selection("sel6").set(34, 55, 135);
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").geom(1);
    model.component("comp1").selection("sel7").set(30, 31, 96, 98, 177, 179);
    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").geom(1);
    model.component("comp1").selection("sel8").set(31, 98, 179);
    model.component("comp1").selection().create("sel9", "Explicit");
    model.component("comp1").selection("sel9").geom(1);
    model.component("comp1").selection("sel9").set(30, 31, 34, 55, 96, 98, 135, 177, 179);
    model.component("comp1").selection().create("sel10", "Explicit");
    model.component("comp1").selection("sel10").geom(0);
    model.component("comp1").selection("sel10").set(14, 15);
    model.component("comp1").selection().create("sel11", "Explicit");
    model.component("comp1").selection("sel11").geom(0);
    model.component("comp1").selection("sel11").set(114, 115);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intop_tss1");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().named("sel10");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "intop_tss2");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop2").selection().named("sel11");
    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").set("opname", "maxop_uprail");
    model.component("comp1").cpl("maxop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("maxop1").selection().named("sel8");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 1);
    model.component("comp1").variable("var1").selection().named("sel7");
    model.component("comp1").variable("var1")
         .set("qls_rail", "I_train(t_train)*shape_train(x/1[m]-loc_train(t_train)/1[m])/2[m]");
    model.component("comp1").variable("var1").descr("qls_rail", "Train current source along each rail");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").selection().geom("geom1", 1);
    model.component("comp1").variable("var2").selection().named("sel6");
    model.component("comp1").variable("var2").set("dr_rate", "cp.iloc_er1/F_const/ro_pipe/2*M_pipe*(cp.iloc_er1>0)");
    model.component("comp1").variable("var2").descr("dr_rate", "Corrosion rate on pipe");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").selection().geom("geom1", 0);
    model.component("comp1").variable("var3").selection().named("sel10");
    model.component("comp1").variable("var3").set("I_tss1", "-I_train(t_train)*(1-loc_train(t_train)/L_rail)/2");
    model.component("comp1").variable("var3").descr("I_tss1", "Total current at each rail at traction substation 1");
    model.component("comp1").variable().create("var4");
    model.component("comp1").variable("var4").selection().geom("geom1", 0);
    model.component("comp1").variable("var4").selection().named("sel11");
    model.component("comp1").variable("var4").set("I_tss2", "-cp.Is_edgex*cp.A");
    model.component("comp1").variable("var4").descr("I_tss2", "Total current at each rail at traction substation 2");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("LocalCurrentDensity", "LocalCurrentDensity", "Local current density");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat1").label("Q235 steel in soil");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").label("Local current density");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("funcname", "iloc_exp");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("table", new String[][]{{"-0.425", "3e-3"}, 
         {"-0.56", "0"}, 
         {"-0.72", "-18e-3"}, 
         {"-0.95", "-66e-3"}, 
         {"-1.1", "-90e-3"}, 
         {"-1.14", "-105e-3"}, 
         {"-1.18", "-126e-3"}});
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("fununit", new String[]{"A/m^2"});
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").func("int1")
         .set("argunit", new String[]{"V"});
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .set("ilocmat", "iloc_exp(E_vs_ref_exp)");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .setPropertyInfo("ilocmat", "G. Cui, Z. Li, C. Yang and M. Wang, \u201cThe influence of DC stray current on pipeline corrosion\u201d, Petroleum Science, vol. 13, p. 135, 2016.");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").set("E_vs_SHE", "V");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .descr("E_vs_SHE", "\u7535\u6781\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .set("E_ref_exp_vs_SHE", "0.314 [V]");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .descr("E_ref_exp_vs_SHE", "\u5b9e\u9a8c\u4e2d\u7684\u53c2\u6bd4\u7535\u6781\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .set("E_vs_ref_exp", "E_vs_SHE-E_ref_exp_vs_SHE");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity")
         .descr("E_vs_ref_exp", "\u7535\u6781\u7535\u4f4d vs. \u5b9e\u9a8c CSE \u53c2\u6bd4\u7535\u6781");
    model.component("comp1").material("mat1").propertyGroup("LocalCurrentDensity").addInput("electricpotential");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("Eeq", "Eeq_vs_SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "G. Cui, Z. Li, C. Yang and M. Wang, \u201cThe influence of DC stray current on pipeline corrosion\u201d, Petroleum Science, vol. 13, p. 135, 2016.");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("dEeqdT", "0[V/K]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("cEeqref", "0[mol/m^3]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("Eeq_vs_ref_exp", "-0.56 [V]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .descr("Eeq_vs_ref_exp", "\u5e73\u8861\uff08\u5f00\u8def\uff09\u7535\u4f4d vs. CSE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .set("E_ref_exp_vs_SHE", "0.314 [V]");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .descr("E_ref_exp_vs_SHE", "\u5b9e\u9a8c\u4e2d\u7684\u53c2\u6bd4\u7535\u6781\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .set("Eeq_vs_SHE", "Eeq_vs_ref_exp+E_ref_exp_vs_SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .descr("Eeq_vs_SHE", "\u5e73\u8861\uff08\u5f00\u8def\uff09\u7535\u4f4d vs. SHE");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").addInput("concentration");

    model.component("comp1").physics("cp").feature("ice1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cp").feature("ice1")
         .set("sigmal", new String[]{"1/rho_clay", "0", "0", "0", "1/rho_clay", "0", "0", "0", "1/rho_clay"});
    model.component("comp1").physics("cp").create("ice2", "Electrolyte", 3);
    model.component("comp1").physics("cp").feature("ice2").selection().named("sel4");
    model.component("comp1").physics("cp").feature("ice2").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cp").feature("ice2")
         .set("sigmal", new String[]{"1/rho_pond", "0", "0", "0", "1/rho_pond", "0", "0", "0", "1/rho_pond"});
    model.component("comp1").physics("cp").create("ice3", "Electrolyte", 3);
    model.component("comp1").physics("cp").feature("ice3").selection().named("sel5");
    model.component("comp1").physics("cp").feature("ice3").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cp").feature("ice3")
         .set("sigmal", new String[]{"1/rho_sand", "0", "0", "0", "1/rho_sand", "0", "0", "0", "1/rho_sand"});
    model.component("comp1").physics("cp").create("ice4", "Electrolyte", 3);
    model.component("comp1").physics("cp").feature("ice4").selection().named("sel1");
    model.component("comp1").physics("cp").feature("ice4").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cp").feature("ice4")
         .set("sigmal", new String[]{"1/rho_tiex", "0", "0", "0", "1/rho_tie", "0", "0", "0", "1/rho_tie"});
    model.component("comp1").physics("cp").create("ice5", "Electrolyte", 3);
    model.component("comp1").physics("cp").feature("ice5").selection().named("sel2");
    model.component("comp1").physics("cp").feature("ice5").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cp").feature("ice5")
         .set("sigmal", new String[]{"1/rho_gravel", "0", "0", "0", "1/rho_gravel", "0", "0", "0", "1/rho_gravel"});
    model.component("comp1").physics("cp").create("edge1", "EdgeElectrode", 1);
    model.component("comp1").physics("cp").feature("edge1").selection().named("sel7");
    model.component("comp1").physics("cp").feature("edge1").set("redge", "r_rail");
    model.component("comp1").physics("cp").feature("edge1").set("FilmResistanceType", "SurfaceResistance");
    model.component("comp1").physics("cp").feature("edge1").set("Rf", "R_railins");
    model.component("comp1").physics("cp").feature("edge1").feature("er1").set("Eeq_mat", "from_mat");
    model.component("comp1").physics("cp").feature("edge1").feature("er1").set("ilocmat_mat", "from_mat");
    model.component("comp1").physics("cp").feature("edge1").create("et1", "ElectricCurrent", 0);
    model.component("comp1").physics("cp").feature("edge1").feature("et1").selection().named("sel10");
    model.component("comp1").physics("cp").feature("edge1").feature("et1").set("Its", "I_tss1");
    model.component("comp1").physics("cp").feature("edge1").create("pot1", "EdgeElectricPotential", 0);
    model.component("comp1").physics("cp").feature("edge1").feature("pot1").selection().named("sel11");
    model.component("comp1").physics("cp").feature("edge1").create("extcs1", "EdgeExternalCurrentSource", 1);
    model.component("comp1").physics("cp").feature("edge1").feature("extcs1").set("qls", "qls_rail");
    model.component("comp1").physics("cp").create("edge2", "EdgeElectrode", 1);
    model.component("comp1").physics("cp").feature("edge2").selection().named("sel6");
    model.component("comp1").physics("cp").feature("edge2").set("redge", "r_pipe");
    model.component("comp1").physics("cp").feature("edge2").set("ElectricPotentialModelSelection", "Floating");
    model.component("comp1").physics("cp").feature("edge2").feature("er1").set("Eeq_mat", "from_mat");
    model.component("comp1").physics("cp").feature("edge2").feature("er1").set("ilocmat_mat", "from_mat");

    model.component("comp1").material("mat1").selection().geom("geom1", 1);
    model.component("comp1").material("mat1").selection().named("sel9");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma_steel"});

    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 6);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().named("sel6");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmax", 10);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size3", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size3").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size3").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size3").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size3").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size3").set("hmax", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size3").set("hnarrowactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size3").set("hnarrow", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "rho_pond", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "\u03a9*m", 0);
    model.study("std1").feature("param").setIndex("pname", "rho_pond", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "\u03a9*m", 0);
    model.study("std1").feature("param").setIndex("pname", "L_rePos", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0 0 50", 0);
    model.study("std1").feature("param").setIndex("pname", "rho_pond", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "\u03a9*m", 1);
    model.study("std1").feature("param").setIndex("pname", "rho_pond", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "\u03a9*m", 1);
    model.study("std1").feature("param").setIndex("pname", "rho_sand", 1);
    model.study("std1").feature("param").setIndex("plistarr", "400 50 400", 1);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "rho_pond", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "\u03a9*m", 0);
    model.study("std1").feature("stat").setIndex("pname", "rho_pond", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "\u03a9*m", 0);
    model.study("std1").feature("stat").setIndex("pname", "t_train", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,3,90)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 31, 0);
    model.result("pg1").setIndex("looplevel", 3, 1);
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (cp)");
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", new String[]{"phil"});
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"cp.Ilx", "cp.Ily", "cp.Ilz"});
    model.result("pg1").feature("str1").set("posmethod", "start");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 31, 0);
    model.result("pg2").setIndex("looplevel", 3, 1);
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (cp)");
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"cp.Ilx", "cp.Ily", "cp.Ilz"});
    model.result("pg2").feature("str1").set("posmethod", "start");
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result("pg2").feature("str1").create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "root.comp1.cp.IlMag");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 31, 0);
    model.result("pg3").setIndex("looplevel", 3, 1);
    model.result("pg3").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (cp)");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"cp.Ilx", "cp.Ily", "cp.Ilz"});
    model.result("pg3").feature("str1").set("posmethod", "start");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", new String[]{"cp.Evsref"});
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("radiusexpr", "root.comp1.cp.redge");
    model.result("pg3").feature("line1").set("tuberadiusscaleactive", "on");
    model.result("pg3").feature("line1").set("tuberadiusscale", "1");

    model.component("comp1").geom("geom1").run();

    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 19, 0);
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("paramindicator", "t= eval(t_train) s");
    model.result("pg2").set("edges", false);
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").run();
    model.result("pg2").feature().remove("str1");
    model.result("pg2").run();
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").feature("vol1").create("sel1", "Selection");
    model.result("pg2").feature("vol1").feature("sel1").selection().named("sel3");
    model.result("pg2").run();
    model.result("pg2").feature("vol1").create("tran1", "Transparency");
    model.result("pg2").run();
    model.result("pg2").feature("vol1").feature("tran1").set("transparency", 0.7);
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", "qls_rail>1e-6");
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("line1").set("tuberadiusscale", 4);
    model.result("pg2").feature("line1").set("tubeendcaps", false);
    model.result("pg2").feature("line1").set("coloring", "gradient");
    model.result("pg2").feature("line1").set("topcolor", "black");
    model.result("pg2").feature("line1").set("bottomcolor", "gray");
    model.result("pg2").feature("line1").set("colorlegend", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "blue");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().set(7);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").create("tran1", "Transparency");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg2").feature("surf1").feature("tran1").set("uniformblending", 1);
    model.result("pg2").run();

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").feature().duplicate("surf2", "surf1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("color", "custom");
    model.result("pg2").feature("surf2")
         .set("customcolor", new double[]{0.7686274647712708, 0.4156862795352936, 0.2823529541492462});
    model.result("pg2").run();
    model.result("pg2").feature("surf2").feature("sel1").selection().set(28, 40);
    model.result("pg2").run();
    model.result("pg2").feature("surf2").feature("tran1").set("transparency", 0.85);
    model.result("pg2").feature("surf2").feature("tran1").set("uniformblending", 0);
    model.result("pg2").create("line2", "Line");
    model.result("pg2").feature("line2").set("expr", "cp.iloc_er1");
    model.result("pg2").feature("line2").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg2").feature("line2").set("linetype", "tube");
    model.result("pg2").feature("line2").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("line2").set("tuberadiusscale", 3);
    model.result("pg2").feature("line2").create("sel1", "Selection");
    model.result("pg2").feature("line2").feature("sel1").selection().named("sel6");
    model.result("pg2").create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").setIndex("expr", 0, 0);
    model.result("pg2").feature("arwl1").set("expr", new String[]{"0", "0", "cp.iloc_er1"});
    model.result("pg2").feature("arwl1").set("arrowcount", 80);
    model.result("pg2").feature("arwl1").set("scaleactive", true);
    model.result("pg2").feature("arwl1").set("scale", 40000);
    model.result("pg2").feature("arwl1").create("sel1", "Selection");
    model.result("pg2").feature("arwl1").feature("sel1").selection().named("sel6");
    model.result("pg2").run();
    model.result("pg2").feature("arwl1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("arwl1").feature("col1").set("expr", "cp.iloc_er1");
    model.result("pg2").feature("arwl1").feature("col1").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg2").feature("arwl1").feature("col1").set("colorlegend", false);
    model.result("pg2").create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("xnumber", "0");
    model.result("pg2").feature("strmsl1").set("ynumber", "0");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "z_pipe");
    model.result("pg2").feature("strmsl1").set("number", 70);
    model.result("pg2").feature("strmsl1").set("pointtype", "arrow");
    model.result("pg2").feature("strmsl1").set("color", "gray");
    model.result("pg2").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevelinput", "first", 1);
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("twoyaxes", true);
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("yseclabelactive", true);
    model.result("pg4").set("legendlayout", "outside");
    model.result("pg4").set("legendposoutside", "top");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("plotonsecyaxis", true);
    model.result("pg4").feature("glob1").setIndex("expr", "loc_train(t_train)", 0);
    model.result("pg4").feature("glob1").set("linestyle", "dotted");
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").feature("glob1").setIndex("legends", "Traveled distance", 0);
    model.result("pg4").create("glob2", "Global");
    model.result("pg4").feature("glob2").set("markerpos", "datapoints");
    model.result("pg4").feature("glob2").set("linewidth", "preference");
    model.result("pg4").feature("glob2").setIndex("expr", "I_train(t_train)", 0);
    model.result("pg4").feature("glob2").setIndex("expr", "intop_tss1(I_tss1)", 1);
    model.result("pg4").feature("glob2").setIndex("expr", "intop_tss2(I_tss2)", 2);
    model.result("pg4").feature("glob2").set("legendmethod", "manual");
    model.result("pg4").feature("glob2").setIndex("legends", "Train propulsion", 0);
    model.result("pg4").feature("glob2").setIndex("legends", "TSS 1", 1);
    model.result("pg4").feature("glob2").setIndex("legends", "TSS 2", 2);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevelinput", "first", 1);
    model.result("pg5").setIndex("looplevelinput", "manual", 0);
    model.result("pg5").setIndex("looplevel", new int[]{5, 9, 13, 19, 21, 26}, 0);
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").selection().named("sel8");
    model.result("pg5").feature("lngr1").set("expr", "cp.phis_edge");
    model.result("pg5").feature("lngr1").set("descr", "\u7535\u52bf");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "manual");
    model.result("pg5").feature("lngr1").setIndex("legends", "12 s", 0);
    model.result("pg5").feature("lngr1").setIndex("legends", "24 s", 1);
    model.result("pg5").feature("lngr1").setIndex("legends", "36 s", 2);
    model.result("pg5").feature("lngr1").setIndex("legends", "54 s", 3);
    model.result("pg5").feature("lngr1").setIndex("legends", "60 s", 4);
    model.result("pg5").feature("lngr1").setIndex("legends", "75 s", 5);
    model.result("pg5").feature().duplicate("lngr2", "lngr1");
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").set("expr", "maxop_uprail(cp.phis_edge)");
    model.result("pg5").feature("lngr2").set("xdataexpr", "loc_train(t_train)");
    model.result("pg5").feature("lngr2").set("linestyle", "none");
    model.result("pg5").feature("lngr2").set("linecolor", "cyclereset");
    model.result("pg5").feature("lngr2").set("linewidth", 2);
    model.result("pg5").feature("lngr2").set("linemarker", "square");
    model.result("pg5").feature("lngr2").set("legend", false);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").set("legendpos", "lowermiddle");
    model.result("pg6").run();
    model.result("pg6").feature("lngr1").set("expr", "cp.iloc_er1");
    model.result("pg6").feature("lngr1").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg6").run();
    model.result("pg6").feature("lngr2").set("expr", "maxop_uprail(cp.iloc_er1)");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").set("legendpos", "lowerright");
    model.result("pg7").run();
    model.result("pg7").feature("lngr1").selection().named("sel6");
    model.result("pg7").feature("lngr1").set("xdata", "arc");
    model.result("pg7").run();
    model.result("pg7").feature().remove("lngr2");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevelinput", "all", 1);
    model.result("pg8").setIndex("looplevel", new int[]{19}, 0);
    model.result("pg8").set("legendpos", "uppermiddle");
    model.result("pg8").run();
    model.result("pg8").feature("lngr1").set("expr", "dr_rate");
    model.result("pg8").feature("lngr1").set("descr", "Corrosion rate on pipe");
    model.result("pg8").feature("lngr1").set("unit", "mm/yr");
    model.result("pg8").feature("lngr1").setIndex("legends", "Base scenario", 0);
    model.result("pg8").feature("lngr1").setIndex("legends", "\\rho <sub>sand</sub>=\\rho <sub>clay</sub>", 1);
    model.result("pg8").feature("lngr1").setIndex("legends", "L<sub>rePos</sub>=50 m (+y-direction)", 2);
    model.result("pg8").run();
    model.result("pg1").run();
    model.result().remove("pg1");
    model.result().remove("pg3");
    model.result("pg2").run();
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
    model.result().export("anim1").run();
    model.result("pg8").run();

    model.title("\u8f7b\u8f68\u4ea4\u901a\u7cfb\u7edf\u4e2d\u5217\u8f66\u7684\u6742\u6563\u7535\u6d41");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u8f7b\u8f68\u4ea4\u901a (LRT) \u7cfb\u7edf\u4e2d\u5217\u8f66\u8fd0\u884c\u65f6\u4ea7\u751f\u7684\u6742\u6563\u7535\u6d41\uff0c\u4ee5\u53ca\u8fd9\u4e9b\u7535\u6d41\u5bf9\u9644\u8fd1\u7ba1\u9053\u8150\u8680\u7684\u5f71\u54cd\uff1b\u540c\u65f6\u7814\u7a76\u4e86\u571f\u58e4\u7535\u5bfc\u7387\u548c\u7ba1\u9053\u4f4d\u7f6e\u53d8\u5316\u5bf9\u7cfb\u7edf\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("stray_current_train.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
