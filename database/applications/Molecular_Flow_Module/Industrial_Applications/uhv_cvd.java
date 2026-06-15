/*
 * uhv_cvd.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:18 by COMSOL 6.3.0.290. */
public class uhv_cvd {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Molecular_Flow_Module\\Industrial_Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("fmf", "FreeMolecularFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/fmf", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("R0", "4[in]", "\u8154\u534a\u5f84");
    model.param().set("L0", "30[in]", "\u8154\u957f\u5ea6");
    model.param().set("Dw", "6[in]", "\u6676\u5706\u76f4\u5f84");
    model.param().set("Lw", "6[in]", "\u6676\u5706\u76d2\u957f\u5ea6");
    model.param().set("Dp", "6[in]", "\u6cf5\u76f4\u5f84");
    model.param().set("Rin", "1[cm]", "\u8f93\u6c14\u7ba1\u9053\u5165\u53e3\u534a\u5f84");
    model.param().set("Tf", "600[degC]", "\u7194\u7089\u6e29\u5ea6");
    model.param().set("sccm", "1", "\u603b\u8d28\u91cf\u6d41\u7387");
    model.param().set("sccmH2", "0.8*sccm", "H2 \u8d28\u91cf\u6d41\u7387 (sccms)");
    model.param().set("sccmSiH4", "0.2*sccm", "SiH4 \u8d28\u91cf\u6d41\u7387 (sccms)");
    model.param().set("SH2", "lsH2*1[l/s]", "H2 \u7684\u6cf5\u901f");
    model.param().set("SSiH4", "lsSiH4*1[l/s]", "SiH4 \u7684\u6cf5\u901f");
    model.param().set("lsH2", "350", "H2 \u7684 l/s");
    model.param().set("lsSiH4", "450", "SiH4 \u7684 l/s");
    model.param().set("sp", "1", "\u626b\u63cf\u53c2\u6570");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "R0");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L0");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"-Lo/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"-L0/2", "0", "0"});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "Dp/2");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "Dp/4+R0");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"0.3*L0", "0", "-(R0+Dp/4)"});
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("cyl1", "cyl2");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", "Dw/2");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "Lw");
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new String[]{"-Lw/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("cyl3").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("cyl4", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl4").set("r", "Dw/10");
    model.component("comp1").geom("geom1").feature("cyl4").set("h", "L0/2");
    model.component("comp1").geom("geom1").feature("cyl4").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl4");
    model.component("comp1").geom("geom1").create("uni2", "Union");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("cyl3", "cyl4");
    model.component("comp1").geom("geom1").feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("uni2");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("cyl5", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl5").set("r", "Rin");
    model.component("comp1").geom("geom1").feature("cyl5").set("h", "1.5*(R0)");
    model.component("comp1").geom("geom1").feature("cyl5").set("pos", new String[]{"-0.9*L0/2", "-1.5*R0", "0"});
    model.component("comp1").geom("geom1").feature("cyl5").set("axistype", "y");
    model.component("comp1").geom("geom1").run("cyl5");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("cyl5");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("dif1");
    model.component("comp1").geom("geom1").feature("dif2").set("keepsubtract", true);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(13, 14, 15, 16, 17, 18);
    model.component("comp1").selection("sel1").label("\u6676\u5706\u76d2");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(13, 14, 15, 16, 17, 18, 19, 20, 21, 22);
    model.component("comp1").selection("sel2").label("\u6676\u5706\u76d2\u548c\u8f68\u9053");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1")
         .set("table", new String[][]{{"1.07E-05", "189.474"}, 
         {"1.62E-05", "189.474"}, 
         {"2.97E-05", "189.474"}, 
         {"5.60E-05", "189.474"}, 
         {"9.45E-05", "189.474"}, 
         {"0.000162975", "189.474"}, 
         {"0.000334673", "189.474"}, 
         {"0.000546625", "189.474"}, 
         {"0.000774827", "186.09"}, 
         {"0.00125181", "182.707"}, 
         {"0.00187382", "177.068"}, 
         {"0.00286673", "168.045"}, 
         {"0.00410806", "160.15"}, 
         {"0.00595143", "148.872"}, 
         {"0.00834452", "136.466"}, 
         {"0.0119578", "121.805"}, 
         {"0.0182941", "108.271"}, 
         {"0.0276844", "90.2256"}, 
         {"0.0383955", "77.8195"}, 
         {"0.0562341", "69.9248"}, 
         {"0.0797104", "59.7744"}, 
         {"0.114226", "53.0075"}, 
         {"0.182542", "43.985"}, 
         {"0.267352", "39.4737"}, 
         {"0.370791", "37.218"}, 
         {"0.543061", "32.7068"}, 
         {"0.745005", "30.4511"}, 
         {"1", "32.7068"}});
    model.component("comp1").func("int1").set("funcname", "pump1_H2");
    model.component("comp1").func("int1").setIndex("argunit", "hPa", 0);
    model.component("comp1").func("int1").setIndex("fununit", "l/s", 0);
    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2")
         .set("table", new String[][]{{"1.04E-05", "170.891"}, 
         {"1.78E-05", "170.17"}, 
         {"3.34E-05", "170.212"}, 
         {"6.36E-05", "170.254"}, 
         {"0.000151909", "170.311"}, 
         {"0.00033279", "170.363"}, 
         {"0.000586352", "168.888"}, 
         {"0.00093666", "165.894"}, 
         {"0.0013714", "162.894"}, 
         {"0.00212036", "157.63"}, 
         {"0.00324296", "149.34"}, 
         {"0.00535322", "136.519"}, 
         {"0.00855257", "123.695"}, 
         {"0.0132247", "110.869"}, 
         {"0.0208999", "97.2886"}, 
         {"0.0330296", "82.9519"}, 
         {"0.0510723", "71.6384"}, 
         {"0.0824867", "61.0838"}, 
         {"0.12616", "52.038"}, 
         {"0.188795", "44.503"}, 
         {"0.285611", "39.2372"}, 
         {"0.422753", "36.2384"}, 
         {"0.625739", "33.9957"}, 
         {"0.956933", "33.2675"}});
    model.component("comp1").func("int2").set("funcname", "pump2_H2");
    model.component("comp1").func("int2").setIndex("argunit", "hPa", 0);
    model.component("comp1").func("int2").setIndex("fununit", "l/s", 0);
    model.component("comp1").func().create("int3", "Interpolation");
    model.component("comp1").func("int3")
         .set("table", new String[][]{{"1.01E-05", "274.254"}, 
         {"1.61E-05", "274.254"}, 
         {"2.53E-05", "274.254"}, 
         {"3.85E-05", "274.254"}, 
         {"5.92E-05", "267.724"}, 
         {"9.11E-05", "259.888"}, 
         {"0.000144893", "249.44"}, 
         {"0.000220614", "236.381"}, 
         {"0.000325216", "224.627"}, 
         {"0.000489863", "206.343"}, 
         {"0.000714385", "188.06"}, 
         {"0.000987147", "171.082"}, 
         {"0.00140889", "154.104"}, 
         {"0.00212216", "131.903"}, 
         {"0.00312837", "111.007"}, 
         {"0.00486705", "91.4179"}, 
         {"0.00799138", "70.5224"}, 
         {"0.0132635", "52.2388"}, 
         {"0.023233", "39.1791"}, 
         {"0.0357578", "31.3433"}, 
         {"0.0574597", "24.8134"}, 
         {"0.081129", "19.5896"}, 
         {"0.120892", "16.9776"}, 
         {"0.16705", "14.3657"}, 
         {"0.246255", "14.3657"}});
    model.component("comp1").func("int3").set("funcname", "pump3_H2");
    model.component("comp1").func("int3").setIndex("argunit", "hPa", 0);
    model.component("comp1").func("int3").setIndex("fununit", "l/s", 0);
    model.component("comp1").func().create("int4", "Interpolation");
    model.component("comp1").func("int4")
         .set("table", new String[][]{{"1.03E-05", "546.992"}, 
         {"2.08E-05", "546.992"}, 
         {"4.26E-05", "546.992"}, 
         {"9.35E-05", "546.992"}, 
         {"0.000202683", "546.992"}, 
         {"0.000275039", "546.992"}, 
         {"0.000416215", "543.609"}, 
         {"0.000623028", "541.353"}, 
         {"0.00112251", "535.714"}, 
         {"0.00200049", "525.564"}, 
         {"0.00306052", "513.158"}, 
         {"0.00463148", "496.241"}, 
         {"0.00693281", "471.429"}, 
         {"0.0100437", "438.722"}, 
         {"0.0140823", "395.865"}, 
         {"0.0182941", "358.647"}, 
         {"0.024026", "316.917"}, 
         {"0.0318998", "271.805"}, 
         {"0.0437621", "234.586"}, 
         {"0.0593847", "197.368"}, 
         {"0.0797104", "165.789"}, 
         {"0.105833", "138.722"}, 
         {"0.138993", "116.165"}, 
         {"0.186566", "98.1203"}, 
         {"0.258749", "83.4586"}, 
         {"0.362793", "69.9248"}, 
         {"0.497702", "63.1579"}, 
         {"0.697831", "55.2632"}, 
         {"1", "50.7519"}});
    model.component("comp1").func("int4").set("funcname", "pump1_SiH4");
    model.component("comp1").func("int4").setIndex("argunit", "hPa", 0);
    model.component("comp1").func("int4").setIndex("fununit", "l/s", 0);
    model.component("comp1").func().create("int5", "Interpolation");
    model.component("comp1").func("int5")
         .set("table", new String[][]{{"1.02E-05", "349.34"}, 
         {"2.04E-05", "349.386"}, 
         {"4.28E-05", "349.434"}, 
         {"0.000100176", "350.246"}, 
         {"0.000229233", "349.544"}, 
         {"0.00043116", "349.586"}, 
         {"0.000837906", "348.873"}, 
         {"0.00170083", "350.432"}, 
         {"0.00313021", "348.204"}, 
         {"0.0052232", "342.188"}, 
         {"0.00825403", "333.145"}, 
         {"0.0130453", "314.271"}, 
         {"0.0182893", "296.902"}, 
         {"0.025923", "277.265"}, 
         {"0.0355628", "255.358"}, 
         {"0.0467111", "227.398"}, 
         {"0.0606928", "195.657"}, 
         {"0.0841905", "159.384"}, 
         {"0.118053", "129.917"}, 
         {"0.188657", "98.1892"}, 
         {"0.29494", "78.5588"}, 
         {"0.461062", "64.9776"}, 
         {"0.675112", "55.9289"}, 
         {"0.988474", "51.4171"}});
    model.component("comp1").func("int5").set("funcname", "pump2_SiH4");
    model.component("comp1").func("int5").setIndex("argunit", "hPa", 0);
    model.component("comp1").func("int5").setIndex("fununit", "l/s", 0);
    model.component("comp1").func().create("int6", "Interpolation");
    model.component("comp1").func("int6")
         .set("table", new String[][]{{"9.95E-06", "671.269"}, 
         {"1.80E-05", "670.03"}, 
         {"3.15E-05", "670.094"}, 
         {"5.52E-05", "668.851"}, 
         {"0.000103197", "670.228"}, 
         {"0.00022669", "669.011"}, 
         {"0.000497953", "670.407"}, 
         {"0.000853631", "670.468"}, 
         {"0.00125839", "669.206"}, 
         {"0.00164768", "664.012"}, 
         {"0.00213431", "656.206"}, 
         {"0.00264802", "647.089"}, 
         {"0.00304677", "630.127"}, 
         {"0.00335769", "610.548"}, 
         {"0.00407852", "553.108"}, 
         {"0.00534284", "485.228"}, 
         {"0.00663109", "431.708"}, 
         {"0.00868563", "379.499"}, 
         {"0.011377", "324.679"}, 
         {"0.0149019", "272.471"}, 
         {"0.019309", "225.485"}, 
         {"0.0261234", "170.669"}, 
         {"0.0334852", "127.6"}, 
         {"0.0433845", "91.0621"}, 
         {"0.0515608", "67.5742"}, 
         {"0.0646683", "50.6222"}, 
         {"0.0865229", "40.2075"}, 
         {"0.120864", "29.7976"}, 
         {"0.165226", "25.9152"}, 
         {"0.223448", "22.0315"}, 
         {"0.283249", "23.3644"}});
    model.component("comp1").func("int6").set("funcname", "pump3_SiH4");
    model.component("comp1").func("int6").setIndex("argunit", "hPa", 0);
    model.component("comp1").func("int6").setIndex("fununit", "l/s", 0);

    model.component("comp1").physics("fmf").field("particleflux").component(new String[]{"G", "G2"});
    model.component("comp1").physics("fmf").field("particleflux").component(1, "H2");
    model.component("comp1").physics("fmf").field("particleflux").component(2, "SiH4");
    model.component("comp1").physics("fmf").prop("Compute").set("ComputeN", false);
    model.component("comp1").physics("fmf").feature("fmfp1").setIndex("Mn_H2", "2[g/mol]", 0);
    model.component("comp1").physics("fmf").feature("fmfp1").setIndex("Mn_SiH4", "32.12[g/mol]", 0);
    model.component("comp1").physics("fmf").feature("st1").set("T", "Tf");
    model.component("comp1").physics("fmf").create("wall2", "Wall", 2);
    model.component("comp1").physics("fmf").feature("wall2").selection().set(8);
    model.component("comp1").physics("fmf").feature("wall2").set("BCType", "OutgassingWall");
    model.component("comp1").physics("fmf").feature("wall2").set("BoundaryCondition", "NumberOfSCCM");
    model.component("comp1").physics("fmf").feature("wall2").setIndex("sccmmfr", "sccmH2", 0);
    model.component("comp1").physics("fmf").feature("wall2").setIndex("sccmmfr", "sccmSiH4", 1);
    model.component("comp1").physics("fmf").create("pmp1", "VacuumPump", 2);
    model.component("comp1").physics("fmf").feature("pmp1").selection().set(25);
    model.component("comp1").physics("fmf").feature("pmp1").set("SpecifyPump", "PumpSpeed");
    model.component("comp1").physics("fmf").feature("pmp1")
         .setIndex("pspeed", "pump1_H2(fmf.ptot)*(sp==1)+pump2_H2(fmf.ptot)*(sp==2)+pump3_H2(fmf.ptot)*(sp==3)", 0);
    model.component("comp1").physics("fmf").feature("pmp1")
         .setIndex("pspeed", "pump1_SiH4(fmf.ptot)*(sp==1)+pump2_SiH4(fmf.ptot)*(sp==2)+pump3_SiH4(fmf.ptot)*(sp==3)", 1);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().remaining();
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().named("sel1");
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop2").selection().set(25);

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "R0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "R0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "sp", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "1 2 3", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u5165\u5c04\u5206\u5b50\u901a\u91cf (fmf)");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("expr", "fmf.Gtot");
    model.result("pg1").feature("surf1").set("resolution", "norefine");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u603b\u538b (fmf)");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "fmf.ptot");
    model.result("pg2").feature("surf1").set("resolution", "norefine");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().dataset().duplicate("dset2", "dset1");
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().named("sel2");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "aveop1(fmf.G_SiH4)", 0);
    model.result().numerical("gev1").setIndex("descr", "SiH4 \u901a\u91cf", 0);
    model.result().numerical("gev1").setIndex("expr", "aveop1(fmf.G_SiH4/fmf.Gtot)", 1);
    model.result().numerical("gev1").setIndex("descr", "\u901a\u91cf\u5206\u6570", 1);
    model.result().numerical("gev1").setIndex("expr", "aveop2(fmf.ptot)", 2);
    model.result().numerical("gev1").setIndex("descr", "\u6cf5\u7684\u538b\u529b", 2);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u5206\u5b50\u901a\u91cf\u5206\u6570 SiH4");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "fmf.G_SiH4/fmf.Gtot");
    model.result("pg3").feature("surf1").set("colortable", "Plasma");
    model.result("pg3").run();

    model.title("\u8d85\u9ad8\u771f\u7a7a\u5316\u5b66\u6c14\u76f8\u6c89\u79ef");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5728\u201c\u81ea\u7531\u5206\u5b50\u6d41\u201d\u63a5\u53e3\u4e2d\u4f7f\u7528\u591a\u79cd\u7269\u8d28\u6a21\u62df\u7845\u7247\u7684\u751f\u957f\uff0c\u5e76\u63a2\u7d22\u6da1\u8f6e\u6cf5\u7684\u51e0\u79cd\u6cf5\u7279\u6027\u66f2\u7ebf\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("uhv_cvd.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
