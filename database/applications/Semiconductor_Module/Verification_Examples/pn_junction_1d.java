/*
 * pn_junction_1d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:58 by COMSOL 6.3.0.290. */
public class pn_junction_1d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("semi", "Semiconductor", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/semi", true);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("semi").prop("ShapeProperty").set("Formulation", "FEM1log");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", -2.5, 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", 2.5, 1);
    model.component("comp1").geom("geom1").run("i1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").runPre("fin");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Va", "bias*sweep", "\u5916\u52a0\u7535\u538b");
    model.param().set("Tl", "300[K]", "\u6676\u683c\u6e29\u5ea6");
    model.param().set("Na", "1e15[1/cm^3]", "\u53d7\u4e3b\u6d53\u5ea6");
    model.param().set("Nd", "1e15[1/cm^3]", "\u65bd\u4e3b\u6d53\u5ea6");
    model.param().set("epsilonr_param", "11.8", "\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570");
    model.param().set("Eg0_param", "1.12[V]", "\u5e26\u9699");
    model.param().set("chi0_param", "4.05[V]", "\u7535\u5b50\u4eb2\u548c\u80fd");
    model.param().set("Ni", "1.25e10[1/cm^3]", "\u672c\u5f81\u8f7d\u6d41\u5b50\u6d53\u5ea6");
    model.param().set("Nc_param", "Ni*exp(Eg0_param*e_const/(2*k_B_const*Tl))", "\u5bfc\u5e26\u6001\u5bc6\u5ea6");
    model.param().set("Nv_param", "Ni*exp(Eg0_param*e_const/(2*k_B_const*Tl))", "\u4ef7\u5e26\u6001\u5bc6\u5ea6");
    model.param().set("taun_param", "1e-6[s]", "\u7535\u5b50\u5bff\u547d");
    model.param().set("taup_param", "taun_param", "\u7a7a\u7a74\u5bff\u547d");
    model.param().set("bias", "-4[V]", "\u5668\u4ef6\u504f\u538b");
    model.param().set("sweep", "1", "\u9010\u6e10\u63d0\u9ad8\u504f\u538b\u7684\u626b\u63cf\u53c2\u6570");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("A1n", "1430[cm^2/(V*s)]", "Kramer \u8fc1\u79fb\u7387\u6a21\u578b\uff0cA1 \u53c2\u6570\uff0c\u7535\u5b50");
    model.component("comp1").variable("var1")
         .set("B1n", "-2.2[1]", "Kramer \u8fc1\u79fb\u7387\u6a21\u578b\uff0cB1 \u53c2\u6570\uff0c\u7535\u5b50");
    model.component("comp1").variable("var1")
         .set("MUln", "A1n*(Tl/300[K])^B1n", "Kramer \u8fc1\u79fb\u7387\u6a21\u578b\uff0cMU1 \u53c2\u6570\uff0c\u7535\u5b50");
    model.component("comp1").variable("var1")
         .set("Ain", "4.61e17[1/(V*s*cm)]", "Kramer \u8fc1\u79fb\u7387\u6a21\u578b\uff0cAi \u53c2\u6570\uff0c\u7535\u5b50");
    model.component("comp1").variable("var1")
         .set("Bin", "1.52e15[1/(K^2*cm^3)]", "Kramer \u8fc1\u79fb\u7387\u6a21\u578b\uff0cBi \u53c2\u6570\uff0c\u7535\u5b50");
    model.component("comp1").variable("var1")
         .set("MUin", "(Ain*(Tl/1[K])^(1.5)/Ntot)/(log(1+Bin*Tl^2/Ntot)-Bin*Tl^2/(Ntot+Bin*Tl^2))", "Kramer \u8fc1\u79fb\u7387\u6a21\u578b\uff0cMUi \u53c2\u6570\uff0c\u7535\u5b50");
    model.component("comp1").variable("var1")
         .set("Xn", "sqrt(6*MUln/MUin)", "Kramer \u8fc1\u79fb\u7387\u6a21\u578b\uff0cX \u53c2\u6570\uff0c\u7535\u5b50");
    model.component("comp1").variable("var1")
         .set("mu_n", "MUln*(1.025/(1+(Xn/1.68)^1.43)-0.025)", "Kramer \u8fc1\u79fb\u7387\u6a21\u578b\uff0c\u7535\u5b50\u8fc1\u79fb\u7387");
    model.component("comp1").variable("var1")
         .set("A1p", "495[cm^2/(V*s)]", "Kramer \u8fc1\u79fb\u7387\u6a21\u578b\uff0cA1 \u53c2\u6570\uff0c\u7a7a\u7a74");
    model.component("comp1").variable("var1")
         .set("B1p", "-2.2[1]", "Kramer \u8fc1\u79fb\u7387\u6a21\u578b\uff0cB1 \u53c2\u6570\uff0c\u7a7a\u7a74");
    model.component("comp1").variable("var1")
         .set("MUlp", "A1p*(Tl/300[K])^B1p", "Kramer \u8fc1\u79fb\u7387\u6a21\u578b\uff0cMU1 \u53c2\u6570\uff0c\u7a7a\u7a74");
    model.component("comp1").variable("var1")
         .set("Aip", "1e17[1/(V*s*cm)]", "Kramer \u8fc1\u79fb\u7387\u6a21\u578b\uff0cAi \u53c2\u6570\uff0c\u7a7a\u7a74");
    model.component("comp1").variable("var1")
         .set("Bip", "6.25e14[1/(K^2*cm^3)]", "Kramer \u8fc1\u79fb\u7387\u6a21\u578b\uff0cBi \u53c2\u6570\uff0c\u7a7a\u7a74");
    model.component("comp1").variable("var1")
         .set("MUip", "(Aip*(Tl/1[K])^(1.5)/Ntot)/(log(1+Bip*Tl^2/Ntot)-Bip*Tl^2/(Ntot+Bip*Tl^2))", "Kramer \u8fc1\u79fb\u7387\u6a21\u578b\uff0cMUi \u53c2\u6570\uff0c\u7a7a\u7a74");
    model.component("comp1").variable("var1")
         .set("Xp", "sqrt(6*MUlp/MUip)", "Kramer \u8fc1\u79fb\u7387\u6a21\u578b\uff0cX \u53c2\u6570\uff0c\u7a7a\u7a74");
    model.component("comp1").variable("var1")
         .set("mu_p", "MUlp*(1.025/(1+(Xp/1.68)^1.43)-0.025)", "Kramer \u8fc1\u79fb\u7387\u6a21\u578b\uff0c\u7a7a\u7a74\u8fc1\u79fb\u7387");
    model.component("comp1").variable("var1")
         .set("Ntot", "semi.Naminus+semi.Ndplus", "\u7535\u79bb\u6742\u8d28\u603b\u6570");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("funcname", "kramer_eq_V");
    model.component("comp1").func("int1")
         .set("table", new String[][]{{"-2.52E-06", "0.00057817"}, 
         {"-2.02E-06", "0.000259338"}, 
         {"-1.53E-06", "-5.95E-05"}, 
         {"-1.06E-06", "-0.000328494"}, 
         {"-9.88E-07", "-0.000456638"}, 
         {"-8.63E-07", "-0.00185503"}, 
         {"-7.30E-07", "-0.00560881"}, 
         {"-6.25E-07", "-0.0116539"}, 
         {"-5.28E-07", "-0.0229524"}, 
         {"-4.59E-07", "-0.0365422"}, 
         {"-4.13E-07", "-0.0483334"}, 
         {"-3.63E-07", "-0.0642288"}, 
         {"-3.18E-07", "-0.0824582"}, 
         {"-2.35E-07", "-0.122414"}, 
         {"-1.87E-07", "-0.150601"}, 
         {"-1.27E-07", "-0.190515"}, 
         {"-5.01E-08", "-0.249193"}, 
         {"8.64E-10", "-0.29553"}, 
         {"6.31E-08", "-0.347742"}, 
         {"1.59E-07", "-0.415821"}, 
         {"2.04E-07", "-0.446927"}, 
         {"2.67E-07", "-0.479824"}, 
         {"3.24E-07", "-0.504513"}, 
         {"3.77E-07", "-0.525098"}, 
         {"4.38E-07", "-0.542185"}, 
         {"4.92E-07", "-0.555161"}, 
         {"5.61E-07", "-0.565824"}, 
         {"6.27E-07", "-0.572969"}, 
         {"7.17E-07", "-0.5784"}, 
         {"8.26E-07", "-0.582111"}, 
         {"9.35E-07", "-0.584067"}, 
         {"1.04E-06", "-0.584266"}, 
         {"1.37E-06", "-0.584857"}, 
         {"1.64E-06", "-0.584763"}, 
         {"1.94E-06", "-0.584725"}, 
         {"2.17E-06", "-0.583968"}, 
         {"2.45E-06", "-0.584488"}});
    model.component("comp1").func("int1").setIndex("argunit", "m", 0);
    model.component("comp1").func("int1").setIndex("fununit", "1/m^3", 0);
    model.component("comp1").func("int1").label("Kramer Eq V");
    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2").set("funcname", "kramer_fwd_V");
    model.component("comp1").func("int2")
         .set("table", new String[][]{{"-2.51296E-06", "1.43E-05"}, 
         {"-2.27792E-06", "0.000199627"}, 
         {"-1.82662E-06", "0.000648789"}, 
         {"-1.45728E-06", "0.000939974"}, 
         {"-9.9082E-07", "0.00121436"}, 
         {"-8.3028E-07", "0.00124757"}, 
         {"-6.8093E-07", "0.00127195"}, 
         {"-5.4231E-07", "0.000914432"}, 
         {"-4.4786E-07", "5.53E-05"}, 
         {"-3.7172E-07", "-0.0010984"}, 
         {"-3.2542E-07", "-0.0022756"}, 
         {"-2.7422E-07", "-0.00438255"}, 
         {"-2.2639E-07", "-0.00677226"}, 
         {"-1.9325E-07", "-0.00936026"}, 
         {"-1.6384E-07", "-0.0119512"}, 
         {"-1.3384E-07", "-0.0150085"}, 
         {"-8.736E-08", "-0.0222541"}, 
         {"-4.331E-08", "-0.0305285"}, 
         {"-1.045E-08", "-0.0388118"}, 
         {"1.667E-08", "-0.0455125"}, 
         {"4.497E-08", "-0.0531459"}, 
         {"7.104E-08", "-0.0590071"}, 
         {"1.0268E-07", "-0.0633702"}, 
         {"1.306E-07", "-0.0677362"}, 
         {"1.5278E-07", "-0.0705195"}, 
         {"1.7869E-07", "-0.0733"}, 
         {"2.0751E-07", "-0.0754246"}, 
         {"2.3272E-07", "-0.0776454"}, 
         {"2.6468E-07", "-0.0793007"}, 
         {"3.0014E-07", "-0.0807665"}, 
         {"3.3922E-07", "-0.0821361"}, 
         {"3.9659E-07", "-0.0832113"}, 
         {"0.000000469", "-0.0843679"}, 
         {"5.5925E-07", "-0.0848569"}, 
         {"6.7176E-07", "-0.085235"}, 
         {"9.033E-07", "-0.0852392"}, 
         {"1.06733E-06", "-0.0850165"}, 
         {"1.39214E-06", "-0.0849471"}, 
         {"1.70925E-06", "-0.0846971"}, 
         {"2.02625E-06", "-0.0843539"}, 
         {"2.33566E-06", "-0.0839232"}, 
         {"2.44735E-06", "-0.0836484"}});
    model.component("comp1").func("int2").setIndex("argunit", "m", 0);
    model.component("comp1").func("int2").setIndex("fununit", "1/m^3", 0);
    model.component("comp1").func("int2").label("Kramer Fwd V");
    model.component("comp1").func().create("int3", "Interpolation");
    model.component("comp1").func("int3").set("funcname", "kramer_rev_V");
    model.component("comp1").func("int3")
         .set("table", new String[][]{{"-2.502E-06", "-1.256E-02"}, 
         {"-2.246E-06", "-5.518E-03"}, 
         {"-2.023E-06", "0.000E+00"}, 
         {"-1.889E-06", "1.446E-03"}, 
         {"-1.687E-06", "-1.736E-02"}, 
         {"-1.537E-06", "-4.787E-02"}, 
         {"-1.406E-06", "-9.788E-02"}, 
         {"-1.258E-06", "-1.892E-01"}, 
         {"-1.090E-06", "-3.262E-01"}, 
         {"-9.171E-07", "-5.144E-01"}, 
         {"-7.467E-07", "-7.447E-01"}, 
         {"-5.512E-07", "-1.067E+00"}, 
         {"-3.661E-07", "-1.418E+00"}, 
         {"-1.671E-07", "-1.862E+00"}, 
         {"1.738E-09", "-2.293E+00"}, 
         {"1.781E-07", "-2.728E+00"}, 
         {"3.378E-07", "-3.099E+00"}, 
         {"5.272E-07", "-3.469E+00"}, 
         {"7.034E-07", "-3.769E+00"}, 
         {"8.744E-07", "-4.018E+00"}, 
         {"1.013E-06", "-4.179E+00"}, 
         {"1.139E-06", "-4.300E+00"}, 
         {"1.292E-06", "-4.419E+00"}, 
         {"1.420E-06", "-4.492E+00"}, 
         {"1.575E-06", "-4.546E+00"}, 
         {"1.698E-06", "-4.568E+00"}, 
         {"1.829E-06", "-4.581E+00"}, 
         {"1.985E-06", "-4.588E+00"}, 
         {"2.164E-06", "-4.589E+00"}, 
         {"2.469E-06", "-4.589E+00"}});
    model.component("comp1").func("int3").setIndex("argunit", "m", 0);
    model.component("comp1").func("int3").setIndex("fununit", "1/m^3", 0);
    model.component("comp1").func("int3").label("Kramer Rev V");
    model.component("comp1").func().create("int4", "Interpolation");
    model.component("comp1").func("int4")
         .set("table", new String[][]{{"-1.02959E-06", "9.60182E+20"}, 
         {"-9.3494E-07", "9.69474E+20"}, 
         {"-8.7867E-07", "9.29789E+20"}, 
         {"-8.0334E-07", "9.05116E+20"}, 
         {"-7.3577E-07", "8.64571E+20"}, 
         {"-6.762E-07", "7.93016E+20"}, 
         {"-6.244E-07", "7.1374E+20"}, 
         {"-5.5847E-07", "5.85998E+20"}, 
         {"-5.0784E-07", "4.73365E+20"}, 
         {"-4.5461E-07", "3.42733E+20"}, 
         {"-4.1999E-07", "2.55285E+20"}, 
         {"-3.8206E-07", "1.81857E+20"}, 
         {"-3.4791E-07", "1.29723E+20"}, 
         {"-3.1822E-07", "8.68388E+19"}, 
         {"-2.9513E-07", "6.35543E+19"}, 
         {"-2.6545E-07", "4.25443E+19"}, 
         {"-2.506E-07", "2.92595E+19"}, 
         {"-2.3174E-07", "2.05352E+19"}, 
         {"-2.0912E-07", "1.43928E+19"}, 
         {"-1.9474E-07", "9.47956E+18"}, 
         {"-1.7212E-07", "6.6441E+18"}, 
         {"-1.5703E-07", "4.6693E+18"}, 
         {"-1.4241E-07", "3.14257E+18"}, 
         {"-1.2449E-07", "2.02279E+18"}, 
         {"-1.0941E-07", "1.42157E+18"}, 
         {"-9.879E-08", "9.37547E+17"}, 
         {"-8.37E-08", "6.58884E+17"}, 
         {"-6.932E-08", "4.33961E+17"}, 
         {"-5.423E-08", "3.04977E+17"}, 
         {"1.4601E-07", "3.94998E+14"}, 
         {"-4.362E-08", "2.01137E+17"}, 
         {"-3.183E-08", "1.478E+17"}, 
         {"-1.768E-08", "9.52632E+16"}, 
         {"-6.12E-09", "6.85041E+16"}, 
         {"4.49E-09", "4.51796E+16"}, 
         {"1.534E-08", "3.0448E+16"}, 
         {"2.949E-08", "1.9625E+16"}, 
         {"4.081E-08", "1.38105E+16"}, 
         {"5.167E-08", "9.30736E+15"}, 
         {"6.299E-08", "6.54978E+15"}, 
         {"6.984E-08", "4.3255E+15"}, 
         {"8.752E-08", "2.72465E+15"}, 
         {"9.531E-08", "1.96194E+15"}, 
         {"1.1322E-07", "1.26285E+15"}, 
         {"1.1701E-07", "8.91088E+14"}, 
         {"1.4269E-07", "5.84534E+14"}, 
         {"1.6533E-07", "2.89474E+14"}, 
         {"1.7642E-07", "1.99351E+14"}, 
         {"1.8727E-07", "1.34349E+14"}, 
         {"1.9812E-07", "9.05426E+13"}, 
         {"2.2098E-07", "6.48474E+13"}, 
         {"2.3513E-07", "4.17967E+13"}, 
         {"2.4645E-07", "2.94132E+13"}, 
         {"2.686E-07", "1.97427E+13"}, 
         {"2.8368E-07", "1.38747E+13"}, 
         {"3.0184E-07", "9.12601E+12"}, 
         {"3.2469E-07", "6.53613E+12"}, 
         {"3.4684E-07", "4.38718E+12"}, 
         {"3.6923E-07", "3.00914E+12"}, 
         {"3.9162E-07", "2.06395E+12"}, 
         {"4.2907E-07", "1.40805E+12"}, 
         {"4.4392E-07", "9.68375E+11"}, 
         {"4.7831E-07", "7.05867E+11"}, 
         {"5.1294E-07", "5.25767E+11"}, 
         {"5.5133E-07", "3.91093E+11"}, 
         {"6.1255E-07", "2.94886E+11"}, 
         {"6.5588E-07", "2.4407E+11"}, 
         {"7.2158E-07", "1.961E+11"}, 
         {"7.9222E-07", "1.75314E+11"}, 
         {"8.5979E-07", "1.6746E+11"}, 
         {"9.0075E-07", "1.58017E+11"}, 
         {"9.8739E-07", "1.53205E+11"}, 
         {"1.05143E-06", "1.49742E+11"}, 
         {"1.18728E-06", "1.45785E+11"}, 
         {"1.30452E-06", "1.46013E+11"}, 
         {"1.41446E-06", "1.4984E+11"}, 
         {"1.55031E-06", "1.4588E+11"}, 
         {"1.68285E-06", "1.48501E+11"}, 
         {"1.80787E-06", "1.51577E+11"}});
    model.component("comp1").func("int4").set("funcname", "kramer_eq_n");
    model.component("comp1").func("int4").setIndex("argunit", "m", 0);
    model.component("comp1").func("int4").setIndex("fununit", "1/m^3", 0);
    model.component("comp1").func("int4").label("Kramer Eq n");
    model.component("comp1").func().create("int5", "Interpolation");
    model.component("comp1").func("int5")
         .set("table", new String[][]{{"-2.44588E-06", "1.58132E+11"}, 
         {"-2.39314E-06", "1.55183E+11"}, 
         {"-2.34393E-06", "1.55827E+11"}, 
         {"-2.27966E-06", "1.55635E+11"}, 
         {"-2.23799E-06", "1.56702E+11"}, 
         {"-2.18525E-06", "1.5378E+11"}, 
         {"-2.14358E-06", "1.54834E+11"}, 
         {"-2.09038E-06", "1.58662E+11"}, 
         {"-2.04141E-06", "1.55912E+11"}, 
         {"-1.94323E-06", "1.53847E+11"}, 
         {"-1.83752E-06", "1.514E+11"}, 
         {"-1.73535E-06", "1.52455E+11"}, 
         {"-1.64094E-06", "1.50638E+11"}, 
         {"-1.535E-06", "1.51483E+11"}, 
         {"-1.38362E-06", "1.53172E+11"}, 
         {"-1.28167E-06", "1.50939E+11"}, 
         {"-1.1795E-06", "1.5199E+11"}, 
         {"-1.07732E-06", "1.53049E+11"}, 
         {"-9.7868E-07", "1.57696E+11"}, 
         {"-8.765E-07", "1.58795E+11"}, 
         {"-7.8116E-07", "1.71078E+11"}, 
         {"-6.8134E-07", "1.964E+11"}, 
         {"-5.8813E-07", "2.46503E+11"}, 
         {"-5.0504E-07", "3.4611E+11"}, 
         {"-4.3985E-07", "5.33447E+11"}, 
         {"-3.8879E-07", "9.01302E+11"}, 
         {"-3.415E-07", "1.52487E+12"}, 
         {"-2.9774E-07", "2.63981E+12"}, 
         {"-2.5093E-07", "6.05342E+12"}, 
         {"-2.1824E-07", "1.07518E+13"}, 
         {"-1.9708E-07", "1.8764E+13"}, 
         {"-1.7897E-07", "2.47217E+13"}, 
         {"-1.5381E-07", "4.4028E+13"}, 
         {"-1.0372E-07", "1.36659E+14"}, 
         {"-8.61E-08", "2.44038E+14"}, 
         {"-7.528E-08", "3.29435E+14"}, 
         {"-5.766E-08", "5.88285E+14"}, 
         {"-4.284E-08", "8.10417E+14"}, 
         {"-2.569E-08", "1.38594E+15"}, 
         {"-8.06E-09", "2.47492E+15"}, 
         {"1.333E-08", "4.41363E+15"}, 
         {"4.154E-08", "1.0412E+16"}, 
         {"6.621E-08", "2.51334E+16"}, 
         {"8.76E-08", "4.48214E+16"}, 
         {"9.769E-08", "8.0255E+16"}, 
         {"1.2285E-07", "1.4293E+17"}, 
         {"1.4071E-07", "2.60815E+17"}, 
         {"1.6164E-07", "4.45434E+17"}, 
         {"1.8326E-07", "8.11725E+17"}, 
         {"2.0442E-07", "1.41662E+18"}, 
         {"2.2558E-07", "2.47226E+18"}, 
         {"2.5497E-07", "4.59139E+18"}, 
         {"2.799E-07", "8.00208E+18"}, 
         {"3.0836E-07", "1.36297E+19"}, 
         {"3.4128E-07", "2.47377E+19"}, 
         {"3.7398E-07", "4.39382E+19"}, 
         {"4.102E-07", "7.62692E+19"}, 
         {"4.4973E-07", "1.26616E+20"}, 
         {"5.0362E-07", "1.95938E+20"}, 
         {"5.6291E-07", "3.52293E+20"}, 
         {"6.314E-07", "5.19295E+20"}, 
         {"7.2156E-07", "6.96393E+20"}, 
         {"8.136E-07", "7.84474E+20"}, 
         {"9.1695E-07", "8.80138E+20"}, 
         {"1.0276E-06", "9.63748E+20"}});
    model.component("comp1").func("int5").set("funcname", "kramer_eq_p");
    model.component("comp1").func("int5").setIndex("argunit", "m", 0);
    model.component("comp1").func("int5").setIndex("fununit", "1/m^3", 0);
    model.component("comp1").func("int5").label("Kramer Eq p");
    model.component("comp1").func().create("int6", "Interpolation");
    model.component("comp1").func("int6").set("funcname", "kramer_fwd_n");
    model.component("comp1").func("int6")
         .set("table", new String[][]{{"-2.49625E-06", "1.01752E+21"}, 
         {"-1.59996E-06", "1.03924E+21"}, 
         {"-7.89954E-07", "1.04248E+21"}, 
         {"-6.39881E-07", "1.08294E+21"}, 
         {"-5.01205E-07", "1.04176E+21"}, 
         {"-3.70079E-07", "9.77128E+20"}, 
         {"-2.87793E-07", "8.74056E+20"}, 
         {"-2.31703E-07", "8.04369E+20"}, 
         {"-1.60766E-07", "6.83819E+20"}, 
         {"-1.12474E-07", "5.38891E+20"}, 
         {"-5.67843E-08", "4.02909E+20"}, 
         {"-8.64291E-09", "2.93723E+20"}, 
         {"3.18499E-08", "1.98217E+20"}, 
         {"6.85934E-08", "1.33812E+20"}, 
         {"1.05487E-07", "9.76508E+19"}, 
         {"1.57328E-07", "6.93394E+19"}, 
         {"2.13168E-07", "5.60422E+19"}, 
         {"2.80256E-07", "4.52480E+19"}, 
         {"3.54942E-07", "3.84534E+19"}, 
         {"4.11032E-07", "3.53876E+19"}, 
         {"4.82120E-07", "3.25211E+19"}, 
         {"5.34561E-07", "3.15344E+19"}, 
         {"6.09447E-07", "2.97321E+19"}, 
         {"6.69387E-07", "2.88100E+19"}, 
         {"7.44273E-07", "2.71634E+19"}, 
         {"8.07962E-07", "2.63119E+19"}, 
         {"8.86598E-07", "2.47995E+19"}, 
         {"9.42788E-07", "2.40387E+19"}, 
         {"1.02897E-06", "2.32368E+19"}, 
         {"1.15630E-06", "2.12440E+19"}, 
         {"1.29108E-06", "1.89112E+19"}, 
         {"1.41835E-06", "1.68462E+19"}, 
         {"1.54563E-06", "1.50067E+19"}, 
         {"1.68781E-06", "1.26740E+19"}, 
         {"1.82253E-06", "1.09931E+19"}, 
         {"1.94581E-06", "8.60346E+18"}, 
         {"2.07289E-06", "6.90799E+18"}, 
         {"2.12883E-06", "5.88083E+18"}, 
         {"2.19597E-06", "4.87303E+18"}, 
         {"2.25186E-06", "4.04213E+18"}, 
         {"2.30755E-06", "3.02216E+18"}, 
         {"2.36324E-06", "2.25956E+18"}, 
         {"2.40368E-06", "1.48576E+18"}, 
         {"2.43677E-06", "1.05683E+18"}, 
         {"2.44617E-06", "4.03957E+17"}, 
         {"2.44692E-06", "5.96318E+17"}, 
         {"2.44902E-06", "2.53054E+17"}, 
         {"2.45587E-06", "2.57309E+16"}, 
         {"2.46542E-06", "1.51616E+15"}, 
         {"2.47327E-06", "3.69526E+13"}});
    model.component("comp1").func("int6").setIndex("argunit", "m", 0);
    model.component("comp1").func("int6").setIndex("fununit", "1/m^3", 0);
    model.component("comp1").func("int6").label("Kramer Fwd n");
    model.component("comp1").func().create("int7", "Interpolation");
    model.component("comp1").func("int7").set("funcname", "kramer_fwd_p");
    model.component("comp1").func("int7")
         .set("table", new String[][]{{"-2.489600E-06", "1.594950E+13"}, 
         {"-2.476000E-06", "3.785680E+14"}, 
         {"-2.465010E-06", "2.329050E+15"}, 
         {"-2.459010E-06", "5.252000E+16"}, 
         {"-2.446310E-06", "7.811790E+17"}, 
         {"-2.426910E-06", "1.092920E+18"}, 
         {"-2.407620E-06", "1.451700E+18"}, 
         {"-2.373270E-06", "1.976230E+18"}, 
         {"-2.338980E-06", "2.621340E+18"}, 
         {"-2.293640E-06", "3.130780E+18"}, 
         {"-2.251940E-06", "3.939890E+18"}, 
         {"-2.202900E-06", "4.583390E+18"}, 
         {"-2.153810E-06", "5.472250E+18"}, 
         {"-2.112420E-06", "5.893060E+18"}, 
         {"-1.988040E-06", "8.165270E+18"}, 
         {"-1.867710E-06", "9.684840E+18"}, 
         {"-1.724790E-06", "1.207440E+19"}, 
         {"-1.566970E-06", "1.427200E+19"}, 
         {"-1.424140E-06", "1.689290E+19"}, 
         {"-1.225130E-06", "1.938180E+19"}, 
         {"-9.735210E-07", "2.330930E+19"}, 
         {"-7.257650E-07", "2.662340E+19"}, 
         {"-5.192020E-07", "3.132770E+19"}, 
         {"-3.727280E-07", "3.519210E+19"}, 
         {"-2.711960E-07", "4.074210E+19"}, 
         {"-1.770630E-07", "4.971580E+19"}, 
         {"-9.382780E-08", "7.283330E+19"}, 
         {"-1.799120E-08", "1.124650E+20"}, 
         {"4.329790E-08", "2.196820E+20"}, 
         {"1.042370E-07", "3.577970E+20"}, 
         {"1.801240E-07", "5.670250E+20"}, 
         {"2.669580E-07", "7.681720E+20"}, 
         {"3.647910E-07", "9.130260E+20"}, 
         {"4.700220E-07", "1.029570E+21"}, 
         {"6.237950E-07", "1.041760E+21"}, 
         {"8.713010E-07", "1.045010E+21"}, 
         {"1.167550E-06", "1.043560E+21"}, 
         {"1.471340E-06", "1.068790E+21"}, 
         {"1.936310E-06", "1.050810E+21"}, 
         {"2.438770E-06", "1.029570E+21"}, 
         {"2.502460E-06", "9.972920E+20"}});
    model.component("comp1").func("int7").setIndex("argunit", "m", 0);
    model.component("comp1").func("int7").setIndex("fununit", "1/m^3", 0);
    model.component("comp1").func("int7").label("Kramer Fwd p");
    model.component("comp1").func().create("int8", "Interpolation");
    model.component("comp1").func("int8").set("funcname", "kramer_rev_n");
    model.component("comp1").func("int8")
         .set("table", new String[][]{{"-2.49E-06", "1.02E+21"}, 
         {"-2.38E-06", "1.01E+21"}, 
         {"-2.13E-06", "1.07E+21"}, 
         {"-1.98E-06", "1.04E+21"}, 
         {"-1.86E-06", "9.42E+20"}, 
         {"-1.78E-06", "8.49E+20"}, 
         {"-1.72E-06", "6.54E+20"}, 
         {"-1.66E-06", "5.04E+20"}, 
         {"-1.62E-06", "3.98E+20"}, 
         {"-1.56E-06", "2.48E+20"}, 
         {"-1.52E-06", "1.68E+20"}, 
         {"-1.45E-06", "6.85E+19"}, 
         {"-1.39E-06", "2.54E+19"}, 
         {"-1.34E-06", "9.41E+18"}, 
         {"-1.29E-06", "3.27E+18"}, 
         {"-1.25E-06", "1.14E+18"}, 
         {"-1.21E-06", "4.08E+17"}, 
         {"-1.18E-06", "1.37E+17"}, 
         {"-1.11E-06", "1.61E+16"}, 
         {"-1.03E-06", "9.62E+14"}, 
         {"-9.50E-07", "2.39E+13"}, 
         {"-8.76E-07", "9.94E+11"}, 
         {"-8.49E-07", "3.05E+11"}, 
         {"-8.29E-07", "1.77E+11"}, 
         {"-8.05E-07", "1.13E+11"}, 
         {"-7.58E-07", "7.67E+10"}, 
         {"-7.01E-07", "6.46E+10"}, 
         {"-6.30E-07", "5.81E+10"}, 
         {"-4.37E-07", "4.47E+10"}, 
         {"-3.05E-07", "3.71E+10"}, 
         {"-9.72E-08", "2.86E+10"}, 
         {"-2.92E-08", "2.57E+10"}, 
         {"3.88E-08", "2.31E+10"}, 
         {"3.10E-07", "2.10E+10"}, 
         {"4.49E-07", "2.04E+10"}, 
         {"5.85E-07", "1.91E+10"}, 
         {"8.52E-07", "1.59E+10"}, 
         {"9.77E-07", "1.40E+10"}, 
         {"1.03E-06", "1.18E+10"}, 
         {"1.10E-06", "1.03E+10"}, 
         {"1.16E-06", "8.68E+09"}, 
         {"1.23E-06", "7.57E+09"}, 
         {"1.29E-06", "7.91E+09"}, 
         {"1.36E-06", "8.79E+09"}, 
         {"1.43E-06", "1.01E+10"}, 
         {"1.62E-06", "1.81E+10"}, 
         {"1.74E-06", "2.85E+10"}, 
         {"1.87E-06", "4.35E+10"}, 
         {"1.99E-06", "6.24E+10"}, 
         {"2.12E-06", "8.71E+10"}, 
         {"2.26E-06", "1.14E+11"}, 
         {"2.39E-06", "1.45E+11"}, 
         {"2.50E-06", "1.73E+11"}});
    model.component("comp1").func("int8").setIndex("argunit", "m", 0);
    model.component("comp1").func("int8").setIndex("fununit", "1/m^3", 0);
    model.component("comp1").func("int8").label("Kramer Rev n");
    model.component("comp1").func().create("int9", "Interpolation");
    model.component("comp1").func("int9")
         .set("table", new String[][]{{"-2.50E-06", "1.53E+11"}, 
         {"-2.41E-06", "1.47E+11"}, 
         {"-2.29E-06", "1.15E+11"}, 
         {"-2.15E-06", "9.27E+10"}, 
         {"-2.01E-06", "6.83E+10"}, 
         {"-1.87E-06", "4.60E+10"}, 
         {"-1.73E-06", "2.91E+10"}, 
         {"-1.58E-06", "1.74E+10"}, 
         {"-1.45E-06", "1.06E+10"}, 
         {"-1.35E-06", "8.53E+09"}, 
         {"-1.30E-06", "8.11E+09"}, 
         {"-1.25E-06", "7.48E+09"}, 
         {"-1.21E-06", "8.03E+09"}, 
         {"-1.16E-06", "1.00E+10"}, 
         {"-1.08E-06", "1.71E+10"}, 
         {"-9.95E-07", "2.51E+10"}, 
         {"-9.02E-07", "3.27E+10"}, 
         {"-7.94E-07", "3.89E+10"}, 
         {"-6.44E-07", "4.54E+10"}, 
         {"-3.93E-07", "5.58E+10"}, 
         {"-4.35E-08", "6.39E+10"}, 
         {"1.30E-08", "6.27E+10"}, 
         {"1.14E-07", "7.69E+10"}, 
         {"2.07E-07", "8.86E+10"}, 
         {"4.10E-07", "1.14E+11"}, 
         {"7.05E-07", "1.76E+11"}, 
         {"7.61E-07", "1.95E+11"}, 
         {"8.06E-07", "2.09E+11"}, 
         {"8.55E-07", "2.38E+11"}, 
         {"8.80E-07", "3.45E+11"}, 
         {"8.97E-07", "5.00E+11"}, 
         {"9.68E-07", "5.97E+12"}, 
         {"1.01E-06", "4.23E+13"}, 
         {"1.05E-06", "2.20E+14"}, 
         {"1.09E-06", "1.12E+15"}, 
         {"1.14E-06", "8.67E+15"}, 
         {"1.23E-06", "1.37E+17"}, 
         {"1.30E-06", "1.03E+18"}, 
         {"1.36E-06", "4.94E+18"}, 
         {"1.42E-06", "1.59E+19"}, 
         {"1.51E-06", "8.15E+19"}, 
         {"1.58E-06", "2.06E+20"}, 
         {"1.65E-06", "3.51E+20"}, 
         {"1.73E-06", "5.99E+20"}, 
         {"1.83E-06", "8.30E+20"}, 
         {"1.92E-06", "1.02E+21"}, 
         {"2.07E-06", "1.08E+21"}, 
         {"2.22E-06", "1.15E+21"}, 
         {"2.50E-06", "1.19E+21"}});
    model.component("comp1").func("int9").set("funcname", "kramer_rev_p");
    model.component("comp1").func("int9").setIndex("argunit", "m", 0);
    model.component("comp1").func("int9").setIndex("fununit", "1/m^3", 0);
    model.component("comp1").func("int9").label("Kramer Rev p");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(1);

    model.component("comp1").physics("semi").feature("smm1").set("minput_temperature", "Tl");
    model.component("comp1").physics("semi").feature("smm1").set("mun_mat", "userdef");
    model.component("comp1").physics("semi").feature("smm1").set("mun", "mu_n");
    model.component("comp1").physics("semi").feature("smm1").set("mup_mat", "userdef");
    model.component("comp1").physics("semi").feature("smm1").set("mup", "mu_p");
    model.component("comp1").physics("semi").create("tar1", "TrapAssistedRecombination", 1);
    model.component("comp1").physics("semi").feature("tar1").selection().all();
    model.component("comp1").physics("semi").create("adm1", "AnalyticDopingModel", 1);
    model.component("comp1").physics("semi").feature("adm1").selection().set(1);
    model.component("comp1").physics("semi").feature("adm1").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm1").set("NDc", "Nd");
    model.component("comp1").physics("semi").create("adm2", "AnalyticDopingModel", 1);
    model.component("comp1").physics("semi").feature("adm2").selection().set(2);
    model.component("comp1").physics("semi").feature("adm2").set("NAc", "Na");
    model.component("comp1").physics("semi").create("mc1", "MetalContact", 0);
    model.component("comp1").physics("semi").feature("mc1").selection().set(1);
    model.component("comp1").physics("semi").create("mc2", "MetalContact", 0);
    model.component("comp1").physics("semi").feature("mc2").set("V0", "Va");
    model.component("comp1").physics("semi").feature("mc2").selection().set(3);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"epsilonr_param"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("SemicondMaterial", "SemicondMaterial", "Semiconductor_material");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("Eg0", new String[]{"Eg0_param"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("chi0", new String[]{"chi0_param"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("Nc", new String[]{"Nc_param"});
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").set("Nv", new String[]{"Nv_param"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("SRH", "SRH", "Shockley-Read-Hall_recombination");
    model.component("comp1").material("mat1").propertyGroup("SRH").set("taun", new String[]{"taun_param"});
    model.component("comp1").material("mat1").propertyGroup("SRH").set("taup", new String[]{"taup_param"});

    model.component("comp1").physics().copy("semi2", "semi");
    model.component("comp1").physics("semi2").prop("ShapeProperty").set("Formulation", "FVM");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 500);

    model.study("std1").label("\u7814\u7a76 1 - \u6709\u9650\u5143\u5bf9\u6570\u5f62\u5f0f");
    model.study("std1").feature("stat").setSolveFor("/physics/semi2", false);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").set("sweeptype", "filled");
    model.study("std1").feature("stat").setIndex("pname", "Va", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "V", 0);
    model.study("std1").feature("stat").setIndex("pname", "Va", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "V", 0);
    model.study("std1").feature("stat").setIndex("pname", "bias", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "-4 0 0.5", 0);
    model.study("std1").feature("stat").setIndex("pname", "Va", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);

    return model;
  }

  public static Model run2(Model model) {
    model.study("std1").feature("stat").setIndex("punit", "V", 1);
    model.study("std1").feature("stat").setIndex("pname", "Va", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "V", 1);
    model.study("std1").feature("stat").setIndex("pname", "sweep", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "0 1", 1);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").label("\u80fd\u7ea7 (semi)");
    model.result("pg1").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "Energy Diagram");
    model.result("pg1").set("ylabel", "Energy (eV)");
    model.result("pg1").feature().create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").label("\u5bfc\u5e26\u80fd\u7ea7");
    model.result("pg1").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr1").set("expr", "semi.Ec_e");
    model.result("pg1").feature("lngr1").set("unit", "eV");
    model.result("pg1").feature("lngr1").set("linecolor", "blue");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("legendmethod", "manual");
    model.result("pg1").feature("lngr1").set("legends", new String[]{"Ec"});
    model.result("pg1").feature("lngr1").set("resolution", "norefine");
    model.result("pg1").feature("lngr1").set("smooth", "everywhere");
    model.result("pg1").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr1").set("data", "parent");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1, 2);
    model.result("pg1").feature().create("lngr2", "LineGraph");
    model.result("pg1").feature("lngr2").label("\u7535\u5b50\u51c6\u8d39\u7c73\u80fd\u7ea7");
    model.result("pg1").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr2").set("expr", "semi.Efn_e");
    model.result("pg1").feature("lngr2").set("unit", "eV");
    model.result("pg1").feature("lngr2").set("linestyle", "dashed");
    model.result("pg1").feature("lngr2").set("linecolor", "black");
    model.result("pg1").feature("lngr2").set("legend", true);
    model.result("pg1").feature("lngr2").set("legendmethod", "manual");
    model.result("pg1").feature("lngr2").set("legends", new String[]{"Efn"});
    model.result("pg1").feature("lngr2").set("resolution", "norefine");
    model.result("pg1").feature("lngr2").set("smooth", "everywhere");
    model.result("pg1").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr2").set("data", "parent");
    model.result("pg1").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr2").selection().set(1, 2);
    model.result("pg1").feature().create("lngr3", "LineGraph");
    model.result("pg1").feature("lngr3").label("\u7a7a\u7a74\u51c6\u8d39\u7c73\u80fd\u7ea7");
    model.result("pg1").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr3").set("expr", "semi.Efp_e");
    model.result("pg1").feature("lngr3").set("unit", "eV");
    model.result("pg1").feature("lngr3").set("linestyle", "dotted");
    model.result("pg1").feature("lngr3").set("linecolor", "black");
    model.result("pg1").feature("lngr3").set("legend", true);
    model.result("pg1").feature("lngr3").set("legendmethod", "manual");
    model.result("pg1").feature("lngr3").set("legends", new String[]{"Efp"});
    model.result("pg1").feature("lngr3").set("resolution", "norefine");
    model.result("pg1").feature("lngr3").set("smooth", "everywhere");
    model.result("pg1").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr3").set("data", "parent");
    model.result("pg1").feature("lngr3").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr3").selection().set(1, 2);
    model.result("pg1").feature().create("lngr4", "LineGraph");
    model.result("pg1").feature("lngr4").label("\u4ef7\u5e26\u80fd\u7ea7");
    model.result("pg1").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr4").set("expr", "semi.Ev_e");
    model.result("pg1").feature("lngr4").set("unit", "eV");
    model.result("pg1").feature("lngr4").set("linecolor", "green");
    model.result("pg1").feature("lngr4").set("legend", true);
    model.result("pg1").feature("lngr4").set("legendmethod", "manual");
    model.result("pg1").feature("lngr4").set("legends", new String[]{"Ev"});
    model.result("pg1").feature("lngr4").set("resolution", "norefine");
    model.result("pg1").feature("lngr4").set("smooth", "everywhere");
    model.result("pg1").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg1").feature("lngr4").set("data", "parent");
    model.result("pg1").feature("lngr4").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr4").selection().set(1, 2);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").label("\u8f7d\u6d41\u5b50\u6d53\u5ea6 (semi)");
    model.result("pg2").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg2").set("ylabel", "Carrier concentration (1/cm^3)");
    model.result("pg2").set("ylog", true);
    model.result("pg2").feature().create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").label("\u7535\u5b50\u6d53\u5ea6");
    model.result("pg2").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr1").set("expr", "semi.N");
    model.result("pg2").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("legendmethod", "manual");
    model.result("pg2").feature("lngr1").set("legends", new String[]{"electrons"});
    model.result("pg2").feature("lngr1").set("resolution", "norefine");
    model.result("pg2").feature("lngr1").set("smooth", "everywhere");
    model.result("pg2").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr1").set("data", "parent");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1, 2);
    model.result("pg2").feature().create("lngr2", "LineGraph");
    model.result("pg2").feature("lngr2").label("\u7a7a\u7a74\u6d53\u5ea6");
    model.result("pg2").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr2").set("expr", "semi.P");
    model.result("pg2").feature("lngr2").set("unit", "1/cm^3");
    model.result("pg2").feature("lngr2").set("legend", true);
    model.result("pg2").feature("lngr2").set("legendmethod", "manual");
    model.result("pg2").feature("lngr2").set("legends", new String[]{"holes"});
    model.result("pg2").feature("lngr2").set("resolution", "norefine");
    model.result("pg2").feature("lngr2").set("smooth", "everywhere");
    model.result("pg2").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg2").feature("lngr2").set("data", "parent");
    model.result("pg2").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr2").selection().set(1, 2);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("\u7535\u52bf (semi)");
    model.result("pg3").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg3").set("ylabel", "Electric Potential (V)");
    model.result("pg3").feature().create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg3").feature("lngr1").set("expr", "V");
    model.result("pg3").feature("lngr1").set("resolution", "norefine");
    model.result("pg3").feature("lngr1").set("smooth", "everywhere");
    model.result("pg3").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg3").feature("lngr1").set("data", "parent");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1, 2);
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").create("lngr2", "LineGraph");
    model.result("pg4").feature("lngr1").selection().all();
    model.result("pg4").feature("lngr1").set("xdataexpr", "X");
    model.result("pg4").feature("lngr1").set("expr", "semi.Nnetdop");
    model.result("pg4").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg4").feature("lngr1").set("linecolor", "red");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "manual");
    model.result("pg4").feature("lngr1").set("legends", new String[]{"P \u578b (\u7ea2\u8272)"});
    model.result("pg4").feature("lngr1").feature().create("filt1", "LineGraphFilter");
    model.result("pg4").feature("lngr1").feature("filt1").set("expr", "semi.Na-semi.Nd > 1[1/cm^3]");
    model.result("pg4").feature("lngr2").selection().all();
    model.result("pg4").feature("lngr2").set("xdataexpr", "X");
    model.result("pg4").feature("lngr2").set("expr", "semi.Nnetdop");
    model.result("pg4").feature("lngr2").set("unit", "1/cm^3");
    model.result("pg4").feature("lngr2").set("linecolor", "blue");
    model.result("pg4").feature("lngr2").set("legend", true);
    model.result("pg4").feature("lngr2").set("legendmethod", "manual");
    model.result("pg4").feature("lngr2").set("legends", new String[]{"N \u578b (\u84dd\u8272)"});
    model.result("pg4").feature("lngr2").feature().create("filt1", "LineGraphFilter");
    model.result("pg4").feature("lngr2").feature("filt1").set("expr", "semi.Nd-semi.Na > 1[1/cm^3]");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("legendpos", "uppermiddle");
    model.result("pg4")
         .set("ylabel", "\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 \\vert N<sub>d</sub> - N<sub>a</sub>\\vert, 1/cm<sup>3</sup>");
    model.result("pg4").set("ylog", true);
    model.result("pg4").feature("lngr1").label("P \u578b");
    model.result("pg4").feature("lngr2").label("N \u578b");
    model.result("pg4").label("\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 (semi)");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevelinput", "first", 1);
    model.result("pg1").setIndex("looplevelinput", "last", 0);
    model.result("pg1").run();
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").setIndex("legends", "Ec FE", 0);
    model.result("pg1").run();
    model.result("pg1").feature("lngr2").set("xdata", "expr");
    model.result("pg1").feature("lngr2").set("xdataexpr", "x");
    model.result("pg1").feature("lngr2").setIndex("legends", "Efn FE", 0);
    model.result("pg1").feature("lngr2").set("linestyle", "solid");
    model.result("pg1").feature("lngr2").set("linecolor", "gray");
    model.result("pg1").run();
    model.result("pg1").feature("lngr3").set("xdata", "expr");
    model.result("pg1").feature("lngr3").set("xdataexpr", "x");
    model.result("pg1").feature("lngr3").set("linestyle", "solid");
    model.result("pg1").feature("lngr3").setIndex("legends", "Efp FE", 0);
    model.result("pg1").run();
    model.result("pg1").feature("lngr4").set("xdata", "expr");
    model.result("pg1").feature("lngr4").set("xdataexpr", "x");
    model.result("pg1").feature("lngr4").setIndex("legends", "Ev FE", 0);
    model.result("pg1").run();
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").label("\u80fd\u7ea7\u53cd\u5411\u504f\u538b");
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").label("\u8f7d\u6d41\u5b50\u6d53\u5ea6\u53cd\u5411\u504f\u538b");
    model.result("pg2").setIndex("looplevelinput", "first", 1);
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").run();
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").setIndex("legends", "\u7535\u5b50 FE", 0);
    model.result("pg2").run();
    model.result("pg2").feature("lngr2").set("xdata", "expr");
    model.result("pg2").feature("lngr2").set("xdataexpr", "x");
    model.result("pg2").feature("lngr2").setIndex("legends", "\u7a7a\u7a74 FE", 0);
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").set("expr", "V-intop1(V)");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("legendmethod", "manual");
    model.result("pg3").feature("lngr1").setIndex("legends", "Rev FE", 0);
    model.result("pg3").feature("lngr1").setIndex("legends", "Eq FE", 1);
    model.result("pg3").feature("lngr1").setIndex("legends", "Fwd FE", 2);
    model.result("pg3").run();
    model.result("pg3").set("legendpos", "lowerleft");
    model.result("pg3").setIndex("looplevelinput", "last", 0);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevelinput", "first", 1);
    model.result("pg4").setIndex("looplevelinput", "first", 0);
    model.result("pg4").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std2").feature("stat").setSolveFor("/physics/semi2", true);
    model.study("std2").label("\u7814\u7a76 2 - \u6709\u9650\u4f53\u79ef");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("stat").setSolveFor("/physics/semi", false);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "Va", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "V", 0);
    model.study("std2").feature("stat").setIndex("pname", "Va", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "V", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "-4 0 0.5", 0);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg3").run();
    model.result("pg3").feature().duplicate("lngr2", "lngr1");
    model.result("pg3").run();
    model.result("pg3").feature("lngr2").set("data", "dset2");
    model.result("pg3").feature("lngr2").set("expr", "V2-intop1(V2)");
    model.result("pg3").feature("lngr2").set("linestyle", "dashed");
    model.result("pg3").feature("lngr2").setIndex("legends", "Rev FV", 0);
    model.result("pg3").feature("lngr2").setIndex("legends", "Eq FV", 1);
    model.result("pg3").feature("lngr2").setIndex("legends", "Fwd FV", 2);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("lngr3", "lngr1");
    model.result("pg3").run();
    model.result("pg3").feature("lngr3").set("expr", "kramer_rev_V(x)");
    model.result("pg3").feature("lngr3").set("data", "dset1");
    model.result("pg3").feature("lngr3").setIndex("looplevelinput", "first", 1);
    model.result("pg3").feature("lngr3").setIndex("looplevelinput", "last", 0);
    model.result("pg3").feature("lngr3").set("linestyle", "dotted");
    model.result("pg3").feature("lngr3").set("linemarker", "cycle");
    model.result("pg3").feature("lngr3").setIndex("legends", "Rev Kramer", 0);
    model.result("pg3").feature().duplicate("lngr4", "lngr3");
    model.result("pg3").run();
    model.result("pg3").feature("lngr4").set("expr", "kramer_eq_V(x)");
    model.result("pg3").feature("lngr4").setIndex("legends", "Eq Kramer", 0);
    model.result("pg3").feature().duplicate("lngr5", "lngr4");
    model.result("pg3").run();
    model.result("pg3").feature("lngr5").set("expr", "kramer_fwd_V(x)");
    model.result("pg3").feature("lngr5").setIndex("legends", "Fwd Kramer", 0);
    model.result("pg3").run();
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("lngr5", "lngr1");
    model.result("pg1").feature().duplicate("lngr6", "lngr2");
    model.result("pg1").feature().duplicate("lngr7", "lngr3");
    model.result("pg1").feature().duplicate("lngr8", "lngr4");
    model.result("pg1").run();
    model.result("pg1").feature("lngr5").set("data", "dset2");
    model.result("pg1").feature("lngr5").setIndex("looplevelinput", "first", 0);
    model.result("pg1").feature("lngr5").set("expr", "semi2.Ec_e");
    model.result("pg1").feature("lngr5").set("linestyle", "dashed");
    model.result("pg1").feature("lngr5").set("linecolor", "cyan");
    model.result("pg1").feature("lngr5").setIndex("legends", "Ec FV", 0);
    model.result("pg1").run();
    model.result("pg1").feature("lngr6").set("data", "dset2");
    model.result("pg1").feature("lngr6").setIndex("looplevelinput", "first", 0);
    model.result("pg1").feature("lngr6").set("expr", "semi2.Efn_e");
    model.result("pg1").feature("lngr6").set("linestyle", "dashed");
    model.result("pg1").feature("lngr6").set("linecolor", "green");
    model.result("pg1").feature("lngr6").setIndex("legends", "Efn FV", 0);
    model.result("pg1").run();
    model.result("pg1").feature("lngr7").set("data", "dset2");
    model.result("pg1").feature("lngr7").setIndex("looplevelinput", "first", 0);
    model.result("pg1").feature("lngr7").set("expr", "semi2.Efp_e");
    model.result("pg1").feature("lngr7").set("linestyle", "dashed");
    model.result("pg1").feature("lngr7").set("linecolor", "magenta");
    model.result("pg1").feature("lngr7").setIndex("legends", "Efp FV", 0);
    model.result("pg1").run();
    model.result("pg1").feature("lngr8").set("data", "dset2");
    model.result("pg1").feature("lngr8").setIndex("looplevelinput", "first", 0);
    model.result("pg1").feature("lngr8").set("expr", "semi2.Ev_e");
    model.result("pg1").feature("lngr8").set("linestyle", "dashed");
    model.result("pg1").feature("lngr8").set("linecolor", "red");
    model.result("pg1").feature("lngr8").setIndex("legends", "Ev FV", 0);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("lngr3", "lngr1");
    model.result("pg2").feature().duplicate("lngr4", "lngr2");
    model.result("pg2").run();
    model.result("pg2").feature("lngr3").set("data", "dset2");
    model.result("pg2").feature("lngr3").setIndex("looplevelinput", "first", 0);
    model.result("pg2").feature("lngr3").set("expr", "semi2.N");
    model.result("pg2").feature("lngr3").set("linestyle", "dashed");
    model.result("pg2").feature("lngr3").setIndex("legends", "\u7535\u5b50 FV", 0);
    model.result("pg2").run();
    model.result("pg2").feature("lngr4").set("data", "dset2");
    model.result("pg2").feature("lngr4").setIndex("looplevelinput", "first", 0);
    model.result("pg2").feature("lngr4").set("expr", "semi2.P");
    model.result("pg2").feature("lngr4").set("linestyle", "dashed");
    model.result("pg2").feature("lngr4").setIndex("legends", "\u7a7a\u7a74 FV", 0);
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("lngr5", "lngr3");
    model.result("pg2").feature().duplicate("lngr6", "lngr4");
    model.result("pg2").run();
    model.result("pg2").feature("lngr5").set("expr", "kramer_rev_n(x)");
    model.result("pg2").feature("lngr5").set("linestyle", "dotted");
    model.result("pg2").feature("lngr5").set("linemarker", "cycle");
    model.result("pg2").feature("lngr5").setIndex("legends", "\u7535\u5b50 Kramer", 0);
    model.result("pg2").run();
    model.result("pg2").feature("lngr6").set("expr", "kramer_rev_p(x)");
    model.result("pg2").feature("lngr6").set("linestyle", "dotted");
    model.result("pg2").feature("lngr6").set("linemarker", "cycle");
    model.result("pg2").feature("lngr6").setIndex("legends", "\u7a7a\u7a74 Kramer", 0);
    model.result("pg2").run();
    model.result("pg1").run();
    model.result().duplicate("pg5", "pg1");
    model.result().duplicate("pg6", "pg2");
    model.result("pg5").run();
    model.result().duplicate("pg7", "pg5");
    model.result().duplicate("pg8", "pg6");
    model.result("pg7").run();
    model.result("pg5").run();
    model.result("pg5").label("\u80fd\u7ea7\u5e73\u8861");
    model.result("pg5").setIndex("looplevelinput", "manual", 0);
    model.result("pg5").setIndex("looplevel", new int[]{1}, 0);
    model.result("pg5").run();
    model.result("pg5").feature("lngr5").setIndex("looplevelinput", "manual", 0);
    model.result("pg5").feature("lngr5").setIndex("looplevel", new int[]{2}, 0);
    model.result("pg5").run();
    model.result("pg5").feature("lngr6").setIndex("looplevelinput", "manual", 0);
    model.result("pg5").feature("lngr6").setIndex("looplevel", new int[]{2}, 0);
    model.result("pg5").run();
    model.result("pg5").feature("lngr7").setIndex("looplevelinput", "manual", 0);
    model.result("pg5").feature("lngr7").setIndex("looplevel", new int[]{2}, 0);
    model.result("pg5").run();
    model.result("pg5").feature("lngr8").setIndex("looplevelinput", "manual", 0);
    model.result("pg5").feature("lngr8").setIndex("looplevel", new int[]{2}, 0);
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevelinput", "manual", 0);
    model.result("pg6").setIndex("looplevel", new int[]{1}, 0);
    model.result("pg6").run();
    model.result("pg6").feature("lngr3").setIndex("looplevelinput", "manual", 0);
    model.result("pg6").feature("lngr3").setIndex("looplevel", new int[]{2}, 0);
    model.result("pg6").run();
    model.result("pg6").feature("lngr4").setIndex("looplevelinput", "manual", 0);
    model.result("pg6").feature("lngr4").setIndex("looplevel", new int[]{2}, 0);
    model.result("pg6").run();
    model.result("pg6").feature("lngr5").set("expr", "kramer_eq_n(x)");
    model.result("pg6").run();
    model.result("pg6").feature("lngr6").set("expr", "kramer_eq_p(x)");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").label("\u8f7d\u6d41\u5b50\u6d53\u5ea6\u5e73\u8861");
    model.result("pg7").run();
    model.result("pg7").setIndex("looplevelinput", "last", 1);
    model.result("pg7").run();
    model.result("pg7").feature("lngr5").setIndex("looplevelinput", "last", 0);
    model.result("pg7").run();
    model.result("pg7").feature("lngr6").setIndex("looplevelinput", "last", 0);
    model.result("pg7").run();
    model.result("pg7").feature("lngr7").setIndex("looplevelinput", "last", 0);
    model.result("pg7").run();
    model.result("pg7").feature("lngr8").setIndex("looplevelinput", "last", 0);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").label("\u80fd\u7ea7\u6b63\u5411\u504f\u538b");
    model.result("pg8").run();
    model.result("pg8").label("\u8f7d\u6d41\u5b50\u6d53\u5ea6\u6b63\u5411\u504f\u538b");
    model.result("pg8").setIndex("looplevelinput", "last", 1);
    model.result("pg8").run();
    model.result("pg8").feature("lngr3").setIndex("looplevelinput", "last", 0);
    model.result("pg8").run();
    model.result("pg8").feature("lngr4").setIndex("looplevelinput", "last", 0);
    model.result("pg8").run();
    model.result("pg8").feature("lngr5").set("expr", "kramer_fwd_n(x)");
    model.result("pg8").run();
    model.result("pg8").feature("lngr6").set("expr", "kramer_fwd_p(x)");
    model.result("pg8").run();

    model.title("P-N \u7ed3 - \u4e00\u7ef4");

    model
         .description("\u8fd9\u4e2a\u7b80\u5355\u7684\u57fa\u51c6\u6a21\u578b\u4f7f\u7528\u6709\u9650\u5143\u6cd5\u548c\u6709\u9650\u4f53\u79ef\u6cd5\u6765\u8ba1\u7b97\u4e00\u7ef4 P-N \u7ed3\u7684\u7535\u52bf\u548c\u8f7d\u6d41\u5b50\u6d53\u5ea6\uff0c\u5e76\u5c06\u7ed3\u679c\u4e0e Kramer \u548c Hitchon \u6240\u8457\u7684 \"Semiconductor Devices, A Simulation Approach\" \u4e00\u4e66\u4e2d\u7684\u7b49\u6548\u5668\u4ef6\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.label("pn_junction_1d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
