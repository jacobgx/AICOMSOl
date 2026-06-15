/*
 * v_flow_battery.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:19 by COMSOL 6.3.0.290. */
public class v_flow_battery {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Flow_Batteries");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryCurrentDistributionNernstPlanck", "geom1");
    model.component("comp1").physics("tcd").field("concentration").field("cSO4_neg");
    model.component("comp1").physics("tcd").field("concentration")
         .component(new String[]{"cSO4_neg", "cHSO4_neg", "cH_neg", "cV2", "cV3"});
    model.component("comp1").physics("tcd").field("electricpotentialionicphase").field("phil_neg");
    model.component("comp1").physics("tcd").field("electricpotential").field("phis_neg");
    model.component("comp1").physics().create("tcd2", "TertiaryCurrentDistributionNernstPlanck", "geom1");
    model.component("comp1").physics("tcd2").field("concentration").field("cSO4_mem");
    model.component("comp1").physics("tcd2").field("concentration")
         .component(new String[]{"cSO4_mem", "cHSO4_mem", "cH_mem", "cV2_mem", "cV3_mem", "cV4_mem", "cV5_mem"});
    model.component("comp1").physics("tcd2").field("electricpotentialionicphase").field("phil_mem");
    model.component("comp1").physics("tcd2").field("electricpotential").field("phis_mem");
    model.component("comp1").physics().create("tcd3", "TertiaryCurrentDistributionNernstPlanck", "geom1");
    model.component("comp1").physics("tcd3").field("concentration").field("cSO4_pos");
    model.component("comp1").physics("tcd3").field("concentration")
         .component(new String[]{"cSO4_pos", "cHSO4_pos", "cH_pos", "cV4", "cV5"});
    model.component("comp1").physics("tcd3").field("electricpotentialionicphase").field("phil_pos");
    model.component("comp1").physics("tcd3").field("electricpotential").field("phis_pos");

    model.study().create("std1");
    model.study("std1").create("cdi", "CurrentDistributionInitialization");
    model.study("std1").feature("cdi").set("ftplistmethod", "manual");
    model.study("std1").feature("cdi").set("solnum", "auto");
    model.study("std1").feature("cdi").set("notsolnum", "auto");
    model.study("std1").feature("cdi").set("outputmap", new String[]{});
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").setSolveFor("/physics/tcd", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/tcd2", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/tcd3", true);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("ftplistmethod", "manual");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/tcd", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tcd2", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tcd3", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("H_cell", "0.035[m]", "\u7535\u6c60\u9ad8\u5ea6");
    model.param().set("wCell", "0.0285[m]", "\u7535\u6c60\u6df1\u5ea6");
    model.param().set("L_e", "0.004[m]", "\u7535\u6781\u539a\u5ea6");
    model.param().set("L_m", "203e-6[m]", "\u819c\u539a\u5ea6");
    model.param().set("DV2", "2.4e-10[m^2/s]", "V(2+) \u6269\u6563\u7cfb\u6570");
    model.param().set("DV3", "2.4e-10[m^2/s]", "V(3+) \u6269\u6563\u7cfb\u6570");
    model.param().set("DV4", "3.9e-10[m^2/s]", "VO(2+) \u6269\u6563\u7cfb\u6570");
    model.param().set("DV5", "3.9e-10[m^2/s]", "VO2(+) \u6269\u6563\u7cfb\u6570");
    model.param().set("DH", "9.312e-9[m^2/s]", "H(+) \u6269\u6563\u7cfb\u6570");
    model.param().set("DSO4", "1.065e-9[m^2/s]", "SO4(2-) \u6269\u6563\u7cfb\u6570");
    model.param().set("DHSO4", "1.33e-9[m^2/s]", "HSO4(-) \u6269\u6563\u7cfb\u6570");
    model.param().set("v", "30[ml/min]/(wCell*L_e)", "\u5165\u53e3\u901f\u5ea6");
    model.param().set("T", "293.15[K]", "\u7535\u6c60\u6e29\u5ea6");
    model.param().set("sigma_e", "66.7[S/m]", "\u7535\u6781\u7535\u5bfc\u7387");
    model.param().set("epsilon", "0.93", "\u7535\u6781\u5b54\u9699\u7387");
    model.param().set("a", "35e4[m^2/m^3]", "\u7535\u6781\u6bd4\u9762\u79ef");
    model.param().set("E0_pos", "1.004[V]", "\u6807\u51c6\u7535\u4f4d\uff0c\u6b63\u53cd\u5e94");
    model.param().set("k_pos", "2.5e-8[m/s]", "\u901f\u7387\u5e38\u6570\uff0c\u6b63\u53cd\u5e94");
    model.param().set("alpha_pos", "0.55", "\u4f20\u9012\u7cfb\u6570\uff0c\u6b63\u53cd\u5e94");
    model.param().set("E0_neg", "-0.255[V]", "\u6807\u51c6\u7535\u4f4d\uff0c\u8d1f\u53cd\u5e94");
    model.param().set("k_neg", "7e-8[m/s]", "\u901f\u7387\u5e38\u6570\uff0c\u8d1f\u53cd\u5e94");
    model.param().set("alpha_neg", "0.45", "\u4f20\u9012\u7cfb\u6570\uff0c\u8d1f\u53cd\u5e94");
    model.param().set("beta", "0.25", "\u89e3\u79bb\u5e38\u6570");
    model.param().set("kd", "1e4[mol/(m^3*s)]", "HSO4(-) \u89e3\u79bb\u901f\u7387\u5e38\u6570");
    model.param().set("cHm", "1.99[mol/l]", "\u819c\u8d28\u5b50\u6d53\u5ea6");
    model.param().set("cV2_0", "156[mol/m^3]", "V(2+) \u521d\u59cb\u6d53\u5ea6");
    model.param().set("cV3_0", "884[mol/m^3]", "V(3+) \u521d\u59cb\u6d53\u5ea6");
    model.param().set("cV4_0", "884[mol/m^3]", "VO(2+) \u521d\u59cb\u6d53\u5ea6");
    model.param().set("cV5_0", "156[mol/m^3]", "VO2(+) \u521d\u59cb\u6d53\u5ea6");
    model.param().set("cH_0_neg", "4447.5[mol/m^3]", "H(+) \u521d\u59cb\u6d53\u5ea6\uff0c\u8d1f\u6781");
    model.param().set("cH_0_pos", "5097.5[mol/m^3]", "H(+) \u521d\u59cb\u6d53\u5ea6\uff0c\u6b63\u6781");
    model.param().set("cHSO4_0_neg", "2668.5[mol/m^3]", "HSO4(-) \u521d\u59cb\u6d53\u5ea6\uff0c\u8d1f\u6781");
    model.param().set("cHSO4_0_pos", "3058.5[mol/m^3]", "HSO4(-) \u521d\u59cb\u6d53\u5ea6\uff0c\u6b63\u6781");
    model.param().set("i_avg", "-100[mA/cm^2]", "\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6");
    model.param()
         .set("c_fix", "0[M]", "\uff08\u5728\u201c\u7535\u6d41\u5206\u5e03\u521d\u59cb\u5316\u201d\u8fc7\u7a0b\u4e2d\u4f7f\u7528 0[M]\uff0c\u5728\u201c\u8f85\u52a9\u626b\u63cf\u201d\u540e\u4f7f\u7528 -cHm\uff09");
    model.param()
         .set("i0ref_neg", "F_const*k_neg*1[mol/l]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u8d1f\u53cd\u5e94");
    model.param()
         .set("i0ref_pos", "F_const*k_pos*1[mol/l]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6b63\u53cd\u5e94");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L_e", "H_cell"});
    model.component("comp1").geom("geom1").feature("r1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("r1").label("\u8d1f\u6781");
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"L_m", "H_cell"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"L_e", "0"});
    model.component("comp1").geom("geom1").feature("r2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("r2").label("\u819c");
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"L_e", "H_cell"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"L_e+L_m", "0"});
    model.component("comp1").geom("geom1").feature("r3").set("selresult", true);
    model.component("comp1").geom("geom1").feature("r3").label("\u6b63\u6781");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().named("geom1_r1_dom");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("aV3", "max(cV3/1[mol/l],eps)", "V(3+) \u6d3b\u6027");
    model.component("comp1").variable("var1").set("aV2", "max(cV2/1[mol/l],eps)", "V(2+) \u6d3b\u6027");
    model.component("comp1").variable("var1").set("aH", "max(cH_neg/1[mol/l],eps)", "H(+) \u6d3b\u6027");
    model.component("comp1").variable("var1").set("aHSO4", "max(cHSO4_neg/1[mol/l],eps)", "HSO4(-) \u6d3b\u6027");
    model.component("comp1").variable("var1").set("aSO4", "max(cSO4_neg/1[mol/l],eps)", "SO4(2-) \u6d3b\u6027");
    model.component("comp1").variable("var1")
         .set("rd", "kd*((aH-aHSO4)/(aH+aHSO4)-beta)", "\u89e3\u79bb\u901f\u7387");
    model.component("comp1").variable("var1")
         .set("phil", "phil_neg", "\u7535\u89e3\u8d28\u7535\u4f4d\uff08\u7528\u4e8e\u540e\u5904\u7406\uff09");
    model.component("comp1").variable("var1")
         .set("phis", "phis_neg", "\u7535\u6781\u7535\u4f4d\uff08\u7528\u4e8e\u540e\u5904\u7406\uff09");
    model.component("comp1").variable("var1")
         .set("Hx", "tcd.tflux_cH_negx", "H(+) \u901a\u91cf\uff08\u7528\u4e8e\u540e\u5904\u7406\uff09");
    model.component("comp1").variable("var1")
         .set("SO4x", "tcd.tflux_cSO4_negx", "SO4(2-) \u901a\u91cf\uff08\u7528\u4e8e\u540e\u5904\u7406\uff09");
    model.component("comp1").variable("var1")
         .set("HSO4x", "tcd.tflux_cHSO4_negx", "HSO4(-) \u901a\u91cf\uff08\u7528\u4e8e\u540e\u5904\u7406\uff09");
    model.component("comp1").variable("var1").label("\u8d1f\u6781\u53d8\u91cf");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").selection().geom("geom1", 2);
    model.component("comp1").variable("var2").selection().named("geom1_r2_dom");

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2")
         .set("phil", "phil_mem", "\u7535\u89e3\u8d28\u7535\u4f4d\uff08\u7528\u4e8e\u540e\u5904\u7406\uff09");
    model.component("comp1").variable("var2").set("aH", "max(cH_mem/1[mol/l],eps)", "H(+) \u6d3b\u6027");
    model.component("comp1").variable("var2").set("aHSO4", "max(cHSO4_mem/1[mol/l],eps)", "HSO4(-) \u6d3b\u6027");
    model.component("comp1").variable("var2").set("aSO4", "max(cSO4_mem/1[mol/l],eps)", "SO4(2-) \u6d3b\u6027");
    model.component("comp1").variable("var2").set("aV2", "max(cV2_mem/1[mol/l],eps)", "V(2+) \u6d3b\u6027");
    model.component("comp1").variable("var2").set("aV3", "max(cV3_mem/1[mol/l],eps)", "V(3+) \u6d3b\u6027");
    model.component("comp1").variable("var2").set("aV4", "max(cV4_mem/1[mol/l],eps)", "V(4+) \u6d3b\u6027");
    model.component("comp1").variable("var2").set("aV5", "max(cV5_mem/1[mol/l],eps)", "V(5+) \u6d3b\u6027");
    model.component("comp1").variable("var2")
         .set("rd", "kd*((aH-aHSO4)/(aH+aHSO4)-beta)", "\u89e3\u79bb\u901f\u7387");
    model.component("comp1").variable("var2")
         .set("Hx", "tcd2.tflux_cH_memx", "H(+) \u901a\u91cf\uff08\u7528\u4e8e\u540e\u5904\u7406\uff09");
    model.component("comp1").variable("var2")
         .set("SO4x", "tcd2.tflux_cSO4_memx", "SO4(2-) \u901a\u91cf\uff08\u7528\u4e8e\u540e\u5904\u7406\uff09");
    model.component("comp1").variable("var2")
         .set("HSO4x", "tcd2.tflux_cHSO4_memx", "HSO4(-) \u901a\u91cf\uff08\u7528\u4e8e\u540e\u5904\u7406\uff09");
    model.component("comp1").variable("var2").label("\u819c\u53d8\u91cf");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").selection().geom("geom1", 2);
    model.component("comp1").variable("var3").selection().named("geom1_r3_dom");

