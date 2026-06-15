/*
 * monolith_kinetics.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:30 by COMSOL 6.3.0.290. */
public class monolith_kinetics {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("re", "ReactionEng");

    model.study().create("std1");
    model.study("std1").create("spf", "StationaryPlugflow");
    model.study("std1").feature("spf").set("solnum", "auto");
    model.study("std1").feature("spf").set("notsolnum", "auto");
    model.study("std1").feature("spf").set("outputmap", new String[]{});
    model.study("std1").feature("spf").setSolveFor("/physics/re", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param()
         .set("m1", "0.086[kg/s]", "\u5e9f\u6c14\u8d28\u91cf\u6d41\u7387\uff0c\u5de5\u51b5 1\uff08\u4f4e\u8d1f\u8f7d\uff09");
    model.param()
         .set("T1", "523[K]", "SCR \u5165\u53e3\u6e29\u5ea6\uff0c\u5de5\u51b5 1\uff08\u4f4e\u8d1f\u8f7d\uff09");
    model.param()
         .set("y1", "300e-6", "\u5165\u53e3 NO \u6469\u5c14\u5206\u6570\uff0c\u5de5\u51b5 1\uff08\u4f4e\u8d1f\u8f7d\uff09");
    model.param()
         .set("m2", "0.11[kg/s]", "\u5e9f\u6c14\u8d28\u91cf\u6d41\u7387\uff0c\u5de5\u51b5 2\uff08\u4e2d\u7b49\u8d1f\u8f7d\uff09");
    model.param()
         .set("T2", "623[K]", "SCR \u5165\u53e3\u6e29\u5ea6\uff0c\u5de5\u51b5 2\uff08\u4e2d\u7b49\u8d1f\u8f7d\uff09");
    model.param()
         .set("y2", "800e-6", "\u5165\u53e3 NO \u6469\u5c14\u5206\u6570\uff0c\u5de5\u51b5 2\uff08\u4e2d\u7b49\u8d1f\u8f7d\uff09");
    model.param()
         .set("m3", "0.16[kg/s]", "\u5e9f\u6c14\u8d28\u91cf\u6d41\u7387\uff0c\u5de5\u51b5 3\uff08\u9ad8\u8d1f\u8f7d\uff09");
    model.param()
         .set("T3", "703[K]", "SCR \u5165\u53e3\u6e29\u5ea6\uff0c\u5de5\u51b5 3\uff08\u9ad8\u8d1f\u8f7d\uff09");
    model.param()
         .set("y3", "1300e-6", "\u5165\u53e3 NO \u6469\u5c14\u5206\u6570\uff0c\u5de5\u51b5 3\uff08\u9ad8\u8d1f\u8f7d\uff09");
    model.param().create("par2");
    model.param("par2").set("m", "m2");
    model.param("par2").descr("m", "Mass flow rate (case 2 is default)");
    model.param("par2").set("y", "y2");
    model.param("par2").descr("y", "NO molar fraction (case 2 is default)");
    model.param("par2").set("T", "T2");
    model.param("par2").descr("T", "Inlet temperature (case 2 is default)");
    model.param("par2").paramCase().create("case1");
    model.param("par2").paramCase("case1").set("m", "m1");
    model.param("par2").paramCase("case1").set("y", "y1");
    model.param("par2").paramCase("case1").set("T", "T1");
    model.param("par2").paramCase().create("case2");
    model.param("par2").paramCase().create("case3");
    model.param("par2").paramCase("case3").set("m", "m3");
    model.param("par2").paramCase("case3").set("y", "y3");
    model.param("par2").paramCase("case3").set("T", "T3");
    model.param().create("par3");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("T_gas_in", "T", "\u5165\u53e3\u5e9f\u6c14\u6e29\u5ea6");
    model.param("par3").set("d_cat", "0.32[m]", "\u6574\u4f53\u53cd\u5e94\u5668\u76f4\u5f84");
    model.param("par3").set("l_SCR", "0.4[m]", "SCR \u6574\u4f53\u53cd\u5e94\u5668\u957f\u5ea6");
    model.param("par3").set("l_ASC", "0.06[m]", "ASC \u6574\u4f53\u53cd\u5e94\u5668\u957f\u5ea6");
    model.param("par3").set("V_SCR", "pi*(d_cat/2)^2*l_SCR", "SCR \u6574\u4f53\u53cd\u5e94\u5668\u4f53\u79ef");
    model.param("par3").set("V_ASC", "pi*(d_cat/2)^2*l_ASC", "ASC \u6574\u4f53\u53cd\u5e94\u5668\u4f53\u79ef");
    model.param().create("par4");

//    To import content from file, use:
//    model.param("par4").loadFile("FILENAME");
    model.param("par4").set("m_exhaust", "m", "T_gas_in \u7684\u5f15\u64ce\u51fa\u53e3\u5e9f\u6c14\u6d41\u901f");
    model.param("par4").set("yNO", "y", "\u53cd\u5e94\u5668\u5165\u53e3 NO \u6469\u5c14\u5206\u6570");
    model.param("par4")
         .set("NO2ratio", "0.1", "\u53cd\u5e94\u5668\u5165\u53e3 NOx \u4e2d NO2 \u4e0e NO \u7684\u6bd4\u4f8b");
    model.param("par4").set("yNO2", "yNO*NO2ratio", "\u53cd\u5e94\u5668\u5165\u53e3 NO2 \u6469\u5c14\u5206\u6570");
    model.param("par4").set("yO2", "0.07", "\u53cd\u5e94\u5668\u5165\u53e3 O2 \u6469\u5c14\u5206\u6570");
    model.param("par4").set("yH2O", "0.1", "\u53cd\u5e94\u5668\u5165\u53e3 H2O \u6469\u5c14\u5206\u6570");
    model.param("par4")
         .set("yN2", "1-yNO-yO2-yH2O-yNO2", "\u53cd\u5e94\u5668\u5165\u53e3 N2 \u6469\u5c14\u5206\u6570");
    model.param("par4")
         .set("n_exhaust", "m_exhaust/(MN2*yN2+MO2*yO2+MH2O*yH2O+MNO*yNO+MNO2*yNO2)", "\u8fdb\u5165\u53cd\u5e94\u5668\u7684\u5e9f\u6c14\u6469\u5c14\u6d41\u7387");
    model.param("par4")
         .set("v_exhaust", "n_exhaust*R_const*T_gas_in/1[atm]", "\u8fdb\u5165\u53cd\u5e94\u5668\u7684\u5e9f\u6c14\u4f53\u79ef\u6d41\u7387");
    model.param("par4")
         .set("FH2O_in", "yH2O*n_exhaust", "\u8fdb\u5165\u53cd\u5e94\u5668\u7684 H2O \u6469\u5c14\u6d41\u7387");
    model.param("par4")
         .set("FN2_in", "yN2*n_exhaust", "\u8fdb\u5165\u53cd\u5e94\u5668\u7684 N2 \u6469\u5c14\u6d41\u7387");
    model.param("par4")
         .set("FO2_in", "yO2*n_exhaust", "\u8fdb\u5165\u53cd\u5e94\u5668\u7684 O2 \u6469\u5c14\u6d41\u7387");
    model.param("par4")
         .set("FNO_in", "yNO*n_exhaust", "\u8fdb\u5165\u53cd\u5e94\u5668\u7684 NO \u6469\u5c14\u6d41\u7387");
    model.param("par4")
         .set("FNO2_in", "NO2ratio*FNO_in", "\u8fdb\u5165\u53cd\u5e94\u5668\u7684 NO2 \u6469\u5c14\u6d41\u7387");
    model.param("par4")
         .set("cH2O_in", "FH2O_in/v_exhaust", "\u8fdb\u5165\u53cd\u5e94\u5668\u7684\u6c14\u4f53\u4e2d H2O \u6d53\u5ea6");
    model.param("par4")
         .set("cN2_in", "FN2_in/v_exhaust", "\u8fdb\u5165\u53cd\u5e94\u5668\u7684\u6c14\u4f53\u4e2d N2 \u6d53\u5ea6");
    model.param("par4")
         .set("cO2_in", "FO2_in/v_exhaust", "\u8fdb\u5165\u53cd\u5e94\u5668\u7684\u6c14\u4f53\u4e2d O2 \u6d53\u5ea6");
    model.param("par4")
         .set("cNO_in", "FNO_in/v_exhaust", "\u8fdb\u5165\u53cd\u5e94\u5668\u7684\u6c14\u4f53\u4e2d NO \u6d53\u5ea6");
    model.param("par4")
         .set("cNO2_in", "FNO2_in/v_exhaust", "\u8fdb\u5165\u53cd\u5e94\u5668\u7684\u6c14\u4f53\u4e2d NO2 \u6d53\u5ea6");
    model.param("par4")
         .set("cNOx_in", "cNO_in+cNO2_in", "\u8fdb\u5165\u53cd\u5e94\u5668\u7684\u6c14\u4f53\u4e2d  NOx \u6d53\u5ea6");
    model.param("par4")
         .set("ANR", "1.3", "\u8fdb\u5165\u53cd\u5e94\u5668\u7684\u6c14\u4f53\u4e2d\u7684\u6c28\u6c14\u4e0e NOx \u6bd4\u4f8b");
    model.param("par4")
         .set("FNH3_in", "ANR*(FNO_in+FNO2_in)", "\u8fdb\u5165\u53cd\u5e94\u5668\u7684 NH3 \u6469\u5c14\u6d41\u7387");
    model.param("par4")
         .set("cNH3_in", "FNH3_in/v_tot_in", "\u8fdb\u5165\u53cd\u5e94\u5668\u7684\u6c14\u4f53\u4e2d NH3 \u6d53\u5ea6");
    model.param("par4")
         .set("mNH3_in", "FNH3_in*MNH3", "\u8fdb\u5165\u53cd\u5e94\u5668\u7684 NH3 \u8d28\u91cf\u6d41\u7387");
    model.param("par4")
         .set("m_tot_in", "m_exhaust + mNH3_in", "\u8fdb\u5165\u53cd\u5e94\u5668\u7684\u6c14\u4f53\u603b\u8d28\u91cf\u6d41\u7387");
    model.param("par4")
         .set("v_NH3_in", "FNH3_in*R_const*T_gas_in/1[atm]", "\u8fdb\u5165\u53cd\u5e94\u5668\u7684 NH3 \u4f53\u79ef\u6d41\u7387");
    model.param("par4")
         .set("v_tot_in", "v_exhaust + v_NH3_in", "\u8fdb\u5165\u53cd\u5e94\u5668\u7684\u6c14\u4f53\u603b\u4f53\u79ef\u6d41\u7387");
    model.param("par4")
         .set("mNOX_in", "(cNO_in*MNO+cNO2_in*MNO2)*v_tot_in", "\u8fdb\u5165\u53cd\u5e94\u5668\u7684 NOx \u8d28\u91cf\u6d41\u7387");
    model.param("par4").set("MN2", "0.028013[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cN2");
    model.param("par4").set("MH2O", "0.018015[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cH2O");
    model.param("par4").set("MO2", "0.031999[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cO2");
    model.param("par4").set("MNO", "0.030006[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cNO");
    model.param("par4").set("MNO2", "0.046006[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cNO2");
    model.param("par4").set("MNH3", "0.017031[kg/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cNH3");
    model.param().create("par5");

//    To import content from file, use:
//    model.param("par5").loadFile("FILENAME");
    model.param("par5")
         .set("E1_SCR", "65000[J/mol]", "\u8868\u89c2\u6d3b\u5316\u80fd\uff0c\u53cd\u5e94 (1) \u6807\u51c6 SCR");
    model.param("par5")
         .set("A1_SCR", "3.0556E8[m^6/s/mol^2]", "\u8868\u89c2\u9891\u7387\u56e0\u5b50\uff0c\u53cd\u5e94 (1) \u6807\u51c6 SCR");
    model.param("par5")
         .set("E2_SCR", "25830[J/mol]", "\u8868\u89c2\u6d3b\u5316\u80fd\uff0c\u53cd\u5e94 (2) \u5feb\u901f SCR");
    model.param("par5")
         .set("A2_SCR", "2.6111E7[m^6/s/mol^2]", "\u8868\u89c2\u9891\u7387\u56e0\u5b50\uff0c\u53cd\u5e94  (2) \u5feb\u901f SCR");
    model.param("par5")
         .set("E3_SCR", "69000[J/mol]", "\u8868\u89c2\u6d3b\u5316\u80fd\uff0c\u53cd\u5e94 (3) NO2 SCR");
    model.param("par5")
         .set("A3_SCR", "8.4722E8[m^3/(s*mol)]", "\u8868\u89c2\u9891\u7387\u56e0\u5b50\uff0c\u53cd\u5e94  (3) NO2 SCR");
    model.param("par5")
         .set("E4", "12400[J/mol]", "\u8868\u89c2\u6b63\u53cd\u5e94\u6d3b\u5316\u80fd\uff0c\u53cd\u5e94 (4) \u6c27\u5316\u53cd\u5e94 NO to NO2");
    model.param("par5")
         .set("A4", "188.89[m^6/s/mol^2]", "\u8868\u89c2\u6b63\u53cd\u5e94\u9891\u7387\u56e0\u5b50\uff0c\u53cd\u5e94 (4) \u6c27\u5316\u53cd\u5e94 NO to NO2");
    model.param("par5")
         .set("E5_SCR", "1.2317E5[J/mol]", "\u8868\u89c2\u6d3b\u5316\u80fd\uff0c\u53cd\u5e94 (5) \u6c27\u5316\u53cd\u5e94 NH3 to N2");
    model.param("par5")
         .set("A5_SCR", "1.4444E9[m^3/(s*mol)]", "\u8868\u89c2\u9891\u7387\u56e0\u5b50\uff0c\u53cd\u5e94  (5) \u6c27\u5316\u53cd\u5e94 NH3 to N2");
    model.param("par5")
         .set("E6_SCR", "1.75E5[J/mol]", "\u8868\u89c2\u6d3b\u5316\u80fd\uff0c\u53cd\u5e94 (6) \u4e0d\u671f\u671b\u7684 NO \u751f\u6210\u53cd\u5e94");
    model.param("par5")
         .set("A6_SCR", "8E19[m^3/(s*mol)]", "\u8868\u89c2\u9891\u7387\u56e0\u5b50\uff0c\u53cd\u5e94  (6) \u4e0d\u671f\u671b\u7684 NO \u751f\u6210\u53cd\u5e94");
    model.param("par5")
         .set("E1_ASC", "96138[J/mol]", "\u8868\u89c2\u6d3b\u5316\u80fd\uff0c\u53cd\u5e94 (1) \u671f\u671b\u7684 NH3 \u5728 ASC \u4e2d\u6c27\u5316");
    model.param("par5")
         .set("A1_ASC", "2.2222E9[m^3/(s*mol)]", "\u8868\u89c2\u9891\u7387\u56e0\u5b50\uff0c\u53cd\u5e94 (1) \u671f\u671b\u7684 NH3 \u5728 ASC \u4e2d\u6c27\u5316");
    model.param("par5")
         .set("E2_ASC", "1.125E5[J/mol]", "\u8868\u89c2\u6d3b\u5316\u80fd\uff0c\u53cd\u5e94 (2) \u4e0d\u671f\u671b\u7684 NH3 \u5728 ASC \u4e2d\u6c27\u5316");
    model.param("par5")
         .set("A2_ASC", "1.6E17[m^3/(s*mol)]", "\u8868\u89c2\u9891\u7387\u56e0\u5b50\uff0c\u53cd\u5e94 (2) \u4e0d\u671f\u671b\u7684 NH3 \u5728 ASC \u4e2d\u6c27\u5316");
    model.param("par5")
         .set("E4_ASC", "95657[J/mol]", "\u8868\u89c2\u6d3b\u5316\u80fd\uff0c\u53cd\u5e94 (4) \u4e0d\u671f\u671b\u7684 NO2 \u5728 ASC \u4e2d\u751f\u6210\u53cd\u5e94");
    model.param("par5")
         .set("A4_ASC", "6.667E8[m^3/(s*mol)]", "\u8868\u89c2\u9891\u7387\u56e0\u5b50\uff0c\u53cd\u5e94 (4) \u4e0d\u671f\u671b\u7684 NO2 \u5728 ASC \u4e2d\u751f\u6210\u53cd\u5e94");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("K_NH3", "7.6e-7[m^3/mol]*exp(-100[kJ/mol]/R_const*(1/523[K]-1/re.T))", "NH3 \u5438\u9644\u5e73\u8861\u5e38\u6570");
    model.component("comp1").variable("var1")
         .set("G", "re.T*(1+K1*re.c_NO+K2*re.c_H2O)^2*(1+K3*re.c_NH3)^2*(1+K4*re.c_O2)^2[1/(K)]", "\u5438\u9644\u7269\u8d28\u7684\u6291\u5236\u6548\u5e94");
    model.component("comp1").variable("var1")
         .set("K1", "21*exp(-(-7.8e3[J/mol])/(R_const*re.T))[m^3/mol]", "\u6291\u5236\u9879");
    model.component("comp1").variable("var1")
         .set("K2", "1.6*exp(-(-7.8e3[J/mol])/(R_const*re.T))[m^3/mol]", "\u6291\u5236\u9879");
    model.component("comp1").variable("var1")
         .set("K3", "105*exp(-(3.0e4[J/mol])/(R_const*re.T))[m^3/mol]", "\u6291\u5236\u9879");
    model.component("comp1").variable("var1")
         .set("K4", "21*exp(-(0[J/mol])/(R_const*re.T))[m^3/mol]", "\u6291\u5236\u9879");
    model.component("comp1").variable().create("var2");

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2")
         .set("K_NH3_ASC", "7.6e-7[m^3/mol]*exp(-100[kJ/mol]/R_const*(1/523[K]-1/re2.T))", "NH3 \u5438\u9644\u5e73\u8861\u5e38\u6570");
    model.component("comp1").variable("var2")
         .set("G_ASC", "re2.T*(1+K1_ASC*re2.c_NO+K2_ASC*re2.c_H2O)^2*(1+K3_ASC*re2.c_NH3)^2*(1+K4_ASC*re2.c_O2)^2[1/(K)]", "\u5438\u6536\u7269\u8d28\u7684\u6291\u5236\u6548\u5e94");
    model.component("comp1").variable("var2")
         .set("K1_ASC", "21*exp(-(-7.8e3[J/mol])/(R_const*re2.T))[m^3/mol]", "\u6291\u5236\u9879");
    model.component("comp1").variable("var2")
         .set("K2_ASC", "1.6*exp(-(-7.8e3[J/mol])/(R_const*re2.T))[m^3/mol]", "\u6291\u5236\u9879");
    model.component("comp1").variable("var2")
         .set("K3_ASC", "105*exp(-(3.0e4[J/mol])/(R_const*re2.T))[m^3/mol]", "\u6291\u5236\u9879");
    model.component("comp1").variable("var2")
         .set("K4_ASC", "21*exp(-(0[J/mol])/(R_const*re2.T))[m^3/mol]", "\u6291\u5236\u9879");
    model.component("comp1").variable().create("var3");

//    To import content from file, use:
//    model.component("comp1").variable("var3").loadFile("FILENAME");
    model.component("comp1").variable("var3")
         .set("X_NOx_SCR", "(FNO_in+FNO2_in-re.F_NO-re.F_NO2)/(FNO_in+FNO2_in)", "SCR \u4e2d\u7684 NOx \u8f6c\u5316\u7387");
    model.component("comp1").variable("var3")
         .set("X_NOx_tot", "(FNO_in+FNO2_in-re2.F_NO-re2.F_NO2)/(FNO_in+FNO2_in)", "\u7cfb\u7edf (SCR + ASC) \u7684 NOx \u603b\u8f6c\u5316\u7387");
    model.component("comp1").variable("var3")
         .set("X_NH3_SCR", "(FNH3_in-re.F_NH3)/FNH3_in", "SCR \u4e2d\u7684 NH3 \u8f6c\u5316\u7387");
    model.component("comp1").variable("var3")
         .set("X_NH3_tot", "(FNH3_in-re2.F_NH3)/FNH3_in", "NH3 \u603b\u8f6c\u5316\u7387");
    model.component("comp1").variable("var3")
         .set("yNH3_SCR", "re.c_NH3/re.csum", "SCR \u50ac\u5316\u5e8a\u4e2d\u7684 NH3 \u6469\u5c14\u5206\u6570");
    model.component("comp1").variable("var3")
         .set("yNOx_SCR", "(re.c_NO+re.c_NO2)/re.csum", "SCR \u50ac\u5316\u5e8a\u4e2d\u7684 NOx \u6469\u5c14\u5206\u6570");
    model.component("comp1").variable("var3")
         .set("yNH3_ASC", "re2.c_NH3/re2.csum", "ASC \u4e2d\u7684 NH3 \u6469\u5c14\u5206\u6570");
    model.component("comp1").variable("var3")
         .set("yNOx_ASC", "(re2.c_NO+re2.c_NO2)/re2.csum", "ASC \u4e2d\u7684 NOx \u6469\u5c14\u5206\u6570");

    model.thermodynamics().feature().create("spec1", "UserDefinedSpecies");
    model.thermodynamics().feature("spec1").set("Species", "N2O4");
    model.thermodynamics().feature("spec1").set("SpeciesState", "GasLiquid");
    model.thermodynamics().feature("spec1").set("CAS", "10544-72-6");
    model.thermodynamics().feature("spec1").set("ChemicalFormula", "N2O4");
    model.thermodynamics().feature("spec1").set("StructureFormula", "");
    model.thermodynamics().feature("spec1")
         .set("IdealGas_HeatCapacityCp", new String[][]{{"151.4994", "22.8199439173", "0.248101835092", "-0.000129016784375", "-2.07976512868e-07", "223.9986"}, 
         {"223.9986", "17.3772455746", "0.320995572054", "-0.000454437286832", "2.7628321384e-07", "310.9977"}, 
         {"310.9977", "7.55672923384", "0.415727941528", "-0.000759045229188", "6.02767881686e-07", "325.4976"}, 
         {"325.4976", "23.1617866623", "0.271901466969", "-0.000317178762095", "1.50264322937e-07", "557.4951"}, 
         {"557.4951", "41.600968157", "0.172676299817", "-0.000139194835147", "4.38454940552e-08", "803.9924"}, 
         {"803.9924", "59.0032311688", "0.107741868801", "-5.84298540152e-05", "1.036052532e-08", "1499.985"}});
    model.thermodynamics().feature("spec1")
         .set("Saturated_Liquid_Density", new String[][]{{"261.9", "23723.3913174", "-54.5872209623", "0.161934291432", "-0.000237308754858", "343.1466"}});
    model.thermodynamics().feature("spec1")
         .set("Vapor_ThermalConductivity", new String[][]{{"380.0038", "29.7807267369", "-0.219234326434", "0.000540095868453", "-4.44593410339e-07", "400"}});
    model.thermodynamics().feature("spec1")
         .set("VaporViscosity", new String[][]{{"300.003", "1.63115957629e-05", "-1.40930105836e-07", "6.48383663156e-10", "-6.82053838304e-13", "335.00235"}, 
         {"335.00235", "-9.55153890719e-06", "9.06784306719e-08", "-4.29802526723e-11", "5.86563034883e-15", "558.69014735"}, 
         {"558.69014735", "-1.14075878119e-05", "1.00644861511e-07", "-6.08191762446e-11", "1.65089321605e-14", "999.99"}});
    model.thermodynamics().feature("spec1")
         .set("GasLiquidConstant", new String[][]{{"\u7edd\u5bf9\u71b5", "304.32", "J/mol/K"}, 
         {"\u4e34\u754c\u538b\u7f29\u56e0\u5b50", "0.233", "1"}, 
         {"\u4e34\u754c\u538b\u529b", "1.0031e+07", "Pa"}, 
         {"\u4e34\u754c\u6e29\u5ea6", "431.15", "K"}, 
         {"\u4e34\u754c\u4f53\u79ef", "8.249e-05", "m^3/mol"}, 
         {"\u5076\u6781\u77e9", "0", "C\u00b7m"}, 
         {"\u71c3\u70e7\u70ed\uff08\u9ad8\u70ed\u503c\uff09", "0", "J/mol"}, 
         {"Lennard-Jones \u76f4\u5f84\uff08\u52bf\u80fd\u7279\u5f81\u957f\u5ea6\uff09", "0", "m"}, 
         {"Lennard-Jones \u80fd\u91cf\uff08\u6700\u5c0f\u52bf\u80fd\uff09", "0", "K"}, 
         {"\u6b63\u5e38\u6cb8\u70b9\u4e0b\u7684\u6db2\u4f53\u4f53\u79ef", "0", "m^3/mol"}, 
         {"\u5206\u5b50\u8d28\u91cf", "92.011", "g/mol"}, 
         {"\u6b63\u5e38\u6cb8\u70b9\u6e29\u5ea6", "294.3", "K"}, 
         {"\u6807\u51c6\u751f\u6210\u7113", "9163", "J/mol"}, 
         {"\u6807\u51c6\u6469\u5c14\u751f\u6210\u7113\uff08\u79bb\u5b50\u6eb6\u6db2\uff09", "0", "J/mol"}, 
         {"\u6807\u51c6\u6469\u5c14\u751f\u6210\u7113\uff08\u6db2\u4f53\uff09", "0", "J/mol"}, 
         {"\u6807\u51c6\u6469\u5c14\u751f\u6210\u7113\uff08\u56fa\u4f53\uff09", "0", "J/mol"}, 
         {"\u6807\u51c6\u6469\u5c14\u751f\u6210\u7113\uff08\u84b8\u6c7d\uff09", "0", "J/mol"}, 
         {"\u6807\u51c6\u6469\u5c14\u71b5\uff08\u79bb\u5b50\u6eb6\u6db2\uff09", "0", "J/mol/K"}, 
         {"\u6807\u51c6\u6469\u5c14\u71b5\uff08\u6db2\u4f53\uff09", "0", "J/mol/K"}, 
         {"\u6807\u51c6\u6469\u5c14\u71b5\uff08\u56fa\u4f53\uff09", "0", "J/mol/K"}, 
         {"\u6807\u51c6\u6469\u5c14\u71b5\uff08\u84b8\u6c7d\uff09", "0", "J/mol/K"}, 
         {"\u8303\u5fb7\u534e\u9762\u79ef", "0", "m^2/mol"}, 
         {"\u8303\u5fb7\u534e\u4f53\u79ef", "0", "m^3/mol"}});
    model.thermodynamics().feature("spec1")
         .set("GasLiquidModelOptions", new String[][]{{"\u504f\u5fc3\u56e0\u5b50", "0.85327", "1"}, 
         {"Chao-Seader \u504f\u5fc3\u56e0\u5b50", "0", "1"}, 
         {"Chao-Seader \u6db2\u4f53\u4f53\u79ef", "0", "m^3/mol"}, 
         {"Chao-Seader \u6eb6\u89e3\u5ea6\u53c2\u6570", "0", "J^0.5/m^1.5"}, 
         {"COSTALD \u504f\u5fc3\u56e0\u5b50", "0", "1"}, 
         {"COSTALD \u4f53\u79ef\u53c2\u6570", "0", "m^3/mol"}, 
         {"\u5bcc\u52d2\u6269\u6563\u4f53\u79ef", "13.1", "cm^3"}, 
         {"\u7b49\u5f20\u6bd4\u5bb9", "8.8676e-06", "kg^0.25*m^3/mol/s^0.5"}, 
         {"Peng-Robinson (Twu) \u53c2\u6570 L", "0", "1"}, 
         {"Peng-Robinson (Twu) \u53c2\u6570 M", "0", "1"}, 
         {"Peng-Robinson (Twu) \u53c2\u6570 N", "0", "1"}, 
         {"Rackett \u53c2\u6570", "0", "1"}, 
         {"\u6eb6\u89e3\u5ea6\u53c2\u6570", "0", "J^0.5/m^1.5"}, 
         {"Stockmayer \u03b4 \u53c2\u6570", "0", "1"}, 
         {"UNIQUAC Q \u53c2\u6570", "0", "1"}, 
         {"UNIQUAC R \u53c2\u6570", "0", "1"}, 
         {"Wilke-Chang \u5173\u8054\u53c2\u6570", "1", "1"}, 
         {"Wilson \u4f53\u79ef\u53c2\u6570", "0", "m^3/mol"}});
    model.thermodynamics().feature().create("spec2", "UserDefinedSpecies");
    model.thermodynamics().feature("spec2").set("Species", "NO2");
    model.thermodynamics().feature("spec2").set("SpeciesState", "GasLiquid");
    model.thermodynamics().feature("spec2").set("CAS", "10102-44-0");
    model.thermodynamics().feature("spec2").set("ChemicalFormula", "NO2");
    model.thermodynamics().feature("spec2").set("StructureFormula", "");
    model.thermodynamics().feature("spec2")
         .set("IdealGas_HeatCapacityCp", new String[][]{{"165.9993", "35.8254425805", "-0.0567293232053", "0.000356001767705", "-4.78004544541e-07", "223.9986"}, 
         {"223.9986", "32.4036320949", "-0.0109012177757", "0.00015141073262", "-1.73551696657e-07", "310.9977"}, 
         {"310.9977", "27.9650785955", "0.0319147274807", "1.37378647707e-05", "-2.59912614739e-08", "470.49"}, 
         {"470.49", "24.2998355133", "0.055285232749", "-3.5934194648e-05", "9.20001143717e-09", "963.4907"}});
    model.thermodynamics().feature("spec2")
         .set("Saturated_Liquid_Density", new String[][]{{"261.9026", "47780.8048854", "-96.0025964011", "0.26232127372", "-0.000423346367284", "327.9932"}, 
         {"327.9932", "103808.217268", "-608.459065462", "1.82472094865", "-0.00201118383356", "373.7483"}, 
         {"373.7483", "350445.755442", "-2588.16737322", "7.121623457", "-0.00673531012243", "392.3892"}, 
         {"392.3892", "1568690.00637", "-11902.2176906", "30.8583886824", "-0.0268996140205", "407.6409"}, 
         {"407.6409", "2741379.02684", "-20532.5270178", "52.0297415255", "-0.0442117081071", "414.4194"}, 
         {"414.4194", "23901405.4952", "-173710.875824", "421.651310414", "-0.341512436861", "421.1979"}, 
         {"421.1979", "-24025981.4299", "167653.980875", "-388.81060475", "0.299881956897", "424.5872"}, 
         {"424.5872", "1171039730.24", "-8276305.55206", "19498.6460424", "-15.3132882369", "426.2818"}, 
         {"426.2818", "-3800925140.26", "26714385.1401", "-62584.8271252", "48.872322602", "427.9764"}, 
         {"427.9764", "17346687004.3", "-121524715.039", "283787.3097", "-220.90278402", "429.6711"}, 
         {"429.6711", "-22629305820.2", "157591051.588", "-365815.995308", "283.051135623", "431.3657"}});
    model.thermodynamics().feature("spec2")
         .set("Vapor_ThermalConductivity", new String[][]{{"420.0042", "-15.8305048878", "0.111940151375", "-0.000264111354853", "2.08523312366e-07", "425.804058"}, 
         {"425.804058", "0.142692610769", "-0.000598918170081", "1.86430549483e-07", "1.62236779811e-09", "483.75782068"}, 
         {"483.75782068", "0.568403779574", "-0.00323894475326", "5.64376161541e-06", "-2.13800625176e-09", "530.260979312"}, 
         {"530.260979312", "4.28090087709", "-0.0242427367105", "4.52540531072e-05", "-2.70378782249e-08", "605.722503968"}, 
         {"605.722503968", "3.54280559853", "-0.0205871257316", "3.92189281862e-05", "-2.37167067416e-08", "651.762146706"}, 
         {"651.762146706", "-8.52192261186", "0.034945686068", "-4.59851789874e-05", "1.98595721224e-08", "744.87380587"}, 
         {"744.87380587", "-6.69104780557", "0.0275717867906", "-3.60856507461e-05", "1.54295025977e-08", "791.067898284"}});
    model.thermodynamics().feature("spec2")
         .set("VaporViscosity", new String[][]{{"300.003", "0.000239189260846", "-2.40914442054e-06", "8.25332107135e-09", "-9.09949292819e-12", "307.0029"}, 
         {"307.0029", "-1.89810139051e-05", "1.13668309412e-07", "3.57676542572e-11", "-1.77152219467e-13", "335.0023"}, 
         {"335.0023", "-3.28500933225e-05", "2.37868167897e-07", "-3.34975646566e-10", "1.91744054602e-13", "488.9995"}, 
         {"488.9995", "-2.44211701796e-05", "1.86156930097e-07", "-2.29226585881e-10", "1.19658731621e-13", "579.9978"}, 
         {"579.9978", "-1.03829719748e-05", "1.13545284649e-07", "-1.04033618859e-10", "4.77084776588e-14", "684.9958"}, 
         {"684.9958", "1.95668473445e-06", "5.95026611349e-08", "-2.51387942283e-11", "9.31660010734e-15", "999.99"}});
    model.thermodynamics().feature("spec2")
         .set("GasLiquidConstant", new String[][]{{"\u7edd\u5bf9\u71b5", "239.92", "J/mol/K"}, 
         {"\u4e34\u754c\u538b\u7f29\u56e0\u5b50", "0.233", "1"}, 
         {"\u4e34\u754c\u538b\u529b", "10132500", "Pa"}, 
         {"\u4e34\u754c\u6e29\u5ea6", "431.15", "K"}, 
         {"\u4e34\u754c\u4f53\u79ef", "8.249e-05", "m^3/mol"}, 
         {"\u5076\u6781\u77e9", "0", "C\u00b7m"}, 
         {"\u71c3\u70e7\u70ed\uff08\u9ad8\u70ed\u503c\uff09", "0", "J/mol"}, 
         {"Lennard-Jones \u76f4\u5f84\uff08\u52bf\u80fd\u7279\u5f81\u957f\u5ea6\uff09", "0", "m"}, 
         {"Lennard-Jones \u80fd\u91cf\uff08\u6700\u5c0f\u52bf\u80fd\uff09", "0", "K"}, 
         {"\u6b63\u5e38\u6cb8\u70b9\u4e0b\u7684\u6db2\u4f53\u4f53\u79ef", "0", "m^3/mol"}, 
         {"\u5206\u5b50\u8d28\u91cf", "46.0055", "g/mol"}, 
         {"\u6b63\u5e38\u6cb8\u70b9\u6e29\u5ea6", "294.15", "K"}, 
         {"\u6807\u51c6\u751f\u6210\u7113", "33180", "J/mol"}, 
         {"\u6807\u51c6\u6469\u5c14\u751f\u6210\u7113\uff08\u79bb\u5b50\u6eb6\u6db2\uff09", "0", "J/mol"}, 
         {"\u6807\u51c6\u6469\u5c14\u751f\u6210\u7113\uff08\u6db2\u4f53\uff09", "0", "J/mol"}, 
         {"\u6807\u51c6\u6469\u5c14\u751f\u6210\u7113\uff08\u56fa\u4f53\uff09", "0", "J/mol"}, 
         {"\u6807\u51c6\u6469\u5c14\u751f\u6210\u7113\uff08\u84b8\u6c7d\uff09", "0", "J/mol"}, 
         {"\u6807\u51c6\u6469\u5c14\u71b5\uff08\u79bb\u5b50\u6eb6\u6db2\uff09", "0", "J/mol/K"}, 
         {"\u6807\u51c6\u6469\u5c14\u71b5\uff08\u6db2\u4f53\uff09", "0", "J/mol/K"}, 
         {"\u6807\u51c6\u6469\u5c14\u71b5\uff08\u56fa\u4f53\uff09", "0", "J/mol/K"}, 
         {"\u6807\u51c6\u6469\u5c14\u71b5\uff08\u84b8\u6c7d\uff09", "0", "J/mol/K"}, 
         {"\u8303\u5fb7\u534e\u9762\u79ef", "222300", "m^2/mol"}, 
         {"\u8303\u5fb7\u534e\u4f53\u79ef", "1.391e-5", "m^3/mol"}});
    model.thermodynamics().feature("spec2")
         .set("GasLiquidModelOptions", new String[][]{{"\u504f\u5fc3\u56e0\u5b50", "0.851088", "1"}, 
         {"Chao-Seader \u504f\u5fc3\u56e0\u5b50", "0", "1"}, 
         {"Chao-Seader \u6db2\u4f53\u4f53\u79ef", "0", "m^3/mol"}, 
         {"Chao-Seader \u6eb6\u89e3\u5ea6\u53c2\u6570", "0", "J^0.5/m^1.5"}, 
         {"COSTALD \u504f\u5fc3\u56e0\u5b50", "0", "1"}, 
         {"COSTALD \u4f53\u79ef\u53c2\u6570", "0", "m^3/mol"}, 
         {"\u5bcc\u52d2\u6269\u6563\u4f53\u79ef", "13.1", "cm^3"}, 
         {"\u7b49\u5f20\u6bd4\u5bb9", "8.8676e-06", "kg^0.25*m^3/mol/s^0.5"}, 
         {"Peng-Robinson (Twu) \u53c2\u6570 L", "0", "1"}, 
         {"Peng-Robinson (Twu) \u53c2\u6570 M", "0", "1"}, 
         {"Peng-Robinson (Twu) \u53c2\u6570 N", "0", "1"}, 
         {"Rackett \u53c2\u6570", "0", "1"}, 
         {"\u6eb6\u89e3\u5ea6\u53c2\u6570", "0", "J^0.5/m^1.5"}, 
         {"Stockmayer \u03b4 \u53c2\u6570", "0", "1"}, 
         {"UNIQUAC Q \u53c2\u6570", "0", "1"}, 
         {"UNIQUAC R \u53c2\u6570", "0", "1"}, 
         {"Wilke-Chang \u5173\u8054\u53c2\u6570", "1", "1"}, 
         {"Wilson \u4f53\u79ef\u53c2\u6570", "0", "m^3/mol"}});
    model.thermodynamics().feature().create("pp1", "BuiltinPropertyPackage");
    model.thermodynamics().feature("pp1")
         .set("compoundlist", new String[][]{{"N2O4", "10544-72-6", "N2O4", "UserDefined"}, {"NO2", "10102-44-0", "NO2", "UserDefined"}});
    model.thermodynamics().feature("pp1").set("phase_list", new String[][]{{"Gas", "Vapor"}});
    model.thermodynamics().feature("pp1").label("\u6c14\u4f53\u7cfb\u7edf 1");
    model.thermodynamics().feature("pp1").set("manager_id", "COMSOL");
    model.thermodynamics().feature("pp1").set("manager_version", "1.0");
    model.thermodynamics().feature("pp1").set("packagename", "pp1");
    model.thermodynamics().feature("pp1").set("package_desc", "\u5185\u7f6e\u5c5e\u6027\u5305");
    model.thermodynamics().feature("pp1").set("managerindex", "0");
    model.thermodynamics().feature("pp1").set("packageid", "COMSOL1");
    model.thermodynamics().feature("pp1").set("ThermodynamicModel", "IdealGas");
    model.thermodynamics().feature("pp1").set("LiquidPhaseModel", "None");
    model.thermodynamics().feature("pp1").set("LiquidCard", "None");
    model.thermodynamics().feature("pp1").set("EOSModel", "IdealGas");
    model.thermodynamics().feature("pp1").set("GasPhaseModel", "IdealGas");
    model.thermodynamics().feature("pp1").set("GasEOSCard", "GasPhaseModel");
    model.thermodynamics().feature("pp1").set("VapDiffusivity", "Automatic");
    model.thermodynamics().feature("pp1").set("VapThermalConductivity", "KineticTheory");
    model.thermodynamics().feature("pp1").set("VapViscosity", "Brokaw");
    model.thermodynamics().feature("pp1").storePersistenceData();
    model.thermodynamics().feature("pp1").set("packagename", "pp1");
    model.thermodynamics().feature("pp1").label("Gas System 1");
    model.thermodynamics().feature().remove("spec1");
    model.thermodynamics().feature().create("pp1", "BuiltinPropertyPackage");
    model.thermodynamics().feature("pp1")
         .set("compoundlist", new String[][]{{"NO2", "10102-44-0", "NO2", "UserDefined"}, 
         {"ammonia", "7664-41-7", "H3N", "COMSOL"}, 
         {"nitrogen", "7727-37-9", "N2", "COMSOL"}, 
         {"nitrogen oxide", "10102-43-9", "NO", "COMSOL"}, 
         {"oxygen", "7782-44-7", "O2", "COMSOL"}, 
         {"water", "7732-18-5", "H2O", "COMSOL"}});
    model.thermodynamics().feature("pp1").set("phase_list", new String[][]{{"Gas", "Vapor"}});
    model.thermodynamics().feature("pp1").label("\u6c14\u4f53\u7cfb\u7edf 1");
    model.thermodynamics().feature("pp1").set("manager_id", "COMSOL");
    model.thermodynamics().feature("pp1").set("manager_version", "1.0");
    model.thermodynamics().feature("pp1").set("packagename", "pp1");
    model.thermodynamics().feature("pp1").set("package_desc", "\u5185\u7f6e\u5c5e\u6027\u5305");
    model.thermodynamics().feature("pp1").set("managerindex", "0");
    model.thermodynamics().feature("pp1").set("packageid", "COMSOL1");
    model.thermodynamics().feature("pp1").set("ThermodynamicModel", "IdealGas");
    model.thermodynamics().feature("pp1").set("LiquidPhaseModel", "None");
    model.thermodynamics().feature("pp1").set("LiquidCard", "None");
    model.thermodynamics().feature("pp1").set("EOSModel", "IdealGas");
    model.thermodynamics().feature("pp1").set("GasPhaseModel", "IdealGas");
    model.thermodynamics().feature("pp1").set("GasEOSCard", "GasPhaseModel");
    model.thermodynamics().feature("pp1").set("VapDiffusivity", "Automatic");
    model.thermodynamics().feature("pp1").set("VapThermalConductivity", "KineticTheory");
    model.thermodynamics().feature("pp1").set("VapViscosity", "Brokaw");
    model.thermodynamics().feature("pp1").storePersistenceData();
    model.thermodynamics().feature("pp1").set("WarningState", false);
    model.thermodynamics().feature("pp1").set("Warning", new String[]{""});

    model.component("comp1").physics("re").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch1").set("formula", "4NH3+4NO+O2=>4N2+6H2O");
    model.component("comp1").physics("re").feature("rch1").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re").feature("rch1")
         .set("r", "re.kf_1*re.c_NO*re.c_O2*re.c_NH3/(1+K_NH3*re.c_NH3)");
    model.component("comp1").physics("re").feature("rch1").set("bulkFwdOrder", 3);
    model.component("comp1").physics("re").feature("rch1").set("useArrhenius", true);
    model.component("comp1").physics("re").feature("rch1").set("Af", "A1_SCR");
    model.component("comp1").physics("re").feature("rch1").set("Ef", "E1_SCR");
    model.component("comp1").physics("re").create("rch2", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch2").set("formula", "2 NH3 + NO + NO2 => 2 N2 + 3 H2O");
    model.component("comp1").physics("re").feature("rch2").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re").feature("rch2")
         .set("r", "re.kf_2*re.c_NO2*re.c_NO*re.c_NH3/(1+K_NH3*re.c_NH3)");
    model.component("comp1").physics("re").feature("rch2").set("useArrhenius", true);
    model.component("comp1").physics("re").feature("rch2").set("Af", "A2_SCR");
    model.component("comp1").physics("re").feature("rch2").set("Ef", "E2_SCR");
    model.component("comp1").physics("re").feature("rch2").set("bulkFwdOrder", 3);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("re").create("rch3", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch3").set("formula", "8 NH3 + 6 NO2 => 7 N2 + 12 H2O");
    model.component("comp1").physics("re").feature("rch3").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re").feature("rch3").set("r", "re.kf_3*re.c_NO2*re.c_NH3/(1+K_NH3*re.c_NH3)");
    model.component("comp1").physics("re").feature("rch3").set("useArrhenius", true);
    model.component("comp1").physics("re").feature("rch3").set("Af", "A3_SCR");
    model.component("comp1").physics("re").feature("rch3").set("Ef", "E3_SCR");
    model.component("comp1").physics("re").feature("rch3").set("bulkFwdOrder", 2);
    model.component("comp1").physics("re").create("rch4", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch4").set("formula", "2 NO + O2 <=> 2 NO2");
    model.component("comp1").physics("re").feature("rch4").set("setKeq0", true);
    model.component("comp1").physics("re").feature("rch4").set("useArrhenius", true);
    model.component("comp1").physics("re").feature("rch4").set("Af", "A4");
    model.component("comp1").physics("re").feature("rch4").set("Ef", "E4");
    model.component("comp1").physics("re").prop("mixture").set("Thermodynamics", true);
    model.component("comp1").physics("re").prop("SpeciesMatching").setIndex("PackageSpecies", "water", 0, 0);
    model.component("comp1").physics("re").prop("SpeciesMatching").setIndex("PackageSpecies", "nitrogen", 1, 0);
    model.component("comp1").physics("re").prop("SpeciesMatching").setIndex("PackageSpecies", "ammonia", 2, 0);
    model.component("comp1").physics("re").prop("SpeciesMatching")
         .setIndex("PackageSpecies", "nitrogen oxide", 3, 0);
    model.component("comp1").physics("re").prop("SpeciesMatching").setIndex("PackageSpecies", "oxygen", 5, 0);
    model.component("comp1").physics("re").create("rch5", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch5").set("formula", "4 NH3 + 3 O2 => 2 N2 + 6 H2O");
    model.component("comp1").physics("re").feature("rch5").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re").feature("rch5").set("r", "re.kf_5*(re.c_NH3*re.c_O2)/(1+K_NH3*re.c_NH3)");
    model.component("comp1").physics("re").feature("rch5").set("useArrhenius", true);
    model.component("comp1").physics("re").feature("rch5").set("Af", "A5_SCR");
    model.component("comp1").physics("re").feature("rch5").set("Ef", "E5_SCR");
    model.component("comp1").physics("re").feature("rch5").set("bulkFwdOrder", 2);
    model.component("comp1").physics("re").create("rch6", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch6").set("formula", "4 NH3 + 5 O2 => 4 NO + 6 H2O");
    model.component("comp1").physics("re").feature("rch6").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re").feature("rch6").set("r", "re.kf_6*re.c_O2*re.c_NH3/G");
    model.component("comp1").physics("re").feature("rch6").set("useArrhenius", true);
    model.component("comp1").physics("re").feature("rch6").set("Af", "A6_SCR");
    model.component("comp1").physics("re").feature("rch6").set("Ef", "E6_SCR");
    model.component("comp1").physics("re").feature("rch6").set("bulkFwdOrder", 2);
    model.component("comp1").physics("re").prop("reactor").set("reactor", "plugflow");
    model.component("comp1").physics("re").feature("inits1").set("T0plug", "T_gas_in");
    model.component("comp1").physics("re").feature("inits1").setIndex("F0", "FH2O_in", 0, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("F0", "FN2_in", 1, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("F0", "FNH3_in", 2, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("F0", "FNO_in", 3, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("F0", "FNO2_in", 4, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("F0", "FO2_in", 5, 0);
    model.component("comp1").physics().create("re2", "ReactionEng");

    model.study("std1").feature("spf").setSolveFor("/physics/re2", true);

    model.component("comp1").physics("re2").prop("reactor").set("reactor", "plugflow");
    model.component("comp1").physics("re2").prop("energybalance").set("energybalance", "include");
    model.component("comp1").physics("re2").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re2").feature("rch1").set("formula", "4 NH3 + 3 O2 => 2 N2 + 6 H2O");
    model.component("comp1").physics("re2").feature("rch1").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re2").feature("rch1")
         .set("r", "re2.kf_1*re2.c_NH3*re2.c_O2/(1+K_NH3_ASC*re2.c_NH3)");
    model.component("comp1").physics("re2").feature("rch1").set("useArrhenius", true);
    model.component("comp1").physics("re2").feature("rch1").set("Af", "A1_ASC");
    model.component("comp1").physics("re2").feature("rch1").set("Ef", "E1_ASC");
    model.component("comp1").physics("re2").feature("rch1").set("bulkFwdOrder", 2);
    model.component("comp1").physics("re2").create("rch2", "ReactionChem", -1);
    model.component("comp1").physics("re2").feature("rch2").set("formula", "4 NH3 + 5 O2 => 4 NO + 6 H2O");
    model.component("comp1").physics("re2").feature("rch2").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re2").feature("rch2").set("r", "re2.kf_2*re2.c_O2*re2.c_NH3/G_ASC");
    model.component("comp1").physics("re2").feature("rch2").set("useArrhenius", true);
    model.component("comp1").physics("re2").feature("rch2").set("Af", "A2_ASC");
    model.component("comp1").physics("re2").feature("rch2").set("Ef", "E2_ASC");
    model.component("comp1").physics("re2").feature("rch2").set("bulkFwdOrder", 2);
    model.component("comp1").physics("re2").create("rch3", "ReactionChem", -1);
    model.component("comp1").physics("re2").feature("rch3").set("formula", "2 NO + O2 <=> 2 NO2");
    model.component("comp1").physics("re2").feature("rch3").set("setKeq0", true);
    model.component("comp1").physics("re2").feature("rch3").set("useArrhenius", true);
    model.component("comp1").physics("re2").feature("rch3").set("Af", "A4");
    model.component("comp1").physics("re2").feature("rch3").set("Ef", "E4");
    model.component("comp1").physics("re2").create("rch4", "ReactionChem", -1);
    model.component("comp1").physics("re2").feature("rch4").set("formula", "4 NH3 + 7 O2 => 4 NO2 + 6 H2O");
    model.component("comp1").physics("re2").feature("rch4").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re2").feature("rch4").set("r", "re2.kf_4*re2.c_NH3*re2.c_O2/G_ASC");
    model.component("comp1").physics("re2").feature("rch4").set("useArrhenius", true);
    model.component("comp1").physics("re2").feature("rch4").set("Af", "A4_ASC");
    model.component("comp1").physics("re2").feature("rch4").set("Ef", "E4_ASC");
    model.component("comp1").physics("re2").feature("rch4").set("bulkFwdOrder", 2);
    model.component("comp1").physics("re2").prop("mixture").set("Thermodynamics", true);
    model.component("comp1").physics("re2").prop("SpeciesMatching").setIndex("PackageSpecies", "water", 0, 0);
    model.component("comp1").physics("re2").prop("SpeciesMatching").setIndex("PackageSpecies", "nitrogen", 1, 0);
    model.component("comp1").physics("re2").prop("SpeciesMatching").setIndex("PackageSpecies", "ammonia", 2, 0);
    model.component("comp1").physics("re2").prop("SpeciesMatching")
         .setIndex("PackageSpecies", "nitrogen oxide", 3, 0);
    model.component("comp1").physics("re2").prop("SpeciesMatching").setIndex("PackageSpecies", "oxygen", 5, 0);
    model.component("comp1").physics("re2").feature("inits1").set("T0plug", "re.T");
    model.component("comp1").physics("re2").feature("inits1").setIndex("F0", "re.F_H2O", 0, 0);
    model.component("comp1").physics("re2").feature("inits1").setIndex("F0", "re.F_N2", 1, 0);
    model.component("comp1").physics("re2").feature("inits1").setIndex("F0", "re.F_NH3", 2, 0);
    model.component("comp1").physics("re2").feature("inits1").setIndex("F0", "re.F_NO", 3, 0);
    model.component("comp1").physics("re2").feature("inits1").setIndex("F0", "re.F_NO2", 4, 0);
    model.component("comp1").physics("re2").feature("inits1").setIndex("F0", "re.F_O2", 5, 0);

    model.study("std1").feature("spf").set("tlist", "0 V_SCR");
    model.study("std1").feature("spf").setSolveFor("/physics/re2", false);
    model.study("std1").feature("spf").set("useparam", true);
    model.study("std1").feature("spf").setIndex("pname", "A1_ASC", 0);
    model.study("std1").feature("spf").setIndex("plistarr", "", 0);
    model.study("std1").feature("spf").setIndex("punit", "m^3/(s*mol)", 0);
    model.study("std1").feature("spf").setIndex("pname", "A1_ASC", 0);
    model.study("std1").feature("spf").setIndex("plistarr", "", 0);
    model.study("std1").feature("spf").setIndex("punit", "m^3/(s*mol)", 0);
    model.study("std1").feature("spf").setIndex("pname", "T_gas_in", 0);
    model.study("std1").feature("spf").setIndex("plistarr", "range(523,25,825)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").set("scalemethod", "init");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{"", "", "", "", "", ""});
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"re.F_NH3", "re.F_NO", "re.F_O2", "re.F_N2", "re.F_H2O", "re.F_NO2"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u6469\u5c14\u6d41\u7387", "\u6469\u5c14\u6d41\u7387", "\u6469\u5c14\u6d41\u7387", "\u6469\u5c14\u6d41\u7387", "\u6469\u5c14\u6d41\u7387", "\u6469\u5c14\u6d41\u7387"});
    model.result("pg1").set("xlabel", "Reactor volume (m<sup>3</sup>)");
    model.result("pg1").label("\u6469\u5c14\u6d41\u7387 (re)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"re.T"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u6e29\u5ea6"});
    model.result("pg2").set("xlabel", "Reactor volume (m<sup>3</sup>)");
    model.result("pg2").label("\u6e29\u5ea6 (re)");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("expr", new String[]{});
    model.result("pg1").feature("glob1").set("descr", new String[]{});
    model.result("pg1").feature("glob1").setIndex("expr", "X_NOx_SCR", 0);
    model.result("pg1").feature("glob1").set("colorcycle", "long");
    model.result("pg1").feature("glob1").set("linemarker", "cycle");
    model.result("pg1").feature("glob1").set("markers", 1);
    model.result("pg1").feature("glob1").set("autoexpr", false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").set("titletype", "none");
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("glob1").setIndex("expr", "re.T-T_gas_in", 0);
    model.result("pg2").run();
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("expr", new String[]{});
    model.result().evaluationGroup("eg1").feature("gev1").set("descr", new String[]{});
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "X_NOx_SCR", 0);
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "none");
    model.result("pg3").create("tblp1", "Table");
    model.result("pg3").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg3").feature("tblp1").set("evaluationgroup", "eg1");
    model.result("pg3").feature("tblp1").set("linewidth", "preference");
    model.result("pg3").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg3").run();
    model.result("pg3").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg3").feature("tblp1").set("plotcolumns", new int[]{3});
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");

    model.study().create("std2");
    model.study("std2").create("spf", "StationaryPlugflow");
    model.study("std2").feature("spf").set("plotgroup", "Default");
    model.study("std2").feature("spf").set("solnum", "auto");
    model.study("std2").feature("spf").set("notsolnum", "auto");
    model.study("std2").feature("spf").set("outputmap", new String[]{});
    model.study("std2").feature("spf").setSolveFor("/physics/re", true);
    model.study("std2").feature("spf").setSolveFor("/physics/re2", true);
    model.study("std2").feature().remove("spf");
    model.study("std2").feature().copy("spf", "std1/spf");
    model.study("std2").feature("spf").setIndex("pname", "A1_ASC", 1);
    model.study("std2").feature("spf").setIndex("plistarr", "", 1);
    model.study("std2").feature("spf").setIndex("punit", "m^3/(s*mol)", 1);
    model.study("std2").feature("spf").setIndex("pname", "A1_ASC", 1);
    model.study("std2").feature("spf").setIndex("plistarr", "", 1);
    model.study("std2").feature("spf").setIndex("punit", "m^3/(s*mol)", 1);
    model.study("std2").feature("spf").setIndex("pname", "ANR", 1);
    model.study("std2").feature("spf").setIndex("plistarr", "range(1,0.1,1.6)", 1);
    model.study("std2").feature("spf").set("sweeptype", "filled");
    model.study("std2").feature("spf").set("tlist", "range(0, V_SCR/10, V_SCR)");
    model.study("std2").setGenPlots(false);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("v1").set("scalemethod", "init");
    model.sol("sol2").feature("pf1").set("tout", "tlist");

    model.result().setOnlyPlotWhenRequested(true);

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().evaluationGroup().duplicate("eg2", "eg1");
    model.result().evaluationGroup("eg2").set("data", "none");
    model.result().evaluationGroup("eg2").feature("gev1").set("data", "dset2");
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("looplevelinput", "manual", 1);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("looplevel", new int[]{1}, 1);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg2").feature().duplicate("gev2", "gev1");
    model.result().evaluationGroup("eg2").feature("gev2").setIndex("looplevel", new int[]{2}, 1);
    model.result().evaluationGroup("eg2").feature().duplicate("gev3", "gev2");
    model.result().evaluationGroup("eg2").feature("gev3").setIndex("looplevel", new int[]{3}, 1);
    model.result().evaluationGroup("eg2").feature().duplicate("gev4", "gev3");
    model.result().evaluationGroup("eg2").feature("gev4").setIndex("looplevel", new int[]{4}, 1);
    model.result().evaluationGroup("eg2").feature().duplicate("gev5", "gev4");
    model.result().evaluationGroup("eg2").feature("gev5").setIndex("looplevel", new int[]{5}, 1);
    model.result().evaluationGroup("eg2").feature().duplicate("gev6", "gev5");
    model.result().evaluationGroup("eg2").feature("gev6").setIndex("looplevel", new int[]{6}, 1);
    model.result().evaluationGroup("eg2").feature().duplicate("gev7", "gev6");
    model.result().evaluationGroup("eg2").feature("gev7").setIndex("looplevel", new int[]{7}, 1);
    model.result().evaluationGroup("eg2").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "none");
    model.result("pg4").create("tblp1", "Table");
    model.result("pg4").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg4").feature("tblp1").set("evaluationgroup", "eg2");
    model.result("pg4").feature("tblp1").set("linewidth", "preference");
    model.result("pg4").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg4").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg4").feature("tblp1").set("plotcolumns", new int[]{4});
    model.result("pg4").feature("tblp1").set("legend", true);
    model.result("pg4").feature("tblp1").set("autoplotlabel", true);
    model.result("pg4").feature("tblp1").set("autoheaders", false);
    model.result("pg4").feature().duplicate("tblp2", "tblp1");
    model.result("pg4").feature("tblp2").set("plotcolumns", new int[]{8});
    model.result("pg4").feature().duplicate("tblp3", "tblp2");
    model.result("pg4").feature("tblp3").set("plotcolumns", new int[]{12});
    model.result("pg4").feature().duplicate("tblp4", "tblp3");
    model.result("pg4").feature("tblp4").set("plotcolumns", new int[]{16});
    model.result("pg4").feature().duplicate("tblp5", "tblp4");
    model.result("pg4").feature("tblp5").set("plotcolumns", new int[]{20});
    model.result("pg4").feature().duplicate("tblp6", "tblp5");
    model.result("pg4").feature("tblp6").set("plotcolumns", new int[]{24});
    model.result("pg4").feature().duplicate("tblp7", "tblp6");
    model.result("pg4").feature("tblp7").set("plotcolumns", new int[]{28});
    model.result("pg4").run();
    model.result().evaluationGroup().duplicate("eg3", "eg2");
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("expr", "X_NH3_SCR", 0);
    model.result().evaluationGroup("eg3").feature("gev2").setIndex("expr", "X_NH3_SCR", 0);
    model.result().evaluationGroup("eg3").feature("gev3").setIndex("expr", "X_NH3_SCR", 0);
    model.result().evaluationGroup("eg3").feature("gev4").setIndex("expr", "X_NH3_SCR", 0);
    model.result().evaluationGroup("eg3").feature("gev5").setIndex("expr", "X_NH3_SCR", 0);
    model.result().evaluationGroup("eg3").feature("gev6").setIndex("expr", "X_NH3_SCR", 0);
    model.result().evaluationGroup("eg3").feature("gev7").setIndex("expr", "X_NH3_SCR", 0);
    model.result().evaluationGroup("eg3").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").feature("tblp1").set("evaluationgroup", "eg3");
    model.result("pg5").feature("tblp2").set("evaluationgroup", "eg3");
    model.result("pg5").feature("tblp3").set("evaluationgroup", "eg3");
    model.result("pg5").feature("tblp4").set("evaluationgroup", "eg3");
    model.result("pg5").feature("tblp5").set("evaluationgroup", "eg3");
    model.result("pg5").feature("tblp6").set("evaluationgroup", "eg3");
    model.result("pg5").feature("tblp7").set("evaluationgroup", "eg3");
    model.result("pg5").set("legendpos", "lowerright");
    model.result("pg5").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg4");
    model.nodeGroup("grp2").add("plotgroup", "pg5");

    model.result().setOnlyPlotWhenRequested(false);

    model.study().create("std3");
    model.study("std3").create("spf", "StationaryPlugflow");
    model.study("std3").feature("spf").set("plotgroup", "Default");
    model.study("std3").feature("spf").set("solnum", "auto");
    model.study("std3").feature("spf").set("notsolnum", "auto");
    model.study("std3").feature("spf").set("outputmap", new String[]{});
    model.study("std3").feature("spf").setSolveFor("/physics/re", true);
    model.study("std3").feature("spf").setSolveFor("/physics/re2", true);
    model.study("std3").feature("spf").set("tlist", "0 V_SCR");
    model.study("std3").feature("spf").setSolveFor("/physics/re2", false);
    model.study("std3").feature().duplicate("spf1", "spf");
    model.study("std3").feature("spf1").set("tlist", "V_SCR V_SCR+V_ASC");
    model.study("std3").feature("spf1").setSolveFor("/physics/re2", true);
    model.study("std3").feature("spf1").setSolveFor("/physics/re", false);
    model.study("std3").setStoreSolution(true);
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("sol");
    model.study("std3").createAutoSequences("jobs");

    model.sol("sol3").runFromTo("st1", "v1");

    model.study("std3").feature("spf1").set("useinitsol", true);
    model.study("std3").feature("spf1").set("initmethod", "init");
    model.study("std3").feature("spf1").set("solnum", "last");
    model.study("std3").feature("spf1").set("usesol", true);
    model.study("std3").feature("spf1").set("notsolnum", "last");

    model.sol("sol3").feature("v1").set("scalemethod", "init");
    model.sol("sol3").feature("v2").set("scalemethod", "init");

    model.study("std3").create("param", "Parametric");
    model.study("std3").feature("param").setIndex("pname", "A1_ASC", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "m^3/(s*mol)", 0);
    model.study("std3").feature("param").setIndex("pname", "A1_ASC", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "m^3/(s*mol)", 0);
    model.study("std3").feature("param").setIndex("pname", "ANR", 0);
    model.study("std3").feature("param").setIndex("plistarr", "1 1.3 1.6", 0);
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol5");
    model.sol("sol5").study("std3");
    model.sol("sol5").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol5");

    model.sol().create("sol6");
    model.sol("sol6").study("std3");
    model.sol("sol6").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p1").feature("so2").set("psol", "sol6");
    model.batch("p1").run("compute");

    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").set("data", "dset6");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("expr", new String[]{});
    model.result("pg6").feature("glob1").set("descr", new String[]{});
    model.result("pg6").feature("glob1").set("expr", new String[]{"yNH3_SCR"});
    model.result("pg6").feature("glob1")
         .set("descr", new String[]{"SCR \u50ac\u5316\u5e8a\u4e2d\u7684 NH3 \u6469\u5c14\u5206\u6570"});
    model.result("pg6").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg6").feature("glob1").setIndex("unit", "ppm", 0);
    model.result("pg6").feature("glob1").set("linestyle", "cycle");
    model.result("pg6").feature("glob1").set("linecolor", "blue");
    model.result("pg6").feature("glob1").set("legend", false);
    model.result("pg6").feature().duplicate("glob2", "glob1");
    model.result("pg6").run();
    model.result("pg6").feature("glob2").set("data", "dset5");
    model.result("pg6").feature("glob2").setIndex("expr", "yNH3_ASC", 0);
    model.result("pg6").feature("glob2").set("linestyle", "cyclereset");
    model.result("pg6").feature("glob2").set("legend", true);
    model.result("pg6").feature("glob2").set("autoplotlabel", true);
    model.result("pg6").feature("glob2").set("autoexpr", false);
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("glob3", "glob1");
    model.result("pg6").run();
    model.result("pg6").feature("glob3").setIndex("expr", "yNOx_SCR", 0);
    model.result("pg6").feature("glob3").set("linestyle", "cyclereset");
    model.result("pg6").feature("glob3").set("linecolor", "magenta");
    model.result("pg6").feature().duplicate("glob4", "glob3");
    model.result("pg6").run();
    model.result("pg6").feature("glob4").set("data", "dset5");
    model.result("pg6").feature("glob4").setIndex("expr", "yNOx_ASC", 0);
    model.result("pg6").feature("glob4").set("legend", true);
    model.result("pg6").feature("glob4").set("autoplotlabel", true);
    model.result("pg6").feature("glob4").set("autoexpr", false);
    model.result("pg6").run();
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("twoyaxes", true);
    model.result("pg6").setIndex("plotonsecyaxis", true, 2, 1);
    model.result("pg6").setIndex("plotonsecyaxis", true, 3, 1);
    model.result("pg6").set("yseclabelactive", true);
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").set("twoyaxes", false);
    model.result("pg7").set("legendpos", "lowerright");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").set("expr", new String[]{"X_NH3_SCR"});
    model.result("pg7").feature("glob1").set("descr", new String[]{"SCR \u4e2d\u7684 NH3 \u8f6c\u5316\u7387"});
    model.result("pg7").feature("glob1").set("unit", new String[]{"ppm"});
    model.result("pg7").feature("glob1").setIndex("unit", 1, 0);
    model.result("pg7").run();
    model.result("pg7").feature("glob2").setIndex("expr", "X_NH3_tot", 0);
    model.result("pg7").feature("glob2").setIndex("unit", 1, 0);
    model.result("pg7").run();
    model.result("pg7").feature("glob3").setIndex("expr", "X_NOx_SCR", 0);
    model.result("pg7").feature("glob3").setIndex("unit", 1, 0);
    model.result("pg7").run();
    model.result("pg7").feature("glob4").setIndex("expr", "X_NOx_tot", 0);
    model.result("pg7").feature("glob4").setIndex("unit", 1, 0);
    model.result("pg7").run();
    model.result("pg6").run();

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").set("type", "plotgroup");
    model.nodeGroup().move("grp3", 2);
    model.nodeGroup("grp3").add("plotgroup", "pg6");
    model.nodeGroup("grp3").add("plotgroup", "pg7");

    model.study().create("std4");
    model.study("std4").create("spf", "StationaryPlugflow");
    model.study("std4").feature("spf").set("plotgroup", "Default");
    model.study("std4").feature("spf").set("solnum", "auto");
    model.study("std4").feature("spf").set("notsolnum", "auto");
    model.study("std4").feature("spf").set("outputmap", new String[]{});
    model.study("std4").feature("spf").setSolveFor("/physics/re", true);
    model.study("std4").feature("spf").setSolveFor("/physics/re2", true);
    model.study("std4").feature().remove("spf");
    model.study("std4").feature().copy("param", "std3/param");
    model.study("std4").feature().copy("spf", "std3/spf");
    model.study("std4").feature().copy("spf1", "std3/spf1");
    model.study("std4").setStoreSolution(true);
    model.study("std4").setGenPlots(false);
    model.study("std4").feature("param").set("sweeptype", "switch");
    model.study("std4").feature("param").setIndex("switchname", "default", 0);
    model.study("std4").feature("param").setIndex("switchcase", "all", 0);
    model.study("std4").feature("param").setIndex("switchlistarr", "", 0);
    model.study("std4").feature("param").setIndex("switchname", "default", 0);
    model.study("std4").feature("param").setIndex("switchcase", "all", 0);
    model.study("std4").feature("param").setIndex("switchlistarr", "", 0);
    model.study("std4").feature("param").setIndex("switchname", "par2", 0);
    model.study("std4").showAutoSequences("all");

    model.sol("sol13").feature("v1").set("scalemethod", "init");
    model.sol("sol13").feature("v2").set("scalemethod", "init");

    model.study("std4").feature("spf1").set("initstudy", "std4");
    model.study("std4").feature("spf1").set("initsoluse", "sol14");
    model.study("std4").feature("spf1").set("notstudy", "std4");
    model.study("std4").feature("spf1").set("notsoluse", "sol14");
    model.study("std4").createAutoSequences("all");

    model.sol().create("sol15");
    model.sol("sol15").study("std4");
    model.sol("sol15").label("\u53c2\u6570\u5316\u89e3 3");

    model.batch("p2").feature("so1").set("psol", "sol15");

    model.sol().create("sol16");
    model.sol("sol16").study("std4");
    model.sol("sol16").label("\u53c2\u6570\u5316\u89e3 4");

    model.batch("p2").feature("so2").set("psol", "sol16");
    model.batch("p2").run("compute");

    model.result("pg6").run();
    model.result().duplicate("pg8", "pg6");

    model.nodeGroup("grp3").add("plotgroup", "pg8");

    model.result("pg8").run();
    model.result("pg8").set("data", "dset10");
    model.result("pg8").run();
    model.result("pg8").feature("glob2").set("data", "dset9");
    model.result("pg8").run();
    model.result("pg8").feature("glob4").set("data", "dset9");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");

    model.nodeGroup("grp3").add("plotgroup", "pg9");

    model.result("pg9").run();
    model.result("pg9").set("twoyaxes", false);
    model.result("pg9").set("legendpos", "lowerright");
    model.result("pg9").run();
    model.result("pg9").feature("glob1").setIndex("expr", "X_NH3_SCR", 0);
    model.result("pg9").feature("glob1").setIndex("unit", 1, 0);
    model.result("pg9").run();
    model.result("pg9").feature("glob2").setIndex("expr", "X_NH3_tot", 0);
    model.result("pg9").feature("glob2").setIndex("unit", 1, 0);
    model.result("pg9").run();
    model.result("pg9").feature("glob3").setIndex("expr", "X_NOx_SCR", 0);
    model.result("pg9").feature("glob3").setIndex("unit", 1, 0);
    model.result("pg9").run();
    model.result("pg9").feature("glob4").setIndex("expr", "X_NOx_tot", 0);
    model.result("pg9").feature("glob4").setIndex("unit", 1, 0);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");

    model.nodeGroup("grp3").add("plotgroup", "pg10");

    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").feature().remove("glob3");
    model.result("pg10").feature().remove("glob4");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").feature("glob1").setIndex("expr", "re.T-T", 0);
    model.result("pg10").feature("glob1").set("linestyle", "solid");
    model.result("pg10").feature("glob1").set("linecolor", "cycle");
    model.result("pg10").run();
    model.result("pg10").feature("glob2").setIndex("expr", "re2.T-T", 0);
    model.result("pg10").feature("glob2").set("linestyle", "solid");
    model.result("pg10").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg10").run();

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg10").run();
    model.result("pg10").set("legendpos", "upperleft");
    model.result("pg10").run();
    model.result("pg8").run();

    model.nodeGroup("grp3").remove("plotgroup", "pg10", false);
    model.nodeGroup("grp3").remove("plotgroup", "pg9", false);
    model.nodeGroup("grp3").remove("plotgroup", "pg8", false);

    model.result("pg10").run();
    model.result("pg8").run();

    model.nodeGroup().create("grp4", "Results");
    model.nodeGroup("grp4").set("type", "plotgroup");
    model.nodeGroup().move("grp4", 3);
    model.nodeGroup("grp4").add("plotgroup", "pg8");
    model.nodeGroup("grp4").add("plotgroup", "pg9");
    model.nodeGroup("grp4").add("plotgroup", "pg10");

    model.result("pg9").run();

    model
         .title("\u53cc\u5e8a\u5e73\u63a8\u6d41\u53cd\u5e94\u5668\u4e2d NOx \u548c\u6c28\u7684\u8f6c\u5316\u52a8\u529b\u5b66\u5206\u6790");

    model
         .description("\u672c\u4f8b\u63a2\u8ba8\u4e86\u91cd\u578b\u67f4\u6cb9\u8f66\u6392\u6c14\u7cfb\u7edf\u4e2d NOx \u548c\u6c28\u7684\u50ac\u5316\u8f6c\u5316\u52a8\u529b\u5b66\u3002\u5728\u7b2c\u4e00\u4e2a\u9009\u62e9\u6027\u50ac\u5316\u8fd8\u539f (SCR) \u5e8a\u4e2d\uff0cNOx \u901a\u8fc7\u6c28\u8fdb\u884c\u9009\u62e9\u6027\u8fd8\u539f\uff0c\u968f\u540e\u5728\u8be5\u5355\u4f53\u50ac\u5316\u5242\u7684\u4e0b\u6e38\uff0c\u5269\u4f59\u7684\u6c28\u5728\u6c28\u9003\u9038\u50ac\u5316\u5242 (ASC) \u4e2d\u88ab\u6c27\u5316\u3002\u5f53\u5e9f\u6c14\u6d41\u7ecf\u4e32\u8054\u653e\u7f6e\u7684\u5355\u4f53\u50ac\u5316\u5242\u901a\u9053\u65f6\uff0c\u4f1a\u53d1\u751f\u8fd9\u4e9b\u53cd\u5e94\u3002\u7814\u7a76\u7684\u91cd\u70b9\u5728\u4e8e\u6e29\u5ea6\u3001\u6c14\u4f53\u7ec4\u6210\u548c\u6d41\u7387\u5bf9\u8f6c\u5316\u7387\u7684\u5f71\u54cd\u3002\u672c\u6a21\u578b\u901a\u8fc7\u201c\u53cd\u5e94\u5de5\u7a0b\u201d\u63a5\u53e3\u4e2d\u7684\u5e73\u63a8\u6d41\u53cd\u5e94\u5668\u8fdb\u884c\u8bbe\u7f6e\u3002");

    model.label("monolith_kinetics.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
