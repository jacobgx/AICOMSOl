/*
 * na_ion_battery_1d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:18 by COMSOL 6.3.0.290. */
public class na_ion_battery_1d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Batteries,_General");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("liion", "LithiumIonBatteryMPH", "geom1");

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
    model.study("std1").feature("cdi").setSolveFor("/physics/liion", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/liion", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L_n", "64[um]", "\u8d1f\u6781\u539a\u5ea6");
    model.param().set("L_p", "68[um]", "\u6b63\u6781\u539a\u5ea6");
    model.param().set("L_sep", "25[um]", "\u9694\u819c\u539a\u5ea6");
    model.param().set("R_n", "3.48[um]", "\u8d1f\u6781\u9897\u7c92\u534a\u5f84");
    model.param().set("R_p", "0.59[um]", "\u6b63\u6781\u9897\u7c92\u534a\u5f84");
    model.param().set("Acc", "2.54[cm^2]", "\u7535\u6781\u6a2a\u622a\u9762\u79ef");
    model.param().set("T0", "298.15[K]", "\u7535\u6c60\u6e29\u5ea6");
    model.param().set("csmax_n", "14.54[kmol/m^3]", "\u8d1f\u6781\u6700\u5927\u6d53\u5ea6");
    model.param().set("csmax_p", "15.32[kmol/m^3]", "\u6b63\u6781\u6700\u5927\u6d53\u5ea6");
    model.param().set("cs_0_n", "13.52[kmol/m^3]", "\u8d1f\u6781\u521d\u59cb\u6d53\u5ea6");
    model.param().set("cs_0_p", "3.32[kmol/m^3]", "\u6b63\u6781\u521d\u59cb\u6d53\u5ea6");
    model.param().set("cl_0", "1[kmol/m^3]", "\u7535\u89e3\u8d28\u4e2d\u7684 NaPF6 \u521d\u59cb\u6d53\u5ea6");
    model.param().set("alpha", "0.5", "\u7535\u8377\u8f6c\u79fb\u7cfb\u6570");
    model.param().set("tplus", "0.45", "\u8f6c\u79fb\u6570");
    model.param().set("sigmaeff_n", "256[S/m]", "\u8d1f\u6781\u7535\u5bfc\u7387");
    model.param().set("sigmaeff_p", "50[S/m]", "\u6b63\u6781\u7535\u5bfc\u7387");
    model.param().set("epsl_n", "0.51", "\u8d1f\u6781\u7684\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param().set("epsl_p", "0.23", "\u6b63\u6781\u7684\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param().set("epsl_sep", "0.55", "\u9694\u819c\u4e2d\u7684\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param().set("epsf_n", "0.001", "\u8d1f\u6781\u586b\u6599\u4f53\u79ef\u5206\u6570");
    model.param().set("epsf_p", "0.22", "\u6b63\u6781\u586b\u6599\u4f53\u79ef\u5206\u6570");
    model.param().set("Rct_n", "2[mohm*m^2]", "\u8d1f\u6781\u63a5\u89e6\u7535\u963b");
    model.param().set("Rct_p", "8.5[mohm*m^2]", "\u6b63\u6781\u63a5\u89e6\u7535\u963b");
    model.param().set("E_min", "2.5", "\u6700\u5c0f\u7535\u538b");
    model.param()
         .set("cl_ref", "1[mol/m^3]", "\u52a8\u529b\u5b66\u8868\u8fbe\u5f0f\u4e2d\u7535\u89e3\u8d28\u7684\u53c2\u8003 NaPF6 \u6d53\u5ea6");
    model.param().set("I_app", "1[A/m^2]", "\u5916\u52a0\u7535\u6d41\u5bc6\u5ea6");
    model.param()
         .set("Q_n", "cs_0_n*(1-epsl_n-epsf_n)*L_n*F_const", "\u521d\u59cb\u6761\u4ef6\u8d1f\u6781\u4e2d\u7684\u94a0\u5e93\u5b58");
    model.param()
         .set("Q_p", "(csmax_p-cs_0_p)*(1-epsl_p-epsf_p)*L_p*F_const", "\u521d\u59cb\u6761\u4ef6\u6b63\u6781\u4e2d\u5269\u4f59\u7684\u627f\u8f7d\u5bb9\u91cf");
    model.param().set("T_dch", "min(Q_n,Q_p)/I_app", "\u653e\u7535\u65f6\u95f4\u8bc4\u4f30");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").set("specify", "len");
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_n", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_sep", 1);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_p", 2);
    model.component("comp1").geom("geom1").run("i1");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").label("\u63d2\u503c - Eeq_NVPF\uff08\u6b63\u6781\uff09");
    model.component("comp1").func("int1").set("funcname", "Eeq_NVPF");
    model.component("comp1").func("int1")
         .set("table", new String[][]{{"0.21", "4.288102031"}, 
         {"0.21004478", "4.210892773"}, 
         {"0.219269532", "4.175283892"}, 
         {"0.261497402", "4.172472367"}, 
         {"0.332354842", "4.172738781"}, 
         {"0.409317336", "4.158176665"}, 
         {"0.474174208", "4.152479924"}, 
         {"0.526985168", "4.143767596"}, 
         {"0.560316674", "4.11121965"}, 
         {"0.575706188", "4.048901275"}, 
         {"0.582273973", "3.941995276"}, 
         {"0.585975815", "3.805375531"}, 
         {"0.590901654", "3.725196032"}, 
         {"0.598588947", "3.695521965"}, 
         {"0.655863013", "3.698707605"}, 
         {"0.696598205", "3.69292017"}, 
         {"0.741871138", "3.684179499"}, 
         {"0.802205196", "3.678465753"}, 
         {"0.864031932", "3.675727917"}, 
         {"0.930500897", "3.649245158"}, 
         {"0.959279735", "3.62262069"}, 
         {"0.979341333", "3.530616911"}, 
         {"0.980053837", "3.450437411"}, 
         {"0.983058101", "3.391026925"}, 
         {"0.992282853", "3.355418044"}, 
         {"0.996268304", "3.162363722"}, 
         {"0.999940293", "3.031684459"}});
    model.component("comp1").func("int1").set("extrap", "linear");
    model.component("comp1").func("int1").setIndex("fununit", "V", 0);
    model.component("comp1").func("int1").setIndex("argunit", 1, 0);
    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2").label("\u63d2\u503c - Eeq_HC\uff08\u8d1f\u6781\uff09");
    model.component("comp1").func("int2").set("funcname", "Eeq_HC");
    model.component("comp1").func("int2")
         .set("table", new String[][]{{"0.001436794", "1.318963892"}, 
         {"0.001643334", "1.21982507"}, 
         {"0.00811789", "1.112038542"}, 
         {"0.01027308", "1.077546494"}, 
         {"0.035479844", "0.978299913"}, 
         {"0.060758448", "0.844570264"}, 
         {"0.090185795", "0.719443422"}, 
         {"0.127982471", "0.577039126"}, 
         {"0.169900951", "0.456168787"}, 
         {"0.232688871", "0.317967115"}, 
         {"0.320485995", "0.1753473"}, 
         {"0.403954777", "0.110332349"}, 
         {"0.483167055", "0.088439192"}, 
         {"0.574861484", "0.075112923"}, 
         {"0.687380455", "0.066007238"}, 
         {"0.799899424", "0.056901553"}, 
         {"0.891593855", "0.043575284"}, 
         {"0.960353451", "0.038968561"}, 
         {"0.987446008", "0.034541438"}, 
         {"0.995806356", "0.021574368"}});
    model.component("comp1").func("int2").set("extrap", "linear");
    model.component("comp1").func("int2").setIndex("fununit", "V", 0);
    model.component("comp1").func("int2").setIndex("argunit", 1, 0);
    model.component("comp1").func().create("int3", "Interpolation");
    model.component("comp1").func("int3").label("\u63d2\u503c - Ds_p");
    model.component("comp1").func("int3").set("funcname", "Ds_p");
    model.component("comp1").func("int3")
         .set("table", new String[][]{{"0.131578947", "2.51E-15"}, 
         {"0.592105263", "1.71E-15"}, 
         {"1.052631579", "1.16E-15"}, 
         {"1.535087719", "7.41E-16"}, 
         {"2.01754386", "4.61E-16"}, 
         {"2.521929825", "2.62E-16"}, 
         {"2.98245614", "1.56E-16"}, 
         {"3.355263158", "9.92E-17"}, 
         {"3.618421053", "7.92E-17"}, 
         {"3.925438596", "6.61E-17"}, 
         {"4.254385965", "5.65E-17"}, 
         {"4.495614035", "5.53E-17"}, 
         {"4.890350877", "6.07E-17"}, 
         {"5.263157895", "6.65E-17"}, 
         {"5.679824561", "7.64E-17"}, 
         {"6.096491228", "8.38E-17"}, 
         {"6.578947368", "8.79E-17"}, 
         {"7.039473684", "8.81E-17"}, 
         {"7.587719298", "8.44E-17"}, 
         {"8.201754386", "7.52E-17"}, 
         {"8.837719298", "6.46E-17"}, 
         {"9.495614035", "5.05E-17"}, 
         {"10.10964912", "4.13E-17"}, 
         {"10.65789474", "3.45E-17"}, 
         {"11.18421053", "3.16E-17"}, 
         {"11.77631579", "3.17E-17"}, 
         {"12.34649123", "3.40E-17"}, 
         {"12.82894737", "3.57E-17"}, 
         {"13.33333333", "3.66E-17"}, 
         {"13.75", "3.58E-17"}, 
         {"14.25438596", "3.35E-17"}, 
         {"14.64912281", "2.74E-17"}, 
         {"15", "2.29E-17"}, 
         {"15.19736842", "1.95E-17"}});
    model.component("comp1").func("int3").set("extrap", "linear");
    model.component("comp1").func("int3").setIndex("fununit", "m^2/s", 0);
    model.component("comp1").func("int3").setIndex("argunit", "kmol/m^3", 0);
    model.component("comp1").func().create("int4", "Interpolation");
    model.component("comp1").func("int4").label("\u63d2\u503c - Ds_n");
    model.component("comp1").func("int4").set("funcname", "Ds_n");
    model.component("comp1").func("int4")
         .set("table", new String[][]{{"0.073891626", "2.57E-16"}, 
         {"0.246305419", "3.03E-16"}, 
         {"0.418719212", "3.54E-16"}, 
         {"0.714285714", "3.99E-16"}, 
         {"1.108374384", "4.50E-16"}, 
         {"1.477832512", "4.83E-16"}, 
         {"1.84729064", "5.31E-16"}, 
         {"2.24137931", "6.21E-16"}, 
         {"2.733990148", "7.60E-16"}, 
         {"3.177339901", "9.20E-16"}, 
         {"3.620689655", "1.11E-15"}, 
         {"4.113300493", "1.36E-15"}, 
         {"4.630541872", "1.57E-15"}, 
         {"5.197044335", "1.79E-15"}, 
         {"5.714285714", "1.93E-15"}, 
         {"6.280788177", "2.02E-15"}, 
         {"6.896551724", "1.97E-15"}, 
         {"7.413793103", "1.84E-15"}, 
         {"7.832512315", "1.67E-15"}, 
         {"8.325123153", "1.45E-15"}, 
         {"8.669950739", "1.27E-15"}, 
         {"9.039408867", "1.06E-15"}, 
         {"9.384236453", "8.77E-16"}, 
         {"9.729064039", "7.08E-16"}, 
         {"10.07389163", "5.44E-16"}, 
         {"10.39408867", "4.39E-16"}, 
         {"10.71428571", "3.58E-16"}, 
         {"10.96059113", "3.14E-16"}, 
         {"11.20689655", "2.96E-16"}, 
         {"11.50246305", "3.14E-16"}, 
         {"11.84729064", "3.80E-16"}, 
         {"12.14285714", "4.72E-16"}, 
         {"12.48768473", "6.13E-16"}, 
         {"12.83251232", "7.78E-16"}, 
         {"13.22660099", "1.01E-15"}, 
         {"13.71921182", "1.21E-15"}, 
         {"14.01477833", "1.28E-15"}, 
         {"14.26108374", "1.28E-15"}, 
         {"14.43349754", "1.24E-15"}});
    model.component("comp1").func("int4").set("extrap", "linear");
    model.component("comp1").func("int4").setIndex("fununit", "m^2/s", 0);
    model.component("comp1").func("int4").setIndex("argunit", "kmol/m^3", 0);
    model.component("comp1").func().create("int5", "Interpolation");
    model.component("comp1").func("int5").label("\u63d2\u503c - k_p");
    model.component("comp1").func("int5").set("funcname", "k_p");
    model.component("comp1").func("int5")
         .set("table", new String[][]{{"0.021929825", "2.27E-10"}, 
         {"0.372807018", "1.74E-10"}, 
         {"0.833333333", "1.24E-10"}, 
         {"1.293859649", "8.51E-11"}, 
         {"1.907894737", "4.83E-11"}, 
         {"2.456140351", "2.96E-11"}, 
         {"2.960526316", "1.95E-11"}, 
         {"3.442982456", "1.45E-11"}, 
         {"3.969298246", "1.16E-11"}, 
         {"4.802631579", "1.08E-11"}, 
         {"5.548245614", "1.17E-11"}, 
         {"6.381578947", "1.18E-11"}, 
         {"7.192982456", "1.10E-11"}, 
         {"8.092105263", "8.79E-12"}, 
         {"8.969298246", "6.65E-12"}, 
         {"9.649122807", "5.03E-12"}, 
         {"10.35087719", "3.87E-12"}, 
         {"11.0745614", "3.34E-12"}, 
         {"12.17105263", "3.37E-12"}, 
         {"13.22368421", "3.60E-12"}, 
         {"14.18859649", "3.30E-12"}, 
         {"14.93421053", "2.35E-12"}, 
         {"15.32894737", "1.91E-12"}});
    model.component("comp1").func("int5").set("extrap", "linear");
    model.component("comp1").func("int5").setIndex("fununit", "m/s", 0);
    model.component("comp1").func("int5").setIndex("argunit", "kmol/m^3", 0);
    model.component("comp1").func().create("int6", "Interpolation");
    model.component("comp1").func("int6").label("\u63d2\u503c - k_n");
    model.component("comp1").func("int6").set("funcname", "k_n");
    model.component("comp1").func("int6")
         .set("table", new String[][]{{"0.121359223", "3.33E-11"}, 
         {"0.412621359", "2.15E-11"}, 
         {"0.631067961", "1.39E-11"}, 
         {"0.800970874", "9.40E-12"}, 
         {"0.970873786", "6.88E-12"}, 
         {"1.140776699", "5.71E-12"}, 
         {"1.262135922", "5.26E-12"}, 
         {"1.529126214", "7.03E-12"}, 
         {"1.796116505", "1.04E-11"}, 
         {"2.063106796", "1.58E-11"}, 
         {"2.5", "2.65E-11"}, 
         {"3.058252427", "4.18E-11"}, 
         {"3.713592233", "5.83E-11"}, 
         {"4.660194175", "7.48E-11"}, 
         {"5.412621359", "7.96E-11"}, 
         {"6.067961165", "7.48E-11"}, 
         {"6.868932039", "6.47E-11"}, 
         {"7.718446602", "4.94E-11"}, 
         {"8.422330097", "3.47E-11"}, 
         {"9.004854369", "2.39E-11"}, 
         {"9.538834951", "1.61E-11"}, 
         {"10.02427184", "1.21E-11"}, 
         {"10.38834951", "1.06E-11"}, 
         {"10.75242718", "1.13E-11"}, 
         {"11.21359223", "1.51E-11"}, 
         {"11.72330097", "2.25E-11"}, 
         {"12.37864078", "3.54E-11"}, 
         {"12.91262136", "4.94E-11"}, 
         {"13.47087379", "6.34E-11"}, 
         {"14.00485437", "7.48E-11"}, 
         {"14.41747573", "7.96E-11"}});
    model.component("comp1").func("int6").set("extrap", "linear");
    model.component("comp1").func("int6").setIndex("fununit", "m/s", 0);
    model.component("comp1").func("int6").setIndex("argunit", "kmol/m^3", 0);
    model.component("comp1").func().create("int7", "Interpolation");
    model.component("comp1").func("int7").label("\u63d2\u503c - Dl");
    model.component("comp1").func("int7").set("funcname", "Dl");
    model.component("comp1").func("int7")
         .set("table", new String[][]{{"0.001131153", "4.14E-11"}, 
         {"0.124121064", "3.87E-11"}, 
         {"0.24937328", "3.62E-11"}, 
         {"0.387618465", "3.33E-11"}, 
         {"0.538734332", "3.11E-11"}, 
         {"0.741699786", "2.81E-11"}, 
         {"1.013696117", "2.49E-11"}, 
         {"1.27269948", "2.25E-11"}, 
         {"1.51427698", "2.14E-11"}, 
         {"1.811953531", "1.99E-11"}, 
         {"2.113940691", "1.86E-11"}, 
         {"2.32097218", "1.79E-11"}, 
         {"2.471904616", "1.76E-11"}});
    model.component("comp1").func("int7").set("extrap", "linear");
    model.component("comp1").func("int7").setIndex("fununit", "m^2/s", 0);
    model.component("comp1").func("int7").setIndex("argunit", "kmol/m^3", 0);
    model.component("comp1").func().create("int8", "Interpolation");
    model.component("comp1").func("int8").label("\u63d2\u503c - sigmal");
    model.component("comp1").func("int8").set("funcname", "sigmal");
    model.component("comp1").func("int8")
         .set("table", new String[][]{{"0.15", "4.04"}, {"0.5", "7.2"}, {"1", "8.83"}, {"1.5", "8.61"}, {"2", "7.6"}});
    model.component("comp1").func("int8").set("extrap", "linear");
    model.component("comp1").func("int8").setIndex("fununit", "mS/cm", 0);
    model.component("comp1").func("int8").setIndex("argunit", "kmol/m^3", 0);

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intop_ref_p");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(3);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "intop_ref_n");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop2").selection().set(2);

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("E_cell", "liion.phis0_ec1", "\u7535\u6c60\u7535\u538b");
    model.component("comp1").variable("var1")
         .set("E_pos", "liion.phis0_ec1-intop_ref_p(phil)", "\u6b63\u6781\u7535\u4f4d");
    model.component("comp1").variable("var1").set("E_neg", "0[V]-intop_ref_n(phil)", "\u8d1f\u6781\u7535\u4f4d");

    model.component("comp1").physics("liion").label("\u94a0\u79bb\u5b50\u7535\u6c60");
    model.component("comp1").physics("liion").prop("Ac").set("Ac", "Acc");
    model.component("comp1").physics("liion").feature("sep1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("liion").feature("sep1")
         .set("sigmal", new String[]{"sigmal(cl)", "0", "0", "0", "sigmal(cl)", "0", "0", "0", "sigmal(cl)"});
    model.component("comp1").physics("liion").feature("sep1").set("Dl_mat", "userdef");
    model.component("comp1").physics("liion").feature("sep1").set("Dl", "Dl(cl)");
    model.component("comp1").physics("liion").feature("sep1").set("transpNum_mat", "userdef");
    model.component("comp1").physics("liion").feature("sep1").set("transpNum", "tplus");
    model.component("comp1").physics("liion").feature("sep1").set("fcl_mat", "userdef");
    model.component("comp1").physics("liion").feature("sep1").set("epsl", "epsl_sep");
    model.component("comp1").physics("liion").create("pce1", "PorousElectrode", 1);
    model.component("comp1").physics("liion").feature("pce1").selection().set(1);
    model.component("comp1").physics("liion").feature("pce1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1")
         .set("sigmal", new String[]{"sigmal(cl)", "0", "0", "0", "sigmal(cl)", "0", "0", "0", "sigmal(cl)"});
    model.component("comp1").physics("liion").feature("pce1").set("Dl_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1").set("Dl", "Dl(cl)");
    model.component("comp1").physics("liion").feature("pce1").set("transpNum_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1").set("transpNum", "tplus");
    model.component("comp1").physics("liion").feature("pce1").set("fcl_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1")
         .set("sigma", new String[]{"sigmaeff_n", "0", "0", "0", "sigmaeff_n", "0", "0", "0", "sigmaeff_n"});
    model.component("comp1").physics("liion").feature("pce1").set("epss", "1-epsf_n-epsl_n");
    model.component("comp1").physics("liion").feature("pce1").set("epsl", "epsl_n");
    model.component("comp1").physics("liion").feature("pce1").set("ElectricCorrModel", "NoCorr");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("csinit", "cs_0_n");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("cEeqref_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("cEeqref", "csmax_n");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("Ds_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("Ds", "Ds_n(liion.cs_pce1)");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("rp", "R_n");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("socmin_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("socmax_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("Eeq_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1").feature("per1")
         .set("Eeq", "Eeq_HC(liion.cs_surface/csmax_n)");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("i0refType", "FromRateConstant");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("k", "k_n(liion.cs_surface)");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("alphaa", "alpha");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("alphac", "1-alpha");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("cl_ref", "cl_ref");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("dEeqdT_mat", "userdef");
    model.component("comp1").physics("liion").create("pce2", "PorousElectrode", 1);
    model.component("comp1").physics("liion").feature("pce2").selection().set(3);
    model.component("comp1").physics("liion").feature("pce2").set("sigmal_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce2")
         .set("sigmal", new String[]{"sigmal(cl)", "0", "0", "0", "sigmal(cl)", "0", "0", "0", "sigmal(cl)"});
    model.component("comp1").physics("liion").feature("pce2").set("Dl_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce2").set("Dl", "Dl(cl)");
    model.component("comp1").physics("liion").feature("pce2").set("transpNum_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce2").set("transpNum", "tplus");
    model.component("comp1").physics("liion").feature("pce2").set("fcl_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce2")
         .set("sigma", new String[]{"sigmaeff_p", "0", "0", "0", "sigmaeff_p", "0", "0", "0", "sigmaeff_p"});
    model.component("comp1").physics("liion").feature("pce2").set("epss", "1-epsf_p-epsl_p");
    model.component("comp1").physics("liion").feature("pce2").set("epsl", "epsl_p");
    model.component("comp1").physics("liion").feature("pce2").set("ElectricCorrModel", "NoCorr");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("csinit", "cs_0_p");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("cEeqref_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("cEeqref", "csmax_p");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("Ds_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("Ds", "Ds_p(liion.cs_pce2)");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("rp", "R_p");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("socmin_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("socmax_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("Eeq_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce2").feature("per1")
         .set("Eeq", "Eeq_NVPF(liion.cs_surface/csmax_p)");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("i0refType", "FromRateConstant");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("k", "k_p(liion.cs_surface)");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("alphaa", "alpha");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("alphac", "1-alpha");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("cl_ref", "cl_ref");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("dEeqdT_mat", "userdef");
    model.component("comp1").physics("liion").create("egnd1", "ElectricGround", 0);
    model.component("comp1").physics("liion").feature("egnd1").selection().set(1);
    model.component("comp1").physics("liion").feature("egnd1").set("IncludeContactResistance", true);
    model.component("comp1").physics("liion").feature("egnd1").set("Rc", "Rct_n");
    model.component("comp1").physics("liion").create("ec1", "ElectrodeCurrent", 0);
    model.component("comp1").physics("liion").feature("ec1").selection().set(4);
    model.component("comp1").physics("liion").feature("ec1").set("ElectronicCurrentType", "AverageCurrentDensity");
    model.component("comp1").physics("liion").feature("ec1").set("Ias", "-I_app");
    model.component("comp1").physics("liion").feature("ec1").set("phis0init", "4.2[V]");
    model.component("comp1").physics("liion").feature("ec1").set("IncludeContactResistance", true);
    model.component("comp1").physics("liion").feature("ec1").set("Rc", "Rct_p");
    model.component("comp1").physics("liion").feature("init1").set("cl", "cl_0");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T0"}});

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "L_n", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "L_n", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "I_app", 0);
    model.study("std1").feature("param").setIndex("plistarr", "1 5 10 12", 0);
    model.study("std1").feature("time").set("tlist", "range(0,0.1*T_dch, T_dch)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", "0.0001");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tout", "tsteps");
    model.sol("sol1").feature("t1").set("tstepsstore", 5);
    model.sol("sol1").feature("t1").create("st1", "StopCondition");
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "comp1.E_cell<2[V]", 0);
    model.sol("sol1").feature("t1").feature("st1").set("storestopcondsol", "stepafter");
    model.sol("sol1").feature("t1").feature("st1").set("stopcondwarn", false);

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{""});
    model.result("pg1").feature("glob1").set("expr", new String[]{"liion.phis0_ec1"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u8fb9\u754c\u7535\u4f4d"});
    model.result("pg1").label("\u5bf9\u5730\u8fb9\u754c\u7535\u6781\u7535\u4f4d (liion)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", ""});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"liion.soc_average_pce1", "liion.soc_average_pce2"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u5e73\u5747 SOC\uff0c\u591a\u5b54\u7535\u6781 1", "\u5e73\u5747 SOC\uff0c\u591a\u5b54\u7535\u6781 2"});
    model.result("pg2").label("\u7535\u6781\u5e73\u5747\u8377\u7535\u72b6\u6001 (liion)");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").label("\u7535\u89e3\u8d28\u7535\u4f4d (liion)");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg3").feature("lngr1").set("expr", new String[]{"phil"});
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (liion)");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg4").feature("lngr1").set("expr", new String[]{"phis"});
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg5").feature("lngr1").set("expr", new String[]{"cl"});
    model.result("pg5").label("\u7535\u89e3\u8d28\u76d0\u6d53\u5ea6 (liion)");
    model.result("pg1").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u7535\u6c60\u7535\u538b vs. \u7535\u6c60\u5bb9\u91cf");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "E_cell", 0);
    model.result("pg6").feature("glob1").set("expr", new String[]{"E_cell"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"\u7535\u6c60\u7535\u538b"});

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg6").feature("glob1").set("unit", new String[]{"V"});
    model.result("pg6").feature("glob1").set("xdata", "expr");
    model.result("pg6").feature("glob1").set("xdataexpr", "t*I_app*Acc");
    model.result("pg6").feature("glob1").set("xdataunit", "mAh");
    model.result("pg6").feature("glob1").set("xdatadescractive", true);
    model.result("pg6").feature("glob1").set("xdatadescr", "\u7535\u6c60\u5bb9\u91cf");
    model.result("pg6").feature("glob1").set("autodescr", false);
    model.result("pg6").run();
    model.result("pg6").set("legendpos", "lowerleft");
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u6b63\u6781\u7535\u4f4d vs. \u7535\u6c60\u5bb9\u91cf");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").set("expr", new String[]{"E_pos"});
    model.result("pg7").feature("glob1").set("descr", new String[]{"\u6b63\u6781\u7535\u4f4d"});
    model.result("pg7").feature("glob1").set("unit", new String[]{"V"});
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u8d1f\u6781\u7535\u4f4d vs. \u7535\u6c60\u5bb9\u91cf");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").set("expr", new String[]{"E_neg"});
    model.result("pg8").feature("glob1").set("descr", new String[]{"\u8d1f\u6781\u7535\u4f4d"});
    model.result("pg8").feature("glob1").set("unit", new String[]{"V"});
    model.result("pg8").run();
    model.result("pg8").set("legendpos", "upperleft");
    model.result("pg8").run();

    model.component("comp1").func("int1").createPlot("pg9");

    model.result("pg9").run();
    model.result("pg9").set("axislimits", true);
    model.result("pg9").set("ymin", 2.2);
    model.result("pg9").set("ymax", 5);
    model.result("pg9").set("titletype", "none");
    model.result("pg9").set("xlabelactive", true);
    model.result("pg9").set("xlabel", "c<sub>s</sub>/c<sub>s,max</sub> (1)");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "E<sub>eq,NVPF</sub> (V)");
    model.result("pg9").run();

    model.component("comp1").func("int2").createPlot("pg10");

    model.result("pg10").run();
    model.result("pg10").set("axislimits", true);
    model.result("pg10").set("ymin", -0.2);
    model.result("pg10").set("ymax", 2);
    model.result("pg10").set("titletype", "none");
    model.result("pg10").set("xlabelactive", true);
    model.result("pg10").set("xlabel", "c<sub>s</sub>/c<sub>s,max</sub> (1)");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "E<sub>eq,HC</sub> (V)");
    model.result("pg10").run();

    model.component("comp1").func("int3").createPlot("pg11");

    model.result("pg11").run();
    model.result("pg11").set("titletype", "none");
    model.result("pg11").set("xlabelactive", true);
    model.result("pg11").set("xlabel", "c<sub>s</sub> (kmol/m<sup>3</sup>)");
    model.result("pg11").set("ylabelactive", true);
    model.result("pg11").set("ylabel", "D<sub>s,p</sub> (m<sup>2</sup>/s)");
    model.result("pg11").run();

    model.component("comp1").func("int4").createPlot("pg12");

    model.result("pg12").run();
    model.result("pg12").set("titletype", "none");
    model.result("pg12").set("xlabelactive", true);
    model.result("pg12").set("xlabel", "c<sub>s</sub> (kmol/m<sup>3</sup>)");
    model.result("pg12").set("ylabelactive", true);
    model.result("pg12").set("ylabel", "D<sub>s,n</sub> (m<sup>2</sup>/s)");
    model.result("pg12").run();

    model.component("comp1").func("int5").createPlot("pg13");

    model.result("pg13").run();
    model.result("pg13").set("titletype", "none");
    model.result("pg13").set("xlabelactive", true);
    model.result("pg13").set("xlabel", "c<sub>s</sub> (kmol/m<sup>3</sup>)");
    model.result("pg13").set("ylabelactive", true);
    model.result("pg13").set("ylabel", "k<sub>p</sub> (m/s)");
    model.result("pg13").run();

    model.component("comp1").func("int6").createPlot("pg14");

    model.result("pg14").run();
    model.result("pg14").set("titletype", "none");
    model.result("pg14").set("xlabelactive", true);
    model.result("pg14").set("xlabel", "c<sub>s</sub> (kmol/m<sup>3</sup>)");
    model.result("pg14").set("ylabelactive", true);
    model.result("pg14").set("ylabel", "k<sub>n</sub> (m/s)");
    model.result("pg14").run();

    model.component("comp1").func("int7").createPlot("pg15");

    model.result("pg15").run();
    model.result("pg15").set("titletype", "none");
    model.result("pg15").set("xlabelactive", true);
    model.result("pg15").set("xlabel", "c<sub>l</sub> (kmol/m<sup>3</sup>)");
    model.result("pg15").set("ylabelactive", true);
    model.result("pg15").set("ylabel", "D<sub>l</sub> (m<sup>2</sup>/s)");
    model.result("pg15").run();

    model.component("comp1").func("int8").createPlot("pg16");

    model.result("pg16").run();
    model.result("pg16").set("titletype", "none");
    model.result("pg16").set("xlabelactive", true);
    model.result("pg16").set("xlabel", "c<sub>l</sub> (kmol/m<sup>3</sup>)");
    model.result("pg16").set("ylabelactive", true);
    model.result("pg16").set("ylabel", "\\sigma<sub>l</sub> (mS/cm)");
    model.result("pg16").run();
    model.result("pg9").run();
    model.result().remove("pg9");
    model.result().remove("pg10");
    model.result().remove("pg11");
    model.result().remove("pg12");
    model.result().remove("pg13");
    model.result().remove("pg14");
    model.result().remove("pg15");
    model.result().remove("pg16");

    model.title("\u94a0\u79bb\u5b50\u7535\u6c60\u7b49\u6e29\u6a21\u578b - \u4e00\u7ef4");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u9502\u79bb\u5b50\u7535\u6c60\u201d\u63a5\u53e3\u5bf9\u94a0\u79bb\u5b50\u7535\u6c60\u8fdb\u884c\u5efa\u6a21\uff0c\u5176\u4e2d\u91c7\u7528\u4e00\u7ef4\u51e0\u4f55\u548c\u7b49\u6e29\u6a21\u578b\u3002\n\n\u94a0\u79bb\u5b50\u7535\u6c60\u7684\u5316\u5b66\u6027\u8d28\u4e0e\u9502\u79bb\u5b50\u7535\u6c60\u7684\u5316\u5b66\u6027\u8d28\u6709\u8bb8\u591a\u76f8\u4f3c\u4e4b\u5904\uff0c\u901a\u5e38\u53ef\u4ee5\u4f7f\u7528\u7c7b\u4f3c\u7684\u7535\u8377\u548c\u8d28\u91cf\u4f20\u9012\u3001\u7535\u6781\u52a8\u529b\u5b66\u4ee5\u53ca\u7535\u6781\u9897\u7c92\u63d2\u5c42\u65b9\u7a0b\u6765\u63cf\u8ff0\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();

    model.label("na_ion_battery_1d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