//    To import content from file, use:
//    model.component("comp1").variable("var3").loadFile("FILENAME");
    model.component("comp1").variable("var3").set("aV4", "max(cV4/1[mol/l],eps)", "VO(2+) \u6d3b\u6027");
    model.component("comp1").variable("var3").set("aV5", "max(cV5/1[mol/l],eps)", "VO2(+) \u6d3b\u6027");
    model.component("comp1").variable("var3").set("aH", "max(cH_pos/1[mol/l],eps)", "H(+) \u6d3b\u6027");
    model.component("comp1").variable("var3").set("aHSO4", "max(cHSO4_pos/1[mol/l],eps)", "HSO4(-) \u6d3b\u6027");
    model.component("comp1").variable("var3").set("aSO4", "max(cSO4_pos/1[mol/l],eps)", "SO4(2-) \u6d3b\u6027");
    model.component("comp1").variable("var3")
         .set("rd", "kd*((aH-aHSO4)/(aH+aHSO4)-beta)", "\u89e3\u79bb\u901f\u7387");
    model.component("comp1").variable("var3")
         .set("phil", "phil_pos", "\u7535\u89e3\u8d28\u7535\u4f4d\uff08\u7528\u4e8e\u540e\u5904\u7406\uff09");
    model.component("comp1").variable("var3")
         .set("phis", "phis_pos", "\u7535\u6781\u7535\u4f4d\uff08\u7528\u4e8e\u540e\u5904\u7406\uff09");
    model.component("comp1").variable("var3")
         .set("Hx", "tcd3.tflux_cH_posx", "H(+) \u901a\u91cf\uff08\u7528\u4e8e\u540e\u5904\u7406\uff09");
    model.component("comp1").variable("var3")
         .set("SO4x", "tcd3.tflux_cSO4_posx", "SO4(2-) \u901a\u91cf\uff08\u7528\u4e8e\u540e\u5904\u7406\uff09");
    model.component("comp1").variable("var3")
         .set("HSO4x", "tcd3.tflux_cHSO4_posx", "HSO4(-) \u901a\u91cf\uff08\u7528\u4e8e\u540e\u5904\u7406\uff09");
    model.component("comp1").variable("var3").label("\u6b63\u6781\u53d8\u91cf");

    model.component("comp1").physics("tcd")
         .label("\u4e09\u6b21\u7535\u6d41\u5206\u5e03\uff0c\u80fd\u65af\u7279-\u666e\u6717\u514b\uff08\u8d1f\u6781\uff09");
    model.component("comp1").physics("tcd").selection().named("geom1_r1_dom");
    model.component("comp1").physics("tcd").prop("dz").set("dz", "wCell");
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", -2, 0);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", -1, 1);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", 1, 2);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", 2, 3);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", 3, 4);
    model.component("comp1").physics("tcd").create("pce1", "PorousElectrode", 2);
    model.component("comp1").physics("tcd").feature("pce1").selection().named("geom1_r1_dom");
    model.component("comp1").physics("tcd").feature("pce1").set("u", new String[]{"0", "v", "0"});
    model.component("comp1").physics("tcd").feature("pce1").set("sigma_mat", "userdef");
    model.component("comp1").physics("tcd").feature("pce1")
         .set("sigma", new String[]{"sigma_e", "0", "0", "0", "sigma_e", "0", "0", "0", "sigma_e"});
    model.component("comp1").physics("tcd").feature("pce1")
         .set("D_cSO4_neg", new String[]{"DSO4", "0", "0", "0", "DSO4", "0", "0", "0", "DSO4"});
    model.component("comp1").physics("tcd").feature("pce1")
         .set("D_cHSO4_neg", new String[]{"DHSO4", "0", "0", "0", "DHSO4", "0", "0", "0", "DHSO4"});
    model.component("comp1").physics("tcd").feature("pce1")
         .set("D_cH_neg", new String[]{"DH", "0", "0", "0", "DH", "0", "0", "0", "DH"});
    model.component("comp1").physics("tcd").feature("pce1")
         .set("D_cV2", new String[]{"DV2", "0", "0", "0", "DV2", "0", "0", "0", "DV2"});
    model.component("comp1").physics("tcd").feature("pce1")
         .set("D_cV3", new String[]{"DV3", "0", "0", "0", "DV3", "0", "0", "0", "DV3"});
    model.component("comp1").physics("tcd").feature("pce1").set("epsl", "epsilon");
    model.component("comp1").physics("tcd").feature("pce1").set("ElectricCorrModel", "NoCorr");
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").setIndex("Vi0", 1, 3);
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").setIndex("Vi0", -1, 4);
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").set("Eeq_ref", "E0_neg");
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").set("i0_ref", "i0ref_neg");
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").set("alphaa", "alpha_neg");
    model.component("comp1").physics("tcd").feature("pce1").feature("per1").set("Av", "a");
    model.component("comp1").physics("tcd").create("reac1", "Reactions", 2);
    model.component("comp1").physics("tcd").feature("reac1").selection().named("geom1_r1_dom");
    model.component("comp1").physics("tcd").feature("reac1").setIndex("R_cSO4_neg", "-rd", 0);
    model.component("comp1").physics("tcd").feature("reac1").setIndex("R_cHSO4_neg", "rd", 0);
    model.component("comp1").physics("tcd").feature("reac1").setIndex("R_cH_neg", "-rd", 0);
    model.component("comp1").physics("tcd").create("egnd1", "ElectricGround", 1);
    model.component("comp1").physics("tcd").feature("egnd1").selection().set(1);
    model.component("comp1").physics("tcd").create("in1", "Inflow", 1);
    model.component("comp1").physics("tcd").feature("in1").selection().set(2);
    model.component("comp1").physics("tcd").feature("in1").setIndex("c0", "cHSO4_0_neg", 1);
    model.component("comp1").physics("tcd").feature("in1").setIndex("c0", "cH_0_neg", 2);
    model.component("comp1").physics("tcd").feature("in1").setIndex("c0", "cV2_0", 3);
    model.component("comp1").physics("tcd").feature("in1").setIndex("c0", "cV3_0", 4);
    model.component("comp1").physics("tcd").feature("in1").set("BoundaryConditionType", "FluxDanckwerts");
    model.component("comp1").physics("tcd").create("out1", "Outflow", 1);
    model.component("comp1").physics("tcd").feature("out1").selection().set(3);
    model.component("comp1").physics("tcd").create("iemb1", "IonExchangeMembraneBoundary", 1);
    model.component("comp1").physics("tcd").feature("iemb1").selection().set(4);
    model.component("comp1").physics("tcd").feature("iemb1").set("phim_src", "root.comp1.phil_mem");
    model.component("comp1").physics("tcd").feature("iemb1").set("IEMTransportModel", "MultipleIons");
    model.component("comp1").physics("tcd").feature("iemb1").setIndex("cmem", "cSO4_mem", 0);
    model.component("comp1").physics("tcd").feature("iemb1").setIndex("species", true, 1);
    model.component("comp1").physics("tcd").feature("iemb1").setIndex("cmem", "cHSO4_mem", 1);
    model.component("comp1").physics("tcd").feature("iemb1").setIndex("species", true, 2);
    model.component("comp1").physics("tcd").feature("iemb1").setIndex("cmem", "cH_mem", 2);
    model.component("comp1").physics("tcd").feature("iemb1").setIndex("species", true, 3);
    model.component("comp1").physics("tcd").feature("iemb1").setIndex("cmem", "cV2_mem", 3);
    model.component("comp1").physics("tcd").feature("iemb1").setIndex("species", true, 4);
    model.component("comp1").physics("tcd").feature("iemb1").setIndex("cmem", "cV3_mem", 4);
    model.component("comp1").physics("tcd").create("ecd1", "ElectrodeNormalCurrentDensity", 1);
    model.component("comp1").physics("tcd").feature("ecd1").selection().set(4);
    model.component("comp1").physics("tcd").feature("ecd1").set("nis", "-tcd2.itot");
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cHSO4_0_neg", 1);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cH_0_neg", 2);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cV2_0", 3);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cV3_0", 4);
    model.component("comp1").physics("tcd2")
         .label("\u4e09\u6b21\u7535\u6d41\u5206\u5e03\uff0c\u80fd\u65af\u7279-\u666e\u6717\u514b\uff08\u79bb\u5b50\u4ea4\u6362\u819c\uff09");
    model.component("comp1").physics("tcd2").selection().named("geom1_r2_dom");
    model.component("comp1").physics("tcd2").prop("dz").set("dz", "wCell");
    model.component("comp1").physics("tcd2").feature("sp1").setIndex("z", -2, 0);
    model.component("comp1").physics("tcd2").feature("sp1").setIndex("z", -1, 1);
    model.component("comp1").physics("tcd2").feature("sp1").setIndex("z", 1, 2);
    model.component("comp1").physics("tcd2").feature("sp1").setIndex("z", 2, 3);
    model.component("comp1").physics("tcd2").feature("sp1").setIndex("z", 3, 4);
    model.component("comp1").physics("tcd2").feature("sp1").setIndex("z", 2, 5);
    model.component("comp1").physics("tcd2").feature("sp1").setIndex("z", 1, 6);
    model.component("comp1").physics("tcd2").create("iem1", "IonExchangeMembrane", 2);
    model.component("comp1").physics("tcd2").feature("iem1").selection().named("geom1_r2_dom");
    model.component("comp1").physics("tcd2").feature("iem1").set("rhofix", "c_fix*F_const");
    model.component("comp1").physics("tcd2").feature("iem1")
         .set("D_cSO4_mem", new String[]{"DSO4", "0", "0", "0", "DSO4", "0", "0", "0", "DSO4"});
    model.component("comp1").physics("tcd2").feature("iem1")
         .set("D_cHSO4_mem", new String[]{"DHSO4", "0", "0", "0", "DHSO4", "0", "0", "0", "DHSO4"});
    model.component("comp1").physics("tcd2").feature("iem1")
         .set("D_cH_mem", new String[]{"DH", "0", "0", "0", "DH", "0", "0", "0", "DH"});
    model.component("comp1").physics("tcd2").feature("iem1")
         .set("D_cV2_mem", new String[]{"DV2", "0", "0", "0", "DV2", "0", "0", "0", "DV2"});
    model.component("comp1").physics("tcd2").feature("iem1")
         .set("D_cV3_mem", new String[]{"DV3", "0", "0", "0", "DV3", "0", "0", "0", "DV3"});
    model.component("comp1").physics("tcd2").feature("iem1")
         .set("D_cV4_mem", new String[]{"DV4", "0", "0", "0", "DV4", "0", "0", "0", "DV4"});
    model.component("comp1").physics("tcd2").feature("iem1")
         .set("D_cV5_mem", new String[]{"DV5", "0", "0", "0", "DV5", "0", "0", "0", "DV5"});
    model.component("comp1").physics("tcd2").feature("iem1").set("epsl", 0.1);
    model.component("comp1").physics("tcd2").create("reac1", "Reactions", 2);
    model.component("comp1").physics("tcd2").feature("reac1").selection().named("geom1_r2_dom");
    model.component("comp1").physics("tcd2").feature("reac1").setIndex("R_cSO4_mem", "-rd", 0);
    model.component("comp1").physics("tcd2").feature("reac1").setIndex("R_cHSO4_mem", "rd", 0);
    model.component("comp1").physics("tcd2").feature("reac1").setIndex("R_cH_mem", "-rd", 0);
    model.component("comp1").physics("tcd2").create("es1", "ElectrodeSurface", 1);
    model.component("comp1").physics("tcd2").feature("es1").selection().set(4);
    model.component("comp1").physics("tcd2").feature("es1").feature("er1").setIndex("Vi0", -2, 2);
    model.component("comp1").physics("tcd2").feature("es1").feature("er1").setIndex("Vi0", 1, 4);
    model.component("comp1").physics("tcd2").feature("es1").feature("er1").setIndex("Vi0", -1, 5);
    model.component("comp1").physics("tcd2").feature("es1").feature("er1").set("Eeq_mat", "userdef");
    model.component("comp1").physics("tcd2").feature("es1").feature("er1")
         .set("ElectrodeKinetics", "FastIrreversibleElectrodeReaction");
    model.component("comp1").physics("tcd2").feature("es1").feature("er1")
         .set("RateLimitingSpeciesConcentration", 6);
    model.component("comp1").physics("tcd2").feature("es1").create("er2", "ElectrodeReaction", 1);
    model.component("comp1").physics("tcd2").feature("es1").feature("er2").setIndex("Vi0", -2, 2);
    model.component("comp1").physics("tcd2").feature("es1").feature("er2").setIndex("Vi0", 1, 5);
    model.component("comp1").physics("tcd2").feature("es1").feature("er2").setIndex("Vi0", -1, 6);
    model.component("comp1").physics("tcd2").feature("es1").feature("er2").set("Eeq_mat", "userdef");
    model.component("comp1").physics("tcd2").feature("es1").feature("er2")
         .set("ElectrodeKinetics", "FastIrreversibleElectrodeReaction");
    model.component("comp1").physics("tcd2").feature("es1").feature("er2")
         .set("RateLimitingSpeciesConcentration", 7);
    model.component("comp1").physics("tcd2").create("es2", "ElectrodeSurface", 1);
    model.component("comp1").physics("tcd2").feature("es2").selection().set(7);
    model.component("comp1").physics("tcd2").feature("es2").feature("er1").set("Eeq_mat", "userdef");
    model.component("comp1").physics("tcd2").feature("es2").feature("er1")
         .set("ElectrodeKinetics", "FastIrreversibleElectrodeReaction");
    model.component("comp1").physics("tcd2").feature("es2").feature("er1")
         .set("RateLimitingSpeciesConcentration", 4);
    model.component("comp1").physics("tcd2").feature("es2").feature("er1").setIndex("Vi0", -2, 2);
    model.component("comp1").physics("tcd2").feature("es2").feature("er1").setIndex("Vi0", 1, 4);
    model.component("comp1").physics("tcd2").feature("es2").feature("er1").setIndex("Vi0", -1, 5);
    model.component("comp1").physics("tcd2").feature("es2").create("er2", "ElectrodeReaction", 1);
    model.component("comp1").physics("tcd2").feature("es2").feature("er2").set("Eeq_mat", "userdef");
    model.component("comp1").physics("tcd2").feature("es2").feature("er2")
         .set("ElectrodeKinetics", "FastIrreversibleElectrodeReaction");
    model.component("comp1").physics("tcd2").feature("es2").feature("er2")
         .set("RateLimitingSpeciesConcentration", 5);
    model.component("comp1").physics("tcd2").feature("es2").feature("er2").setIndex("Vi0", 1, 3);
    model.component("comp1").physics("tcd2").feature("es2").feature("er2").setIndex("Vi0", -1, 4);
    model.component("comp1").physics("tcd2").feature("init1").setIndex("initc", "(cHSO4_0_pos+cHSO4_0_neg)/2", 1);
    model.component("comp1").physics("tcd2").feature("init1").setIndex("initc", "(cH_0_pos+cH_0_neg)/2", 2);
    model.component("comp1").physics("tcd2").feature("init1").setIndex("initc", "cV2_0/2", 3);
    model.component("comp1").physics("tcd2").feature("init1").setIndex("initc", "cV3_0/2", 4);
    model.component("comp1").physics("tcd2").feature("init1").setIndex("initc", "cV4_0/2", 5);
    model.component("comp1").physics("tcd2").feature("init1").setIndex("initc", "cV5_0/2", 6);
    model.component("comp1").physics("tcd2").feature("init1").set("initphil", "-E0_neg");
    model.component("comp1").physics("tcd3")
         .label("\u4e09\u6b21\u7535\u6d41\u5206\u5e03\uff0c\u80fd\u65af\u7279-\u666e\u6717\u514b\uff08\u6b63\u6781\uff09");
    model.component("comp1").physics("tcd3").selection().named("geom1_r3_dom");
    model.component("comp1").physics("tcd3").prop("dz").set("dz", "wCell");
    model.component("comp1").physics("tcd3").feature("sp1").setIndex("z", -2, 0);
    model.component("comp1").physics("tcd3").feature("sp1").setIndex("z", -1, 1);
    model.component("comp1").physics("tcd3").feature("sp1").setIndex("z", 1, 2);
    model.component("comp1").physics("tcd3").feature("sp1").setIndex("z", 2, 3);
    model.component("comp1").physics("tcd3").feature("sp1").setIndex("z", 1, 4);
    model.component("comp1").physics("tcd3").create("pce1", "PorousElectrode", 2);
    model.component("comp1").physics("tcd3").feature("pce1").selection().named("geom1_r3_dom");
    model.component("comp1").physics("tcd3").feature("pce1").set("u", new String[]{"0", "v", "0"});
    model.component("comp1").physics("tcd3").feature("pce1").set("sigma_mat", "userdef");
    model.component("comp1").physics("tcd3").feature("pce1")
         .set("sigma", new String[]{"sigma_e", "0", "0", "0", "sigma_e", "0", "0", "0", "sigma_e"});
    model.component("comp1").physics("tcd3").feature("pce1")
         .set("D_cSO4_pos", new String[]{"DSO4", "0", "0", "0", "DSO4", "0", "0", "0", "DSO4"});
    model.component("comp1").physics("tcd3").feature("pce1")
         .set("D_cHSO4_pos", new String[]{"DHSO4", "0", "0", "0", "DHSO4", "0", "0", "0", "DHSO4"});
    model.component("comp1").physics("tcd3").feature("pce1")
         .set("D_cH_pos", new String[]{"DH", "0", "0", "0", "DH", "0", "0", "0", "DH"});
    model.component("comp1").physics("tcd3").feature("pce1")
         .set("D_cV4", new String[]{"DV4", "0", "0", "0", "DV4", "0", "0", "0", "DV4"});
    model.component("comp1").physics("tcd3").feature("pce1")
         .set("D_cV5", new String[]{"DV5", "0", "0", "0", "DV5", "0", "0", "0", "DV5"});
    model.component("comp1").physics("tcd3").feature("pce1").set("epsl", "epsilon");
    model.component("comp1").physics("tcd3").feature("pce1").set("ElectricCorrModel", "NoCorr");
    model.component("comp1").physics("tcd3").feature("pce1").feature("per1").setIndex("Vi0", -2, 2);
    model.component("comp1").physics("tcd3").feature("pce1").feature("per1").setIndex("Vi0", 1, 3);
    model.component("comp1").physics("tcd3").feature("pce1").feature("per1").setIndex("Vi0", -1, 4);
    model.component("comp1").physics("tcd3").feature("pce1").feature("per1").set("Eeq_ref", "E0_pos");
    model.component("comp1").physics("tcd3").feature("pce1").feature("per1").set("i0_ref", "i0ref_pos");
    model.component("comp1").physics("tcd3").feature("pce1").feature("per1").set("alphaa", "alpha_pos");
    model.component("comp1").physics("tcd3").feature("pce1").feature("per1").set("Av", "a");
    model.component("comp1").physics("tcd3").create("reac1", "Reactions", 2);
    model.component("comp1").physics("tcd3").feature("reac1").selection().named("geom1_r3_dom");
    model.component("comp1").physics("tcd3").feature("reac1").setIndex("R_cSO4_pos", "-rd", 0);
    model.component("comp1").physics("tcd3").feature("reac1").setIndex("R_cHSO4_pos", "rd", 0);
    model.component("comp1").physics("tcd3").feature("reac1").setIndex("R_cH_pos", "-rd", 0);
    model.component("comp1").physics("tcd3").create("ec1", "ElectrodeCurrent", 1);
    model.component("comp1").physics("tcd3").feature("ec1").set("ElectronicCurrentType", "AverageCurrentDensity");
    model.component("comp1").physics("tcd3").feature("ec1").selection().set(10);
    model.component("comp1").physics("tcd3").feature("ec1").set("Ias", "i_avg");
    model.component("comp1").physics("tcd3").create("in1", "Inflow", 1);
    model.component("comp1").physics("tcd3").feature("in1").selection().set(8);
    model.component("comp1").physics("tcd3").feature("in1").setIndex("c0", "cHSO4_0_pos", 1);
    model.component("comp1").physics("tcd3").feature("in1").setIndex("c0", "cH_0_pos", 2);
    model.component("comp1").physics("tcd3").feature("in1").setIndex("c0", "cV4_0", 3);
    model.component("comp1").physics("tcd3").feature("in1").setIndex("c0", "cV5_0", 4);
    model.component("comp1").physics("tcd3").feature("in1").set("BoundaryConditionType", "FluxDanckwerts");
    model.component("comp1").physics("tcd3").create("out1", "Outflow", 1);
    model.component("comp1").physics("tcd3").feature("out1").selection().set(9);
    model.component("comp1").physics("tcd3").create("iemb1", "IonExchangeMembraneBoundary", 1);
    model.component("comp1").physics("tcd3").feature("iemb1").selection().set(7);
    model.component("comp1").physics("tcd3").feature("iemb1").set("phim_src", "root.comp1.phil_mem");
    model.component("comp1").physics("tcd3").feature("iemb1").set("IEMTransportModel", "MultipleIons");
    model.component("comp1").physics("tcd3").feature("iemb1").setIndex("cmem", "cSO4_mem", 0);
    model.component("comp1").physics("tcd3").feature("iemb1").setIndex("species", true, 1);
    model.component("comp1").physics("tcd3").feature("iemb1").setIndex("cmem", "cHSO4_mem", 1);
    model.component("comp1").physics("tcd3").feature("iemb1").setIndex("species", true, 2);
    model.component("comp1").physics("tcd3").feature("iemb1").setIndex("cmem", "cH_mem", 2);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("tcd3").feature("iemb1").setIndex("species", true, 3);
    model.component("comp1").physics("tcd3").feature("iemb1").setIndex("cmem", "cV4_mem", 3);
    model.component("comp1").physics("tcd3").feature("iemb1").setIndex("species", true, 4);
    model.component("comp1").physics("tcd3").feature("iemb1").setIndex("cmem", "cV5_mem", 4);
    model.component("comp1").physics("tcd3").create("ecd1", "ElectrodeNormalCurrentDensity", 1);
    model.component("comp1").physics("tcd3").feature("ecd1").selection().set(7);
    model.component("comp1").physics("tcd3").feature("ecd1").set("nis", "-tcd2.itot");
    model.component("comp1").physics("tcd3").feature("init1").setIndex("initc", "cHSO4_0_pos", 1);
    model.component("comp1").physics("tcd3").feature("init1").setIndex("initc", "cH_0_pos", 2);
    model.component("comp1").physics("tcd3").feature("init1").setIndex("initc", "cV4_0", 3);
    model.component("comp1").physics("tcd3").feature("init1").setIndex("initc", "cV5_0", 4);

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(5, 6);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 40);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature().duplicate("dis3", "dis2");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(8, 9);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("reverse", true);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").selection().set(1, 4, 7, 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("elemcount", 40);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("elemratio", 20);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(2, 8);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "H_cell", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "H_cell", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "c_fix", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "0 -cHm", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "cV3");
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("expr", "cV4");
    model.result("pg1").feature("surf2").set("inheritplot", "surf1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").label("V3/V4 \u7269\u8d28\u6d53\u5ea6");
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "cV2");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("expr", "cV5");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").label("V2/V5 \u7269\u8d28\u6d53\u5ea6");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").label("\u4e8c\u7ef4\u7535\u89e3\u8d28\u7535\u4f4d");
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", "H_cell/2", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", "L_e*2+L_m", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "H_cell/2", 1, 1);
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").set("data", "cln1");
    model.result("pg4").setIndex("looplevelinput", "last", 0);
    model.result("pg4").set("titletype", "none");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").set("expr", "phil");
    model.result("pg4").feature("lngr1")
         .set("descr", "\u7535\u89e3\u8d28\u7535\u4f4d\uff08\u7528\u4e8e\u540e\u5904\u7406\uff09");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").label("\u7535\u89e3\u8d28\u7535\u4f4d");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("titletype", "none");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").set("data", "cln1");
    model.result("pg5").feature("lngr1").setIndex("looplevelinput", "last", 0);
    model.result("pg5").feature("lngr1").set("expr", "tcd.ivtot");
    model.result("pg5").feature("lngr1").set("descr", "\u7535\u6781\u53cd\u5e94\u6e90");
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("lngr2", "lngr1");
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").set("expr", "tcd3.ivtot");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u6781\u53cd\u5e94\u7535\u6d41\u5bc6\u5ea6");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "log(abs(rd))");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").label("\u6eb6\u89e3\u901f\u7387");
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").set("data", "cln1");
    model.result("pg7").setIndex("looplevelinput", "last", 0);
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u7269\u8d28\u6d3b\u6027 (-)");
    model.result("pg7").set("legendpos", "middleright");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").set("expr", "aH");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("legendmethod", "manual");
    model.result("pg7").feature("lngr1").setIndex("legends", "H", 0);
    model.result("pg7").feature().duplicate("lngr2", "lngr1");
    model.result("pg7").run();
    model.result("pg7").feature("lngr2").set("expr", "aHSO4");
    model.result("pg7").feature("lngr2").setIndex("legends", "HSO4", 0);
    model.result("pg7").feature().duplicate("lngr3", "lngr2");
    model.result("pg7").run();
    model.result("pg7").feature("lngr3").set("expr", "aSO4");
    model.result("pg7").feature("lngr3").setIndex("legends", "SO4", 0);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").label("\u786b\u9178\u7269\u8d28");
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").set("data", "cln1");
    model.result("pg8").setIndex("looplevelinput", "last", 0);
    model.result("pg8").set("titletype", "none");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "\u7269\u8d28\u6d3b\u6027 (-)");
    model.result("pg8").set("legendpos", "middleright");
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr1").set("linewidth", "preference");
    model.result("pg8").feature("lngr1").set("expr", "aV2");
    model.result("pg8").feature("lngr1").set("legend", true);
    model.result("pg8").feature("lngr1").set("legendmethod", "manual");
    model.result("pg8").feature("lngr1").setIndex("legends", "V2", 0);
    model.result("pg8").feature().duplicate("lngr2", "lngr1");
    model.result("pg8").run();
    model.result("pg8").feature("lngr2").set("expr", "aV3");
    model.result("pg8").feature("lngr2").setIndex("legends", "V3", 0);
    model.result("pg8").feature().duplicate("lngr3", "lngr2");
    model.result("pg8").run();
    model.result("pg8").feature("lngr3").set("expr", "aV4");
    model.result("pg8").feature("lngr3").setIndex("legends", "V4", 0);
    model.result("pg8").feature().duplicate("lngr4", "lngr3");
    model.result("pg8").run();
    model.result("pg8").feature("lngr4").set("expr", "aV5");
    model.result("pg8").feature("lngr4").setIndex("legends", "V5", 0);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").label("V2/V3/V4/V5");
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").set("data", "cln1");
    model.result("pg9").setIndex("looplevelinput", "last", 0);
    model.result("pg9").set("titletype", "none");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u901a\u91cf (A/m<sup>2</sup>)");
    model.result("pg9").create("lngr1", "LineGraph");
    model.result("pg9").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg9").feature("lngr1").set("linewidth", "preference");
    model.result("pg9").feature("lngr1").set("expr", "Hx*F_const");
    model.result("pg9").feature("lngr1").set("legend", true);
    model.result("pg9").feature("lngr1").set("legendmethod", "manual");
    model.result("pg9").feature("lngr1").setIndex("legends", "\u901a\u91cf\uff0cH", 0);
    model.result("pg9").feature().duplicate("lngr2", "lngr1");
    model.result("pg9").run();
    model.result("pg9").feature("lngr2").set("expr", "-HSO4x*F_const");
    model.result("pg9").feature("lngr2").setIndex("legends", "\u901a\u91cf\uff0cHSO4", 0);
    model.result("pg9").feature().duplicate("lngr3", "lngr2");
    model.result("pg9").run();
    model.result("pg9").feature("lngr3").set("expr", "-2*F_const*SO4x");
    model.result("pg9").feature("lngr3").setIndex("legends", "\u901a\u91cf\uff0cSO4", 0);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").label("\u901a\u91cf");

    model.title("\u9492\u6c27\u5316\u8fd8\u539f\u6db2\u6d41\u7535\u6c60");

    model
         .description("\u8fd9\u4e2a\u9492\u6db2\u6d41\u7535\u6c60\u4e8c\u7ef4\u793a\u4f8b\u6f14\u793a\u5982\u4f55\u5c06\u79bb\u5b50\u4ea4\u6362\u819c\u7684\u4e09\u6b21\u7535\u6d41\u5206\u5e03\u6a21\u578b\u4e0e\u6db2\u6d41\u7535\u6c60\u7684\u4e24\u4e2a\u4e0d\u540c\u81ea\u7531\u7535\u89e3\u8d28\u5ba4\u7684\u4e09\u6b21\u7535\u6d41\u5206\u5e03\u6a21\u578b\u8fdb\u884c\u8026\u5408\u3002\n\n\u79bb\u5b50\u4ea4\u6362\u819c \u8fb9\u754c\u8282\u70b9\u6307\u5b9a\u4e00\u4e2a\u8fb9\u754c\u6761\u4ef6\uff0c\u5176\u4e2d\u79bb\u5b50\u901a\u91cf\u662f\u8fde\u7eed\u7684\uff0c\u4f46\u7535\u89e3\u8d28\u7535\u4f4d\u4e0d\u8fde\u7eed\uff0c\u5e76\u7528\u5510\u5357\u5e73\u8861\u8fdb\u884c\u63cf\u8ff0\u3002\n\n\u8be5\u8fb9\u754c\u6761\u4ef6\u901a\u5e38\u7528\u4e8e\u540c\u65f6\u5305\u542b\u81ea\u7531\u7535\u89e3\u8d28\u548c\u79bb\u5b50\u4ea4\u6362\u819c\u7684\u7535\u5316\u5b66\u6c60\uff0c\u4f8b\u5982\u7528\u4e8e\u900f\u6790\u95ee\u9898\u3002");

    model.label("v_flow_battery.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
