/*
 * electric_motor_noise_pmsm.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:25 by COMSOL 6.3.0.290. */
public class electric_motor_noise_pmsm {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Automotive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");
    model.component("comp1").physics().create("wb", "WeakFormBoundaryPDE", "geom1");
    model.component("comp1").physics("wb").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("wb").feature("wfeq1").set("weak", new String[][]{{"0"}});

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/wb", true);

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").geom("geom2")
         .insertFile("/hymer/build/patch/old/daily/locales/zh_CN/applications.compact/Acoustics_Module/Automotive/electric_motor_noise_pmsm_geom_sequence.mph", "geom1");
    model.component("comp2").geom("geom2").run("comsel2");

    model.component("comp2").view("view2").set("renderwireframe", true);
    model.component("comp2").view("view2").set("showgrid", false);

    model.component("comp2").geom("geom2").feature("fin").set("createpairs", false);

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").label("\u6a21\u578b\u53c2\u6570");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("I0", "1[A]", "\u901a\u8fc7\u7ebf\u5708\u7684\u7535\u6d41");
    model.param("par2").set("Ltransv", "80[mm]", "\u7535\u673a\u8f74\u5411\u957f\u5ea6");
    model.param("par2")
         .set("rpm0", "10[rpm]", "\u7528\u4e8e\u8c10\u6ce2\u8ba1\u7b97\u7684\u53d1\u52a8\u673a\u65cb\u8f6c\u9891\u7387");
    model.param("par2").set("rpm_max", "10000[rpm]", "\u53d1\u52a8\u673a\u6700\u5927\u65cb\u8f6c\u9891\u7387");
    model.param("par2").set("rpm_idle", "1000[rpm]", "\u53d1\u52a8\u673a\u7684\u6020\u901f\u65cb\u8f6c\u9891\u7387");
    model.param("par2").set("w0", "2*pi*rpm0", "\u7528\u4e8e\u8c10\u6ce2\u8ba1\u7b97\u7684\u89d2\u9891\u7387");
    model.param("par2").set("theta", "60[deg]", "\u673a\u68b0\u91cd\u590d\u6700\u5c0f\u89d2\u5ea6");
    model.param("par2")
         .set("t_tot", "theta/w0", "\u7528\u4e8e\u8c10\u6ce2\u8ba1\u7b97\u7684\u91cd\u590d\u5468\u671f");
    model.param("par2").set("f0", "1/t_tot", "\u7528\u4e8e\u8c10\u6ce2\u8ba1\u7b97\u7684\u91cd\u590d\u9891\u7387");
    model.param("par2").set("ang0", "-2[deg]", "\u521d\u59cb\u89d2\u5ea6\u95f4\u9699");
    model.param("par2").set("n_harmonics", "7", "\u5206\u6790\u4e2d\u5305\u542b\u7684\u8c10\u6ce2\u6570");
    model.param("par2").set("Ncoil", "550", "\u7ebf\u5708\u7ec4\u603b\u531d\u6570");
    model.param("par2").set("a_coil", "0.25[mm^2]", "\u5bfc\u7ebf\u9762\u79ef");
    model.param("par2").set("fmax", "4000[Hz]", "\u5206\u6790\u7684\u6700\u5927\u9891\u7387");
    model.param("par2").set("fdelta", "50[Hz]", "\u9891\u7387\u8bf7\u6c42\u4e4b\u95f4\u7684\u589e\u91cf");
    model.param("par2").set("c0", "343[m/s]", "\u7a7a\u6c14\u4e2d\u7684\u58f0\u901f");
    model.param("par2").set("harm_exc", "3", "\u58f0\u632f\u5206\u6790\u4e2d\u6fc0\u52b1\u7684\u8c10\u6ce2");
    model.param("par2").set("z_mic1", "0.5[m]", "\u9ea6\u514b\u98ce 1 \u7684 z \u5750\u6807");
    model.param("par2").set("y_mic2", "0.5[m]", "\u9ea6\u514b\u98ce 2 \u7684 y \u5750\u6807");
    model.param("par2").set("eta_struct", "0.02", "\u7ed3\u6784\u963b\u5c3c\u6bd4");

    model.component("comp1").geom("geom1").create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").run("cro1");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").set("angletol", 5);
    model.component("comp1").selection("sel1").label("\u8f74");
    model.component("comp1").selection("sel1").set(40);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").set("angletol", 5);
    model.component("comp1").selection("sel2").label("\u53e0\u7247\u5f0f\u8f6c\u5b50");
    model.component("comp1").selection("sel2").set(39);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").set("angletol", 5);
    model.component("comp1").selection("sel3").label("\u78c1\u4f53 A");
    model.component("comp1").selection("sel3").set(33, 36, 37);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").set("angletol", 5);
    model.component("comp1").selection("sel4").label("\u78c1\u4f53 B");
    model.component("comp1").selection("sel4").set(34, 35, 38);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u78c1\u4f53");
    model.component("comp1").selection("uni1").set("input", new String[]{"sel3", "sel4"});
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").set("angletol", 5);
    model.component("comp1").selection("sel5").label("\u5185\u90e8\u6c14\u9699");
    model.component("comp1").selection("sel5").set(32);
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u8f6c\u5b50");
    model.component("comp1").selection("uni2").set("input", new String[]{"sel1", "sel2", "uni1"});
    model.component("comp1").selection().create("uni3", "Union");
    model.component("comp1").selection("uni3").label("\u65cb\u8f6c\u90e8\u4ef6");
    model.component("comp1").selection("uni3").set("input", new String[]{"sel5", "uni2"});
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("\u56fa\u5b9a\u90e8\u4ef6");
    model.component("comp1").selection("com1").set("input", new String[]{"uni3"});
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").set("angletol", 5);
    model.component("comp1").selection("sel6").label("\u7ebf\u5708 A");
    model.component("comp1").selection("sel6").set(12, 15, 18, 20, 25, 27);
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").set("angletol", 5);
    model.component("comp1").selection("sel7").label("\u7ebf\u5708 B");
    model.component("comp1").selection("sel7").set(13, 16, 17, 19, 26, 28);
    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").set("angletol", 5);
    model.component("comp1").selection("sel8").label("\u7ebf\u5708 C");
    model.component("comp1").selection("sel8").set(9, 10, 21, 22, 23, 24);
    model.component("comp1").selection().create("uni4", "Union");
    model.component("comp1").selection("uni4").label("\u7ebf\u5708");
    model.component("comp1").selection("uni4").set("input", new String[]{"sel6", "sel7", "sel8"});
    model.component("comp1").selection().create("sel9", "Explicit");
    model.component("comp1").selection("sel9").set("angletol", 5);
    model.component("comp1").selection("sel9").label("\u5916\u90e8\u6c14\u9699");
    model.component("comp1").selection("sel9").set(11);
    model.component("comp1").selection().create("sel10", "Explicit");
    model.component("comp1").selection("sel10").set("angletol", 5);
    model.component("comp1").selection("sel10").label("\u5916\u90e8\u7a7a\u6c14");
    model.component("comp1").selection("sel10").set(1, 2, 3, 4, 14, 30, 31);
    model.component("comp1").selection().create("sel11", "Explicit");
    model.component("comp1").selection("sel11").set("angletol", 5);
    model.component("comp1").selection("sel11").label("\u5916\u58f3");
    model.component("comp1").selection("sel11").set(5, 6, 7, 29);
    model.component("comp1").selection().create("uni5", "Union");
    model.component("comp1").selection("uni5").label("\u7a7a\u6c14");
    model.component("comp1").selection("uni5").set("input", new String[]{"sel5", "sel9", "sel10"});
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u5b9a\u5b50\u529b");
    model.component("comp1").selection("dif1").set("add", new String[]{"com1"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"uni4", "sel11", "uni5"});
    model.component("comp1").selection().create("disk1", "Disk");
    model.component("comp1").selection("disk1").set("angletol", 5);
    model.component("comp1").selection("disk1").label("\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("disk1").set("entitydim", 1);
    model.component("comp1").selection("disk1").set("r", "inf");
    model.component("comp1").selection("disk1").set("rin", "r_stator");
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u90bb\u8fd1\u8f6c\u5b50\u529b");
    model.component("comp1").selection("adj1").set("input", new String[]{"uni2"});
    model.component("comp1").selection().create("adj2", "Adjacent");
    model.component("comp1").selection("adj2").label("\u90bb\u8fd1\u5b9a\u5b50\u529b");
    model.component("comp1").selection("adj2").set("input", new String[]{"dif1"});
    model.component("comp1").selection().create("dif2", "Difference");
    model.component("comp1").selection("dif2").label("\u529b\u8ba1\u7b97");
    model.component("comp1").selection("dif2").set("entitydim", 1);
    model.component("comp1").selection("dif2").set("add", new String[]{"adj1", "adj2"});
    model.component("comp1").selection("dif2").set("subtract", new String[]{"disk1"});
    model.component("comp1").selection().create("adj3", "Adjacent");
    model.component("comp1").selection("adj3").label("\u90bb\u8fd1\u6c14\u9699\u548c\u7ebf\u5708");
    model.component("comp1").selection("adj3").set("input", new String[]{"sel5", "uni4", "sel9"});
    model.component("comp1").selection().create("uni6", "Union");
    model.component("comp1").selection("uni6").label("\u94c1");
    model.component("comp1").selection("uni6").set("input", new String[]{"sel1", "sel2", "dif1"});
    model.component("comp1").selection().create("uni7", "Union");
    model.component("comp1").selection("uni7").label("\u529b\u8ba1\u7b97\u57df");
    model.component("comp1").selection("uni7").set("input", new String[]{"sel5", "uni4", "sel9"});
    model.component("comp1").selection().create("dif3", "Difference");
    model.component("comp1").selection("dif3").label("\u90bb\u8fd1\u5b9a\u5b50\u4e2d\u7684\u6c14\u9699");
    model.component("comp1").selection("dif3").set("entitydim", 1);
    model.component("comp1").selection("dif3").set("add", new String[]{"adj2"});
    model.component("comp1").selection("dif3").set("subtract", new String[]{"disk1"});

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("rotation", "if(isdefined(t),w0*t+ang0,0)");
    model.component("comp1").variable("var1").descr("rotation", "\u65cb\u8f6c");

    model.component("comp1").cpl().create("genext1", "GeneralExtrusion");
    model.component("comp1").cpl("genext1").selection().geom("geom1", 1);
    model.component("comp1").cpl("genext1").selection().named("dif2");
    model.component("comp1").cpl("genext1").set("dstmap", new String[]{"y", "z"});
    model.component("comp1").cpl("genext1").set("usesrcmap", true);
    model.component("comp1").cpl("genext1").set("method", "closest");
    model.component("comp1").cpl("genext1").set("usenan", true);

    model.component("comp1").coordSystem().create("sys3", "Cylindrical");

    model.component("comp1").common().create("rot1", "RotatingDomain");
    model.component("comp1").common("rot1").selection().all();
    model.component("comp1").common("rot1").selection().named("uni3");
    model.component("comp1").common("rot1").set("rotationAngle", "rotation");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup().create("BHCurve", "BHCurve", "B-H Curve");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").func().create("BH", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("EffectiveBHCurve", "EffectiveBHCurve", "Effective B-H Curve");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").func()
         .create("BHeff", "Interpolation");
    model.component("comp1").material("mat1").label("Soft Iron (Without Losses)");
    model.component("comp1").material("mat1").set("family", "iron");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("BHCurve").label("B-H Curve");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").func("BH").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").func("BH")
         .set("table", new String[][]{{"0", "0"}, 
         {"663.146", "1"}, 
         {"1067.5", "1.1"}, 
         {"1705.23", "1.2"}, 
         {"2463.11", "1.3"}, 
         {"3841.67", "1.4"}, 
         {"5425.74", "1.5"}, 
         {"7957.75", "1.6"}, 
         {"12298.3", "1.7"}, 
         {"20462.8", "1.8"}, 
         {"32169.6", "1.9"}, 
         {"61213.4", "2"}, 
         {"111408", "2.1"}, 
         {"188487.757", "2.2"}, 
         {"267930.364", "2.3"}, 
         {"347507.836", "2.4"}});
    model.component("comp1").material("mat1").propertyGroup("BHCurve").func("BH").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").func("BH").set("fununit", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("BHCurve").func("BH")
         .set("argunit", new String[]{"A/m"});
    model.component("comp1").material("mat1").propertyGroup("BHCurve").func("BH").set("defineinv", true);
    model.component("comp1").material("mat1").propertyGroup("BHCurve").func("BH").set("defineprimfun", true);
    model.component("comp1").material("mat1").propertyGroup("BHCurve").set("normB", "BH(normHin)");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").set("normH", "BH_inv(normBin)");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").set("Wpm", "BH_prim(normHin)");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").descr("normHin", "\u78c1\u573a\u6a21");
    model.component("comp1").material("mat1").propertyGroup("BHCurve")
         .descr("normBin", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").addInput("magneticfield");
    model.component("comp1").material("mat1").propertyGroup("BHCurve").addInput("magneticfluxdensity");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").label("Effective B-H Curve");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("table", new String[][]{{"0", "0"}, 
         {"663.146", "1.000000051691021"}, 
         {"1067.5", "1.4936495124126294"}, 
         {"1705.23", "1.9415328461315795"}, 
         {"2463.11", "2.257765669366018"}, 
         {"3841.67", "2.609980642431287"}, 
         {"5425.74", "2.8664452090837504"}, 
         {"7957.75", "3.1441438097176118"}, 
         {"12298.3", "3.448538051654125"}, 
         {"20462.8", "3.7816711973679054"}, 
         {"32169.6", "4.058345590113038"}, 
         {"61213.4", "4.420646552950275"}, 
         {"111408", "4.721274089545955"}, 
         {"188487.757", "4.972148140718701"}, 
         {"267930.364", "5.145510860855953"}, 
         {"347507.836", "5.245510861426532"}});
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("fununit", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("argunit", new String[]{"A/m"});
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff").set("defineinv", true);
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("defineprimfun", true);
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").set("normBeff", "BHeff(normHeffin)");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve")
         .set("normHeff", "BHeff_inv(normBeffin)");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve")
         .set("Wpmeff", "BHeff_prim(normHeffin)");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve")
         .descr("normHeffin", "\u6709\u6548\u78c1\u573a\u6a21");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve")
         .descr("normBeffin", "\u6709\u6548\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").addInput("magneticfield");
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve").addInput("magneticfluxdensity");
    model.component("comp1").material("mat1").selection().named("uni6");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("BHCurve").set("normB", new String[]{"BH(normHin)"});
    model.component("comp1").material("mat1").propertyGroup("BHCurve").set("normH", new String[]{"BH_inv(normBin)"});
    model.component("comp1").material("mat1").propertyGroup("BHCurve").set("Wpm", new String[]{"BH_prim(normHin)"});
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve")
         .set("normBeff", new String[]{"BHeff(normHeffin)"});
    model.component("comp1").material("mat1").propertyGroup("EffectiveBHCurve")
         .set("normHeff", new String[]{"BHeff_inv(normBeffin)"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat2").label("Copper");
    model.component("comp1").material("mat2").set("family", "copper");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("emissivity", "0.5");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "8940[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "126e9[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.34");
    model.component("comp1").material("mat2").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("rho0", "1.667e-8[ohm*m]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("alpha", "3.862e-3[1/K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("Tref", "293.15[K]");
    model.component("comp1").material("mat2").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat2").selection().named("uni4");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RemanentFluxDensity", "RemanentFluxDensity", "Remanent flux density");
    model.component("comp1").material("mat3").label("N40 (Sintered NdFeB)");
    model.component("comp1").material("mat3").set("family", "chrome");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity").label("Remanent flux density");
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity")
         .set("murec", new String[]{"1.05", "0", "0", "0", "1.05", "0", "0", "0", "1.05"});
    model.component("comp1").material("mat3").propertyGroup("RemanentFluxDensity").set("normBr", "1.28[T]");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").label("Aluminum 6063-T83");
    model.component("comp1").material("mat4").set("family", "aluminum");
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.030e7[S/m]", "0", "0", "0", "3.030e7[S/m]", "0", "0", "0", "3.030e7[S/m]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23.4e-6[1/K]", "0", "0", "0", "23.4e-6[1/K]", "0", "0", "0", "23.4e-6[1/K]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"201[W/(m*K)]", "0", "0", "0", "201[W/(m*K)]", "0", "0", "0", "201[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", "69[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat3").selection().named("uni1");
    model.component("comp1").material("mat4").selection().named("sel11");

    model.component("comp1").physics("mf").prop("d").set("d", "Ltransv");
    model.component("comp1").physics("mf").prop("ShapeProperty").set("order_magneticvectorpotential", 1);
    model.component("comp1").physics("mf").selection()
         .set(8, 9, 10, 11, 12, 13, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 32, 33, 34, 35, 36, 37, 38, 39, 40);
    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als1").label("\u94c1\u57df");
    model.component("comp1").physics("mf").feature("als1").selection().named("uni6");
    model.component("comp1").physics("mf").feature("als1").set("ConstitutiveRelationBH", "BHCurve");
    model.component("comp1").physics("mf").create("als2", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als2").label("\u78c1\u4f53 A");
    model.component("comp1").physics("mf").feature("als2").selection().named("sel3");
    model.component("comp1").physics("mf").feature("als2").set("coordinateSystem", "sys3");
    model.component("comp1").physics("mf").feature("als2").set("ConstitutiveRelationBH", "RemanentFluxDensity");
    model.component("comp1").physics("mf").feature("als2").set("sigma_mat", "userdef");
    model.component("comp1").physics("mf").feature().duplicate("als3", "als2");
    model.component("comp1").physics("mf").feature("als3").label("\u78c1\u4f53 B");
    model.component("comp1").physics("mf").feature("als3").selection().named("sel4");
    model.component("comp1").physics("mf").feature("als3").set("e_crel_BH_RemanentFluxDensity", new int[]{-1, 0, 0});
    model.component("comp1").physics("mf").create("coil1", "Coil", 2);
    model.component("comp1").physics("mf").feature("coil1").label("\u7ebf\u5708 A");
    model.component("comp1").physics("mf").feature("coil1").selection().named("sel6");
    model.component("comp1").physics("mf").feature("coil1").setIndex("materialType", "solid", 0);
    model.component("comp1").physics("mf").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil1").set("coilGroup", true);
    model.component("comp1").physics("mf").feature("coil1").set("ICoil", "I0*cos(3*rotation)");
    model.component("comp1").physics("mf").feature("coil1").set("N", "Ncoil");
    model.component("comp1").physics("mf").feature("coil1").set("AreaFrom", "UserDefined");
    model.component("comp1").physics("mf").feature("coil1").set("HarmonicLoss", false);
    model.component("comp1").physics("mf").feature("coil1").set("coilWindArea", "a_coil");
    model.component("comp1").physics("mf").feature("coil1").create("rcd1", "ReverseCoilGroupDomain", 2);
    model.component("comp1").physics("mf").feature("coil1").feature("rcd1").selection().set(15, 18, 27);
    model.component("comp1").physics("mf").feature().duplicate("coil2", "coil1");
    model.component("comp1").physics("mf").feature("coil2").label("\u7ebf\u5708 B");
    model.component("comp1").physics("mf").feature("coil2").selection().named("sel7");
    model.component("comp1").physics("mf").feature("coil2").set("ICoil", "I0*cos(3*rotation-120[deg])");
    model.component("comp1").physics("mf").feature("coil2").feature("rcd1").selection().set(13, 19, 26);
    model.component("comp1").physics("mf").feature().duplicate("coil3", "coil2");
    model.component("comp1").physics("mf").feature("coil3").label("\u7ebf\u5708 C");
    model.component("comp1").physics("mf").feature("coil3").selection().named("sel8");
    model.component("comp1").physics("mf").feature("coil3").set("ICoil", "I0*cos(3*rotation-240[deg])");
    model.component("comp1").physics("mf").feature("coil3").feature("rcd1").selection().set(9, 22, 23);
    model.component("comp1").physics("mf").create("cont1", "Continuity", 1);
    model.component("comp1").physics("mf").feature("cont1").set("pairs", new String[]{"ap2"});
    model.component("comp1").physics("mf").feature("cont1").set("constraintOptions", "weakConstraints");
    model.component("comp1").physics("mf").create("fcal1", "ForceCalculation", 2);
    model.component("comp1").physics("mf").feature("fcal1").label("\u529b\u8ba1\u7b97\u8f6c\u5b50");
    model.component("comp1").physics("mf").feature("fcal1").selection().named("uni2");
    model.component("comp1").physics("mf").feature("fcal1").set("ForceName", "rot");
    model.component("comp1").physics("mf").feature().duplicate("fcal2", "fcal1");
    model.component("comp1").physics("mf").feature("fcal2").label("\u529b\u8ba1\u7b97\u5b9a\u5b50");
    model.component("comp1").physics("mf").feature("fcal2").selection().named("dif1");
    model.component("comp1").physics("mf").feature("fcal2").set("ForceName", "stat");
    model.component("comp1").physics("wb").selection().named("dif2");
    model.component("comp1").physics("wb").prop("ShapeProperty").set("order", 1);
    model.component("comp1").physics("wb").field("dimensionless").component(new String[]{"u", "u2"});
    model.component("comp1").physics("wb").field("dimensionless").component(1, "Fx");
    model.component("comp1").physics("wb").field("dimensionless").component(2, "Fy");
    model.component("comp1").physics("wb").prop("Units").set("CustomDependentVariableUnit", "1");
    model.component("comp1").physics("wb").prop("Units").set("DependentVariableQuantity", "none");
    model.component("comp1").physics("wb").prop("Units").setIndex("CustomDependentVariableUnit", "N/m^2", 0, 0);
    model.component("comp1").physics("wb").prop("Units").set("CustomSourceTermUnit", "m^-2");
    model.component("comp1").physics("wb").prop("Units").set("SourceTermQuantity", "none");
    model.component("comp1").physics("wb").prop("Units").setIndex("CustomSourceTermUnit", "N/m^2", 0, 0);
    model.component("comp1").physics("wb").feature("init1")
         .set("Fx", "if(isnan(mf.nTx_stat),mf.nTx_rot*cos(rotation)+mf.nTy_rot*sin(rotation),mf.nTx_stat)");
    model.component("comp1").physics("wb").feature("init1")
         .set("Fy", "if(isnan(mf.nTy_stat),-mf.nTx_rot*sin(rotation)+mf.nTy_rot*cos(rotation),mf.nTy_stat)");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "4[mm]");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "1[mm]");
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.15);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.1);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().named("dif2");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "0.3[mm]");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmin", "0.15[mm]");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgrad", 1.05);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hcurve", 0.05);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("dif2");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "range(0,1/12/n_harmonics,1)*t_tot");
    model.study("std1").feature("time").setSolveFor("/physics/wb", false);
    model.study("std1").feature("time").set("useinitsol", true);
    model.study("std1").feature("time").set("initmethod", "sol");
    model.study("std1").feature("time").set("initstudy", "std1");
    model.study("std1").feature("time").setEntry("mesh", "geom2", "nomesh");
    model.study("std1").feature("stat").setSolveFor("/physics/wb", false);
    model.study("std1").feature("stat").setEntry("mesh", "geom2", "nomesh");
    model.study("std1").label("\u7814\u7a76 1 - \u7535\u78c1\u5206\u6790");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "onevery");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("solutionparams", "parent");
    model.result("pg1").feature("str1").set("titletype", "none");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.03);
    model.result("pg1").feature("str1").set("maxlen", 0.4);
    model.result("pg1").feature("str1").set("maxsteps", 5000);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("inheritcolor", false);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection()
         .set(28, 29, 32, 33, 34, 35, 38, 39, 43, 44, 48, 49, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 65, 66, 67, 68, 69, 70, 71, 72, 77, 78, 79, 80, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 99, 100, 101, 102, 103, 104, 105, 106, 108, 109, 110, 111, 112, 113, 116, 117, 119, 120, 121, 122, 123, 124, 126, 127, 168, 169, 170, 171, 176, 177, 178, 179, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 196, 197, 199, 200, 203, 204, 205, 206, 207, 208, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 225, 226, 227, 228, 229, 230, 231, 232, 237, 238, 239, 240, 241, 242, 244, 245, 247, 248, 250, 251, 252, 253, 258, 259, 260, 261, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").feature().create("con1", "Contour");
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("solutionparams", "parent");
    model.result("pg1").feature("con1").set("expr", "mf.Az");
    model.result("pg1").feature("con1").set("titletype", "none");
    model.result("pg1").feature("con1").set("number", 10);
    model.result("pg1").feature("con1").set("levelrounding", false);
    model.result("pg1").feature("con1").set("coloring", "uniform");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").feature("con1").set("color", "custom");
    model.result("pg1").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg1").feature("con1").set("resolution", "fine");
    model.result("pg1").feature("con1").set("inheritcolor", false);
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("data", "parent");
    model.result("pg1").feature("con1").set("inheritplot", "surf1");
    model.result("pg1").feature("con1").feature().create("filt1", "Filter");
    model.result("pg1").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("rangecoloractive", true);
    model.result("pg1").feature("surf1").set("rangecolormin", 0);
    model.result("pg1").feature("surf1").set("rangecolormax", 1.5);
    model.result("pg1").run();
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("arwl1", "ArrowLine");
    model.result("pg1").feature("arwl1").set("expr", new String[]{"mf.nTx_stat", "mf.By"});
    model.result("pg1").feature("arwl1").setIndex("expr", "mf.nTy_stat", 1);
    model.result("pg1").feature("arwl1").set("placement", "gausspoints");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("arwl2", "ArrowLine");
    model.result("pg1").feature("arwl2").set("expr", new String[]{"mf.nTx_rot", "mf.nTy_rot"});
    model.result("pg1").feature("arwl2").set("placement", "gausspoints");
    model.result("pg1").feature("arwl2").set("titletype", "none");
    model.result("pg1").feature("arwl2").set("color", "blue");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("arwl1").active(false);
    model.result("pg1").feature("arwl2").active(false);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").set("titletype", "none");

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", "interp", 0);
    model.result("pg1").set("interp", new double[]{0.25});
    model.result("pg1").run();
    model.result("pg1").set("interp", new double[]{0.5});
    model.result("pg1").run();
    model.result("pg1").set("interp", new double[]{0.75});
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 43, 0);
    model.result("pg1").set("titletype", "label");
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", true);

    model.result("pg1").run();
    model.result("pg1").feature("arwl1").active(true);
    model.result("pg1").feature("arwl2").active(true);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u9009\u5b9a\u7684\u70b9\u7684\u7535\u78c1\u529b\uff0c\u65f6\u57df");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u5e94\u529b (MPa)");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(174);
    model.result("pg2").feature("ptgr1").set("expr", "abs(mf.nTx_stat)");
    model.result("pg2").feature("ptgr1").set("unit", "MPa");
    model.result("pg2").feature("ptgr1").set("descractive", true);
    model.result("pg2").feature("ptgr1").set("descr", "\u7edd\u5bf9 x \u5e94\u529b");
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("autodescr", true);
    model.result("pg2").feature("ptgr1").set("linemarker", "point");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr2").set("expr", "abs(mf.nTy_stat)");
    model.result("pg2").feature("ptgr2").set("descr", "\u7edd\u5bf9 y \u5e94\u529b");
    model.result("pg2").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr3").selection().set(176);
    model.result("pg2").feature("ptgr3").set("expr", "abs(mf.nTx_stat)");
    model.result("pg2").feature("ptgr3").set("descr", "\u7edd\u5bf9 x \u5e94\u529b");
    model.result("pg2").feature().duplicate("ptgr4", "ptgr3");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr4").set("expr", "abs(mf.nTy_stat)");
    model.result("pg2").feature("ptgr4").set("descr", "\u7edd\u5bf9 y \u5e94\u529b");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u9009\u5b9a\u7684\u70b9\u7684\u7535\u78c1\u529b\uff0cFFT");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u9891\u7387 (Hz)");
    model.result("pg3").set("ylabel", "\u5085\u91cc\u53f6\u7cfb\u6570 (MPa)");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr1").set("xdata", "fourier");
    model.result("pg3").feature("ptgr1").set("fouriershow", "spectrum");
    model.result("pg3").feature("ptgr1").set("freqrangeactive", true);
    model.result("pg3").feature("ptgr1").set("freqmin", "f0/2");
    model.result("pg3").feature("ptgr1").set("freqmax", "f0*(n_harmonics+1)");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr2").set("xdata", "fourier");
    model.result("pg3").feature("ptgr2").set("fouriershow", "spectrum");
    model.result("pg3").feature("ptgr2").set("freqrangeactive", true);
    model.result("pg3").feature("ptgr2").set("freqmin", "f0/2");
    model.result("pg3").feature("ptgr2").set("freqmax", "f0*(n_harmonics+1)");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr3").set("xdata", "fourier");
    model.result("pg3").feature("ptgr3").set("fouriershow", "spectrum");
    model.result("pg3").feature("ptgr3").set("freqrangeactive", true);
    model.result("pg3").feature("ptgr3").set("freqmin", "f0/2");
    model.result("pg3").feature("ptgr3").set("freqmax", "f0*(n_harmonics+1)");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr4").set("xdata", "fourier");
    model.result("pg3").feature("ptgr4").set("fouriershow", "spectrum");
    model.result("pg3").feature("ptgr4").set("freqrangeactive", true);
    model.result("pg3").feature("ptgr4").set("freqmin", "f0/2");
    model.result("pg3").feature("ptgr4").set("freqmax", "f0*(n_harmonics+1)");
    model.result("pg3").set("ylog", true);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4")
         .label("\u9009\u5b9a\u7684\u70b9\u7684\u7535\u78c1\u529b\uff0cFFT\uff0c\u5f52\u4e00\u5316\u4e3a\u4e00\u6b21\u8c10\u6ce2");
    model.result("pg4").set("ylabel", "\u5f52\u4e00\u5316\u7684\u5085\u91cc\u53f6\u7cfb\u6570 (1)");
    model.result("pg4").set("legendpos", "lowerleft");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr1").set("expr", "abs(mf.nTx_stat)/5.4675[MPa]");
    model.result("pg4").feature("ptgr1")
         .set("descr", "\u5f52\u4e00\u5316\u4e3a\u4e00\u6b21\u8c10\u6ce2\u7684\u7edd\u5bf9 x \u5e94\u529b");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr2").set("expr", "abs(mf.nTy_stat)/0.330696[MPa]");
    model.result("pg4").feature("ptgr2")
         .set("descr", "\u5f52\u4e00\u5316\u4e3a\u4e00\u6b21\u8c10\u6ce2\u7684\u7edd\u5bf9 y \u5e94\u529b");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr3").set("expr", "abs(mf.nTx_stat)/0.079339[MPa]");
    model.result("pg4").feature("ptgr3")
         .set("descr", "\u5f52\u4e00\u5316\u4e3a\u4e00\u6b21\u8c10\u6ce2\u7684\u7edd\u5bf9 x \u5e94\u529b");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr4").set("expr", "abs(mf.nTy_stat)/6.187[MPa]");
    model.result("pg4").feature("ptgr4")
         .set("descr", "\u5f52\u4e00\u5316\u4e3a\u4e00\u6b21\u8c10\u6ce2\u7684\u7edd\u5bf9 y \u5e94\u529b");
    model.result("pg4").run();

    model.study().create("std2");
    model.study("std2").label("\u7814\u7a76 2 - \u7535\u78c1\u529b FFT");
    model.study("std2").create("tffft", "TimeToFreqFFT");
    model.study("std2").feature("tffft").set("fftinputmethod", "init");
    model.study("std2").feature("tffft").set("fftinputstudy", "std1");
    model.study("std2").feature("tffft").set("fftendtime", "t_tot");
    model.study("std2").feature("tffft").set("fftmaxfreq", "n_harmonics/t_tot");
    model.study("std2").feature("tffft").setSolveFor("/physics/mf", false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 8, 0);
    model.result("pg5").create("line1", "Line");
    model.result("pg5").label("\u5f31\u5f62\u5f0f\u8fb9\u754c\u504f\u5fae\u5206\u65b9\u7a0b");
    model.result("pg5").feature("line1").set("expr", "Fx");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u78c1\u529b\uff0cFFT");
    model.result("pg5").set("edges", false);
    model.result("pg5").run();
    model.result("pg5").feature("line1").set("titletype", "none");
    model.result("pg5").feature("line1").set("linetype", "tube");
    model.result("pg5").feature("line1").set("radiusexpr", "0.0002");
    model.result("pg5").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg5").feature("line1").set("coloring", "uniform");
    model.result("pg5").feature("line1").set("color", "black");
    model.result("pg5").run();
    model.result("pg5").create("arwl1", "ArrowLine");
    model.result("pg5").feature("arwl1").set("expr", new String[]{"real(Fx)/t_tot", "mf.By"});
    model.result("pg5").feature("arwl1").setIndex("expr", "real(Fy)/t_tot", 1);
    model.result("pg5").feature("arwl1").set("descractive", true);
    model.result("pg5").feature("arwl1").set("descr", "\u529b\u5206\u91cf\u5b9e\u90e8\uff08\u7ea2\u8272\uff09");
    model.result("pg5").feature("arwl1").set("placement", "gausspoints");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").create("arwl2", "ArrowLine");
    model.result("pg5").feature("arwl2").set("expr", new String[]{"imag(Fx)/t_tot", "mf.By"});
    model.result("pg5").feature("arwl2").setIndex("expr", "imag(Fy)/t_tot", 1);
    model.result("pg5").feature("arwl2").set("descractive", true);
    model.result("pg5").feature("arwl2").set("descr", "\u529b\u5206\u91cf\u865a\u90e8\uff08\u84dd\u8272\uff09");
    model.result("pg5").feature("arwl2").set("placement", "gausspoints");
    model.result("pg5").feature("arwl2").set("color", "blue");
    model.result("pg5").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg4");
    model.nodeGroup("grp1").add("plotgroup", "pg5");
    model.nodeGroup("grp1").label("\u7535\u78c1\u7ed3\u679c");

    model.component("comp2").physics().create("acpr", "PressureAcoustics", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/acpr", false);
    model.study("std1").feature("time").setSolveFor("/physics/acpr", false);
    model.study("std2").feature("tffft").setSolveFor("/physics/acpr", false);

    model.component("comp2").physics().create("solid", "SolidMechanics", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std1").feature("time").setSolveFor("/physics/solid", false);
    model.study("std2").feature("tffft").setSolveFor("/physics/solid", false);

    model.component("comp2").multiphysics().create("asb1", "AcousticStructureBoundary", 2);

    model.study("std1").feature("stat").setSolveFor("/multiphysics/asb1", false);
    model.study("std1").feature("time").setSolveFor("/multiphysics/asb1", false);
    model.study("std2").feature("tffft").setSolveFor("/multiphysics/asb1", false);

    model.component("comp2").multiphysics("asb1").set("Acoustics_physics", "acpr");
    model.component("comp2").multiphysics("asb1").set("Structure_physics", "solid");
    model.component("comp2").multiphysics("asb1").selection().all();

    model.component("comp2").physics("acpr").selection().named("geom2_comsel1");
    model.component("comp2").physics("acpr").create("efc1", "ExteriorFieldCalculation", 2);
    model.component("comp2").physics("acpr").feature("efc1").selection().named("geom2_sel7");
    model.component("comp2").physics("acpr").feature("efc1").setIndex("SymmetryCondition2", 1, 0);
    model.component("comp2").physics("acpr").feature("efc1").setIndex("SymmetryPosition2", -0.073, 0);
    model.component("comp2").physics("solid").selection().named("geom2_unisel1");
    model.component("comp2").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp2").physics("solid").feature("fix1").selection().named("geom2_sel8");
    model.component("comp2").physics("solid").feature("lemm1").create("dmp1", "Damping", 3);
    model.component("comp2").physics("solid").feature("lemm1").feature("dmp1")
         .set("InputParameters", "DampingRatios");
    model.component("comp2").physics("solid").feature("lemm1").feature("dmp1").set("f1", "f0");
    model.component("comp2").physics("solid").feature("lemm1").feature("dmp1").set("zeta1", "eta_struct");
    model.component("comp2").physics("solid").feature("lemm1").feature("dmp1").set("f2", "fmax");
    model.component("comp2").physics("solid").feature("lemm1").feature("dmp1").set("zeta2", "eta_struct");
    model.component("comp2").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp2").physics("solid").feature("bndl1").selection().named("geom2_sel6");
    model.component("comp2").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "tor(rpm)*comp1.genext1(withsol('sol3',Fx,setval(freq,f0*(harm_exc))))", "tor(rpm)*comp1.genext1(withsol('sol3',Fy,setval(freq,f0*(harm_exc))))"});

    model.component("comp2").mesh("mesh2").create("ftri1", "FreeTri");
    model.component("comp2").mesh("mesh2").feature("ftri1").selection().named("geom2_sel9");
    model.component("comp2").mesh("mesh2").feature("size").set("hmax", "c0/fmax/5");
    model.component("comp2").mesh("mesh2").feature("size").set("hmin", "3[mm]");
    model.component("comp2").mesh("mesh2").run();
    model.component("comp2").mesh("mesh2").feature("ftri1").create("size1", "Size");
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").set("hmax", "10[mm]");
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").set("hminactive", true);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").set("hmin", "0.3[mm]");
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").set("hgradactive", true);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").set("hgrad", 1.2);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").set("hcurveactive", true);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").set("hcurve", 0.1);
    model.component("comp2").mesh("mesh2").feature("ftri1").feature("size1").selection()
         .set(301, 306, 331, 334, 337, 351, 354, 376, 379, 403, 406, 437, 445, 464, 467, 496, 499, 505, 508, 525, 528, 949, 952, 957, 966, 969, 993, 996);
    model.component("comp2").mesh("mesh2").run("ftri1");
    model.component("comp2").mesh("mesh2").create("cpf1", "CopyFace");
    model.component("comp2").mesh("mesh2").feature("cpf1").selection("source").geom(2);
    model.component("comp2").mesh("mesh2").feature("cpf1").selection("destination").geom(2);
    model.component("comp2").mesh("mesh2").feature("cpf1").selection("source").set(431);
    model.component("comp2").mesh("mesh2").feature("cpf1").selection("destination").set(916);
    model.component("comp2").mesh("mesh2").run("cpf1");
    model.component("comp2").mesh("mesh2").create("swe1", "Sweep");
    model.component("comp2").mesh("mesh2").feature("swe1").selection().geom("geom2", 3);
    model.component("comp2").mesh("mesh2").feature("swe1").selection().named("geom2_sel10");
    model.component("comp2").mesh("mesh2").run("swe1");
    model.component("comp2").mesh("mesh2").create("ftet1", "FreeTet");
    model.component("comp2").mesh("mesh2").feature("ftet1").selection().geom("geom2", 3);
    model.component("comp2").mesh("mesh2").feature("ftet1").selection().named("geom2_comsel2");
    model.component("comp2").mesh("mesh2").run("ftet1");
    model.component("comp2").mesh("mesh2").create("swe2", "Sweep");
    model.component("comp2").mesh("mesh2").feature("swe2").selection().geom("geom2", 3);
    model.component("comp2").mesh("mesh2").feature("swe2").selection().named("geom2_sel5");
    model.component("comp2").mesh("mesh2").feature("swe2").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh2").feature("swe2").feature("dis1").set("numelem", 6);
    model.component("comp2").mesh("mesh2").run("swe2");
    model.component("comp2").mesh("mesh2").create("bl1", "BndLayer");
    model.component("comp2").mesh("mesh2").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp2").mesh("mesh2").feature("bl1").selection().geom(3);
    model.component("comp2").mesh("mesh2").feature("bl1").selection().set();
    model.component("comp2").mesh("mesh2").feature("bl1").selection().allGeom();
    model.component("comp2").mesh("mesh2").feature("bl1").selection().geom("geom2", 3);
    model.component("comp2").mesh("mesh2").feature("bl1").selection().set(9);
    model.component("comp2").mesh("mesh2").feature("bl1").set("smoothtransition", false);
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").set("blnlayers", 1);
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").selection().named("geom2_sel7");
    model.component("comp2").mesh("mesh2").run("bl1");

    model.component("comp2").variable().create("var2");
    model.component("comp2").variable("var2").set("rpm", "acpr.freq/f0*rpm0/harm_exc");
    model.component("comp2").variable("var2").descr("rpm", "\u8f6c\u901f");
    model.component("comp2").variable("var2").set("p_mic1", "subst(abs(acpr.efc1.pext),x,0,y,0,z,z_mic1)");
    model.component("comp2").variable("var2")
         .descr("p_mic1", "\u9ea6\u514b\u98ce 1 \u5904\u7684\u7edd\u5bf9\u538b\u529b");
    model.component("comp2").variable("var2").set("spl_mic1", "subst(acpr.efc1.Lp_pext,x,0,y,0,z,z_mic1)");
    model.component("comp2").variable("var2")
         .descr("spl_mic1", "\u9ea6\u514b\u98ce 1 \u5904\u7684\u58f0\u538b\u7ea7");
    model.component("comp2").variable("var2").set("p_mic2", "subst(abs(acpr.efc1.pext),x,0,y,y_mic2,z,0)");
    model.component("comp2").variable("var2")
         .descr("p_mic2", "\u9ea6\u514b\u98ce 2 \u5904\u7684\u7edd\u5bf9\u538b\u529b");
    model.component("comp2").variable("var2").set("spl_mic2", "subst(acpr.efc1.Lp_pext,x,0,y,y_mic2,z,0)");
    model.component("comp2").variable("var2")
         .descr("spl_mic2", "\u9ea6\u514b\u98ce 2 \u5904\u7684\u58f0\u538b\u7ea7");

    model.component("comp2").func().create("an1", "Analytic");
    model.component("comp2").func("an1").label("\u626d\u77e9\u66f2\u7ebf");
    model.component("comp2").func("an1").set("funcname", "tor");
    model.component("comp2").func("an1").set("expr", "if(rev<4000,1,4000/rev)");
    model.component("comp2").func("an1").set("args", "rev");
    model.component("comp2").func("an1").setIndex("argunit", "rpm", 0);
    model.component("comp2").func("an1").set("fununit", "1");
    model.component("comp2").func("an1").setIndex("plotargs", "10000[rpm]", 0, 2);
    model.component("comp2").func().create("an2", "Analytic");
    model.component("comp2").func("an2").label("\u8f6c\u901f\u9010\u6e10\u589e\u5927");
    model.component("comp2").func("an2").set("funcname", "rev_ramp");
    model.component("comp2").func("an2").set("expr", "200+9800*t/20[s]");
    model.component("comp2").func("an2").set("args", "t");
    model.component("comp2").func("an2").setIndex("argunit", "s", 0);
    model.component("comp2").func("an2").set("fununit", "rpm");
    model.component("comp2").func("an2").setIndex("plotargs", "20[s]", 0, 2);

    model.component("comp2").coordSystem().create("pml1", "PML");
    model.component("comp2").coordSystem("pml1").selection().named("geom2_sel5");
    model.component("comp2").coordSystem("pml1").set("stretchingType", "rational");

    model.component("comp2").material().create("mat5", "Common");
    model.component("comp2").material("mat5").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp2").material("mat5").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat5").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp2").material("mat5").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp2").material("mat5").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp2").material("mat5").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp2").material("mat5").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp2").material("mat5").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp2").material("mat5").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp2").material("mat5").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp2").material("mat5").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat5").label("Air");
    model.component("comp2").material("mat5").set("family", "air");
    model.component("comp2").material("mat5").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat5").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp2").material("mat5").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp2").material("mat5").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp2").material("mat5").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp2").material("mat5").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp2").material("mat5").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat5").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp2").material("mat5").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp2").material("mat5").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat5").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat5").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp2").material("mat5").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp2").material("mat5").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat5").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp2").material("mat5").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat5").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp2").material("mat5").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat5").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp2").material("mat5").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp2").material("mat5").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp2").material("mat5").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp2").material("mat5").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp2").material("mat5").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp2").material("mat5").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp2").material("mat5").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp2").material("mat5").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp2").material("mat5").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp2").material("mat5").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat5").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp2").material("mat5").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp2").material("mat5").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp2").material("mat5").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp2").material("mat5").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp2").material("mat5").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat5").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp2").material("mat5").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat5").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp2").material("mat5").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat5").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp2").material("mat5").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp2").material("mat5").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp2").material("mat5").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp2").material("mat5").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp2").material("mat5").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp2").material("mat5").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat5").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp2").material("mat5").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp2").material("mat5").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp2").material("mat5").propertyGroup("def").set("molarmass", "");
    model.component("comp2").material("mat5").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp2").material("mat5").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp2").material("mat5").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp2").material("mat5").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp2").material("mat5").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat5").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat5").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp2").material("mat5").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp2").material("mat5").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat5").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp2").material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp2").material("mat5").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp2").material("mat5").propertyGroup("def").addInput("temperature");
    model.component("comp2").material("mat5").propertyGroup("def").addInput("pressure");
    model.component("comp2").material("mat5").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp2").material("mat5").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat5").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp2").material("mat5").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp2").material("mat5").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp2").material("mat5").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat5").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp2").material("mat5").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp2").material("mat5").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat5").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat5").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp2").material("mat5").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat5").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat5").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp2").material("mat5").propertyGroup("idealGas").addInput("temperature");
    model.component("comp2").material("mat5").propertyGroup("idealGas").addInput("pressure");
    model.component("comp2").material("mat5").materialType("nonSolid");
    model.component("comp2").material().create("mat6", "Common");
    model.component("comp2").material("mat6").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat6").label("Steel AISI 4340");
    model.component("comp2").material("mat6").set("family", "steel");
    model.component("comp2").material("mat6").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat6").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat6").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp2").material("mat6").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp2").material("mat6").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp2").material("mat6").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat6").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp2").material("mat6").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp2").material("mat6").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat6").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp2").material("mat6").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp2").material().create("mat7", "Common");
    model.component("comp2").material("mat7").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat7").label("Aluminum 6063-T83");
    model.component("comp2").material("mat7").set("family", "aluminum");
    model.component("comp2").material("mat7").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat7").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat7").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.030e7[S/m]", "0", "0", "0", "3.030e7[S/m]", "0", "0", "0", "3.030e7[S/m]"});
    model.component("comp2").material("mat7").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23.4e-6[1/K]", "0", "0", "0", "23.4e-6[1/K]", "0", "0", "0", "23.4e-6[1/K]"});
    model.component("comp2").material("mat7").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp2").material("mat7").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat7").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp2").material("mat7").propertyGroup("def")
         .set("thermalconductivity", new String[]{"201[W/(m*K)]", "0", "0", "0", "201[W/(m*K)]", "0", "0", "0", "201[W/(m*K)]"});
    model.component("comp2").material("mat7").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp2").material("mat7").propertyGroup("Enu").set("E", "69[GPa]");
    model.component("comp2").material("mat7").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp2").material("mat5").selection().named("geom2_comsel1");
    model.component("comp2").material("mat6").selection().named("geom2_sel1");
    model.component("comp2").material("mat7").selection().named("geom2_sel3");
    model.component("comp2").material().create("mat8", "Common");
    model.component("comp2").material("mat8").label("\u7ebf\u5708");
    model.component("comp2").material("mat8").selection().named("geom2_sel2");
    model.component("comp2").material("mat8").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp2").material("mat8").propertyGroup("Enu").set("E", new String[]{"5[GPa]"});
    model.component("comp2").material("mat8").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp2").material("mat8").propertyGroup("def").set("density", new String[]{"8960[kg/m^3]"});

    model.study().create("std3");
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").setSolveFor("/physics/mf", false);
    model.study("std3").feature("freq").setSolveFor("/physics/wb", false);
    model.study("std3").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std3").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std3").feature("freq").setSolveFor("/multiphysics/asb1", true);
    model.study("std3").feature("freq")
         .set("plist", "range(360[deg]/theta*rpm_idle*harm_exc,fdelta,min(fmax,rpm_max/rpm0*f0*harm_exc)) min(fmax,rpm_max/rpm0*f0*harm_exc)");
    model.study("std3").feature("freq").setEntry("outputmap", "acpr", "selection");
    model.study("std3").feature("freq").setEntry("outputselectionmap", "acpr", "geom2_sel7");
    model.study("std3").feature("freq").setEntry("outputmap", "solid", "selection");
    model.study("std3").feature("freq").setEntry("outputselectionmap", "solid", "geom2_sel7");
    model.study("std3")
         .label("\u7814\u7a76 3 - \u632f\u52a8\u58f0\u5b66\u5206\u6790 - \u6240\u6709\u8c10\u6ce2\u548c\u9891\u7387");
    model.study("std3").setGenPlots(false);
    model.study("std3").create("param", "Parametric");
    model.study("std3").feature("param").setIndex("pname", "a_coil", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "m^2", 0);
    model.study("std3").feature("param").setIndex("pname", "a_coil", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "m^2", 0);
    model.study("std3").feature("param").setIndex("pname", "harm_exc", 0);
    model.study("std3").feature("param").setIndex("plistarr", "range(1,1,n_harmonics)", 0);
    model.study("std3").feature("param").setIndex("punit", "", 0);
    model.study("std3").showAutoSequences("all");

    model.sol("sol4").feature("s1").create("se1", "Segregated");
    model.sol("sol4").feature("s1").feature("se1").set("segterm", "iter");
    model.sol("sol4").feature("s1").feature("se1").feature("ssDef")
         .set("segvar", new String[]{"comp1_A", "comp1_A_lm", "comp1_Fx", "comp1_Fy", "comp2_u2"});
    model.sol("sol4").feature("s1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol4").feature("s1").feature("se1").feature("ss1").set("segvar", new String[]{"comp2_p"});
    model.sol("sol4").feature("s1").feature("d1").set("linsolver", "pardiso");

    model.study("std3").setGenConv(false);
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol5");
    model.sol("sol5").study("std3");
    model.sol("sol5").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol5");
    model.batch("p1").run("compute");

    model.result().dataset().remove("dset4");
    model.result().dataset().remove("dset5");
    model.result().dataset().remove("dset6");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u574e\u8d1d\u5c14\u56fe\uff0c\u7b2c\u4e00\u4e2a\u9ea6\u514b\u98ce");
    model.result("pg6").set("data", "dset7");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "acpr.freq", 0);
    model.result("pg6").feature("glob1").setIndex("unit", "Hz", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "\u9891\u7387", 0);
    model.result("pg6").feature("glob1").set("xdata", "expr");
    model.result("pg6").feature("glob1").set("xdataexpr", "rpm");
    model.result("pg6").feature("glob1").set("xdataunit", "RPM");
    model.result("pg6").feature("glob1").set("linewidth", 3);
    model.result("pg6").feature("glob1").set("legend", false);
    model.result("pg6").feature("glob1").create("col1", "Color");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").feature("col1").set("expr", "spl_mic1");
    model.result("pg6").feature("glob1").feature("col1").set("unit", "dB");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").feature("col1").set("rangecoloractive", true);
    model.result("pg6").feature("glob1").feature("col1").set("rangecolormin", 55);
    model.result("pg6").feature("glob1").feature("col1").set("rangecolormax", 80);
    model.result("pg6").run();
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u574e\u8d1d\u5c14\u56fe\uff0c\u7b2c\u4e8c\u4e2a\u9ea6\u514b\u98ce");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").feature("col1").set("expr", "spl_mic2");
    model.result("pg7").feature("glob1").feature("col1").set("rangecolormax", 100);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").run();
    model.result("pg8").label("\u5916\u573a\u58f0\u538b\u7ea7 (acpr)");
    model.result("pg8").set("looplevel", new int[]{47, 3});
    model.result("pg8").set("showlegendsmaxmin", true);
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").create("rp1", "RadiationPattern");
    model.result("pg8").feature("rp1").set("expr", "acpr.efc1.Lp_pext/80");
    model.result("pg8").feature("rp1").set("descractive", true);
    model.result("pg8").feature("rp1").set("descr", "\u5916\u573a\u58f0\u538b\u7ea7");
    model.result("pg8").feature("rp1").set("useradiusascolor", false);
    model.result("pg8").feature("rp1").set("rangecoloractive", true);
    model.result("pg8").feature("rp1").set("rangecolormin", 65);
    model.result("pg8").feature("rp1").set("rangecolormax", 75);
    model.result("pg8").feature("rp1").set("thetadisc", 160);
    model.result("pg8").feature("rp1").set("phidisc", 240);
    model.result("pg8").feature("rp1").set("anglerestr", "manual");
    model.result("pg8").feature("rp1").set("thetarange", 90);
    model.result("pg8").feature("rp1").set("phimin", -90);
    model.result("pg8").feature("rp1").set("phirange", 270);
    model.result("pg8").feature("rp1").set("sphere", "manual");
    model.result("pg8").feature("rp1").set("radius", 0.5);
    model.result("pg8").feature("rp1").set("grid", "fine");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").set("edges", true);
    model.result("pg8").run();

    model.view("view6").set("showgrid", false);

    model.result("pg6").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg6");
    model.nodeGroup("grp2").add("plotgroup", "pg7");
    model.nodeGroup("grp2").add("plotgroup", "pg8");
    model.nodeGroup("grp2")
         .label("\u632f\u52a8\u58f0\u5b66\u7ed3\u679c - \u6240\u6709\u8c10\u6ce2\u548c\u9891\u7387");

    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u538b\u529b - \u8f6c\u901f - \u4e00\u6b21\u8c10\u6ce2");
    model.result().evaluationGroup("eg1").set("data", "dset7");
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "manual", 1);
    model.result().evaluationGroup("eg1").setIndex("looplevel", new int[]{1}, 1);
    model.result().evaluationGroup("eg1").set("includeparameters", false);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "rpm", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "RPM", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u6fc0\u52b1\u8c10\u6ce2\u533a", 0);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "subst(real(acpr.efc1.pext),x,0.1[m],y,y_mic2,z,0)", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "Pa", 1);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u9ea6\u514b\u98ce 2 \u5904\u7684\u7edd\u5bf9\u538b\u529b - \u5de6\u4fa7\u5b9e\u90e8", 1);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "subst(imag(acpr.efc1.pext),x,0.1[m],y,y_mic2,z,0)", 2);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "Pa", 2);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u9ea6\u514b\u98ce 2 \u5904\u7684\u7edd\u5bf9\u538b\u529b - \u5de6\u4fa7\u865a\u90e8", 2);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "subst(real(acpr.efc1.pext),x,-0.1[m],y,y_mic2,z,0)", 3);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "Pa", 3);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u9ea6\u514b\u98ce 2 \u5904\u7684\u7edd\u5bf9\u538b\u529b - \u53f3\u4fa7\u5b9e\u90e8", 3);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "subst(imag(acpr.efc1.pext),x,-0.1[m],y,y_mic2,z,0)", 4);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "Pa", 4);
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("descr", "\u9ea6\u514b\u98ce 2 \u5904\u7684\u7edd\u5bf9\u538b\u529b - \u53f3\u4fa7\u865a\u90e8", 4);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().duplicate("eg2", "eg1");
    model.result().evaluationGroup("eg2").label("\u538b\u529b - \u8f6c\u901f - \u4e8c\u6b21\u8c10\u6ce2");
    model.result().evaluationGroup("eg2").setIndex("looplevel", new int[]{2}, 1);
    model.result().evaluationGroup("eg2").run();
    model.result().evaluationGroup().duplicate("eg3", "eg2");
    model.result().evaluationGroup("eg3").label("\u538b\u529b - \u8f6c\u901f - \u4e09\u6b21\u8c10\u6ce2");
    model.result().evaluationGroup("eg3").setIndex("looplevel", new int[]{3}, 1);
    model.result().evaluationGroup("eg3").run();
    model.result().evaluationGroup().duplicate("eg4", "eg3");
    model.result().evaluationGroup("eg4").label("\u538b\u529b - \u8f6c\u901f - \u56db\u6b21\u8c10\u6ce2");
    model.result().evaluationGroup("eg4").setIndex("looplevel", new int[]{4}, 1);
    model.result().evaluationGroup("eg4").run();
    model.result().evaluationGroup().duplicate("eg5", "eg4");
    model.result().evaluationGroup("eg5").label("\u538b\u529b - \u8f6c\u901f - \u4e94\u6b21\u8c10\u6ce2");
    model.result().evaluationGroup("eg5").setIndex("looplevel", new int[]{5}, 1);
    model.result().evaluationGroup("eg5").run();
    model.result().evaluationGroup().duplicate("eg6", "eg5");
    model.result().evaluationGroup("eg6").label("\u538b\u529b - \u8f6c\u901f - \u516d\u6b21\u8c10\u6ce2");
    model.result().evaluationGroup("eg6").setIndex("looplevel", new int[]{6}, 1);
    model.result().evaluationGroup("eg6").run();
    model.result().evaluationGroup().duplicate("eg7", "eg6");
    model.result().evaluationGroup("eg7").label("\u538b\u529b - \u8f6c\u901f - \u4e03\u6b21\u8c10\u6ce2");
    model.result().evaluationGroup("eg7").setIndex("looplevel", new int[]{7}, 1);
    model.result().evaluationGroup("eg7").run();

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").set("type", "evaluationgroup");
    model.nodeGroup("grp3").add("evaluationgroup", "eg1");
    model.nodeGroup("grp3").add("evaluationgroup", "eg2");
    model.nodeGroup("grp3").add("evaluationgroup", "eg3");
    model.nodeGroup("grp3").add("evaluationgroup", "eg4");
    model.nodeGroup("grp3").add("evaluationgroup", "eg5");
    model.nodeGroup("grp3").add("evaluationgroup", "eg6");
    model.nodeGroup("grp3").add("evaluationgroup", "eg7");
    model.nodeGroup("grp3").label("\u58f0\u4fe1\u53f7");

    model.component("comp2").func().create("int1", "Interpolation");
    model.component("comp2").func("int1").set("source", "resultTable");
    model.component("comp2").func("int1").setEntry("columnType", "col3", "value");
    model.component("comp2").func("int1").setEntry("columnType", "col4", "value");
    model.component("comp2").func("int1").setEntry("columnType", "col5", "value");
    model.component("comp2").func("int1").setIndex("argunit", "RPM", 0);
    model.component("comp2").func("int1").setEntry("funcnames", "col2", "real1_l");
    model.component("comp2").func("int1").setIndex("fununit", "Pa", 0);
    model.component("comp2").func("int1").setEntry("funcnames", "col3", "imag1_l");
    model.component("comp2").func("int1").setIndex("fununit", "Pa", 1);
    model.component("comp2").func("int1").setEntry("funcnames", "col4", "real1_r");
    model.component("comp2").func("int1").setIndex("fununit", "Pa", 2);
    model.component("comp2").func("int1").setEntry("funcnames", "col5", "imag1_r");
    model.component("comp2").func("int1").setIndex("fununit", "Pa", 3);
    model.component("comp2").func("int1").set("interp", "piecewisecubic");
    model.component("comp2").func("int1").set("extrap", "value");
    model.component("comp2").func().duplicate("int2", "int1");
    model.component("comp2").func("int2").set("resultTable", "evalGroup:eg2");
    model.component("comp2").func("int2").setEntry("funcnames", "col2", "real2_l");
    model.component("comp2").func("int2").setEntry("funcnames", "col3", "imag2_l");
    model.component("comp2").func("int2").setEntry("funcnames", "col4", "real2_r");
    model.component("comp2").func("int2").setEntry("funcnames", "col5", "imag2_r");
    model.component("comp2").func().duplicate("int3", "int2");
    model.component("comp2").func("int3").set("resultTable", "evalGroup:eg3");
    model.component("comp2").func("int3").setEntry("funcnames", "col2", "real3_l");
    model.component("comp2").func("int3").setEntry("funcnames", "col3", "imag3_l");
    model.component("comp2").func("int3").setEntry("funcnames", "col4", "real3_r");
    model.component("comp2").func("int3").setEntry("funcnames", "col5", "imag3_r");
    model.component("comp2").func().duplicate("int4", "int3");
    model.component("comp2").func("int4").set("resultTable", "evalGroup:eg4");
    model.component("comp2").func("int4").setEntry("funcnames", "col2", "real4_l");
    model.component("comp2").func("int4").setEntry("funcnames", "col3", "imag4_l");
    model.component("comp2").func("int4").setEntry("funcnames", "col4", "real4_r");
    model.component("comp2").func("int4").setEntry("funcnames", "col5", "imag4_r");
    model.component("comp2").func().duplicate("int5", "int4");
    model.component("comp2").func("int5").set("resultTable", "evalGroup:eg5");
    model.component("comp2").func("int5").setEntry("funcnames", "col2", "real5_l");
    model.component("comp2").func("int5").setEntry("funcnames", "col3", "imag5_l");
    model.component("comp2").func("int5").setEntry("funcnames", "col4", "real5_r");
    model.component("comp2").func("int5").setEntry("funcnames", "col5", "imag5_r");
    model.component("comp2").func().duplicate("int6", "int5");
    model.component("comp2").func("int6").set("resultTable", "evalGroup:eg6");
    model.component("comp2").func("int6").setEntry("funcnames", "col2", "real6_l");
    model.component("comp2").func("int6").setEntry("funcnames", "col3", "imag6_l");
    model.component("comp2").func("int6").setEntry("funcnames", "col4", "real6_r");
    model.component("comp2").func("int6").setEntry("funcnames", "col5", "imag6_r");
    model.component("comp2").func().duplicate("int7", "int6");
    model.component("comp2").func("int7").set("resultTable", "evalGroup:eg7");
    model.component("comp2").func("int7").setEntry("funcnames", "col2", "real7_l");
    model.component("comp2").func("int7").setEntry("funcnames", "col3", "imag7_l");
    model.component("comp2").func("int7").setEntry("funcnames", "col4", "real7_r");
    model.component("comp2").func("int7").setEntry("funcnames", "col5", "imag7_r");

    model.nodeGroup().create("grp4", "Definitions", "comp2");
    model.nodeGroup("grp4").set("type", "func");
    model.nodeGroup("grp4").placeAfter("func", "an2");
    model.nodeGroup("grp4").add("func", "int1");
    model.nodeGroup("grp4").add("func", "int2");
    model.nodeGroup("grp4").add("func", "int3");
    model.nodeGroup("grp4").add("func", "int4");
    model.nodeGroup("grp4").add("func", "int5");
    model.nodeGroup("grp4").add("func", "int6");
    model.nodeGroup("grp4").add("func", "int7");
    model.nodeGroup("grp4").label("\u58f0\u4fe1\u53f7");

    model.study().create("std4");
    model.study("std4").create("freq", "Frequency");
    model.study("std4").feature("freq").setSolveFor("/physics/mf", false);
    model.study("std4").feature("freq").setSolveFor("/physics/wb", false);
    model.study("std4").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std4").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std4").feature("freq").setSolveFor("/multiphysics/asb1", true);
    model.study("std4").feature("freq").set("plist", 2360);
    model.study("std4")
         .label("\u7814\u7a76 4 - \u632f\u52a8\u58f0\u5b66\u5206\u6790 - \u4e09\u6b21\u8c10\u6ce2 2360 Hz");
    model.study("std4").setGenPlots(false);
    model.study("std4").setGenConv(false);
    model.study("std4").showAutoSequences("all");

    model.sol("sol13").feature("s1").create("se1", "Segregated");
    model.sol("sol13").feature("s1").feature("se1").set("segterm", "iter");
    model.sol("sol13").feature("s1").feature("se1").feature("ssDef")
         .set("segvar", new String[]{"comp1_A", "comp1_A_lm", "comp1_Fx", "comp1_Fy", "comp2_u2"});
    model.sol("sol13").feature("s1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol13").feature("s1").feature("se1").feature("ss1").set("segvar", new String[]{"comp2_p"});

    model.study("std4").createAutoSequences("all");

    model.sol("sol13").runAll();

    model.result().dataset().remove("dset8");
    model.result().dataset().create("grid1", "Grid1D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").set("data", "dset9");
    model.result().dataset("grid1").set("par1", "tt");
    model.result().dataset("grid1").set("parmax1", 20);
    model.result().dataset("grid1").set("res1", 960000);
    model.result().dataset("grid1").set("adaptive", false);
    model.result().create("pg9", "PlotGroup1D");

    model.nodeGroup("grp2").add("plotgroup", "pg9");

    model.result("pg9").run();
    model.result("pg9").label("\u58f0\u4fe1\u53f7");
    model.result("pg9").set("data", "grid1");
    model.result("pg9").set("titletype", "label");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u4fe1\u53f7 (Pa)");
    model.result("pg9").create("lngr1", "LineGraph");
    model.result("pg9").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg9").feature("lngr1").set("linewidth", "preference");
    model.result("pg9").feature("lngr1").label("\u5de6\u58f0\u9053");
    model.result("pg9").feature("lngr1").set("descractive", true);
    model.result("pg9").feature("lngr1").set("descr", "\u5de6\u58f0\u9053");
    model.result("pg9").feature("lngr1")
         .set("expr", "real((real1_l(rev_ramp(tt))+i*imag1_l(rev_ramp(tt)))*exp(i*(rev_ramp(tt))*f0/rpm0*1*pi*tt)+(real2_l(rev_ramp(tt))+i*imag2_l(rev_ramp(tt)))*exp(i*(rev_ramp(tt))*f0/rpm0*2*pi*tt)+(real3_l(rev_ramp(tt))+i*imag3_l(rev_ramp(tt)))*exp(i*(rev_ramp(tt))*f0/rpm0*3*pi*tt)+(real4_l(rev_ramp(tt))+i*imag4_l(rev_ramp(tt)))*exp(i*(rev_ramp(tt))*f0/rpm0*4*pi*tt)+(real5_l(rev_ramp(tt))+i*imag5_l(rev_ramp(tt)))*exp(i*(rev_ramp(tt))*f0/rpm0*5*pi*tt)+(real6_l(rev_ramp(tt))+i*imag6_l(rev_ramp(tt)))*exp(i*(rev_ramp(tt))*f0/rpm0*6*pi*tt)+(real7_l(rev_ramp(tt))+i*imag7_l(rev_ramp(tt)))*exp(i*(rev_ramp(tt))*f0/rpm0*7*pi*tt))");
    model.result("pg9").feature("lngr1").set("xdata", "expr");
    model.result("pg9").feature("lngr1").set("xdataexpr", "tt[s/m]");
    model.result("pg9").feature("lngr1").set("xdatadescractive", true);
    model.result("pg9").feature("lngr1").set("xdatadescr", "\u65f6\u95f4");
    model.result("pg9").feature("lngr1").set("legend", true);
    model.result("pg9").feature("lngr1").set("autosolution", false);

    return model;
  }

  public static Model run4(Model model) {
    model.result("pg9").feature("lngr1").set("autoplotlabel", true);
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("lngr2", "lngr1");
    model.result("pg9").run();
    model.result("pg9").feature("lngr2").set("descr", "\u53f3\u58f0\u9053");
    model.result("pg9").feature("lngr2")
         .set("expr", "real((real1_r(rev_ramp(tt))+i*imag1_r(rev_ramp(tt)))*exp(i*(rev_ramp(tt))*f0/rpm0*1*pi*tt)+(real2_r(rev_ramp(tt))+i*imag2_r(rev_ramp(tt)))*exp(i*(rev_ramp(tt))*f0/rpm0*2*pi*tt)+(real3_r(rev_ramp(tt))+i*imag3_r(rev_ramp(tt)))*exp(i*(rev_ramp(tt))*f0/rpm0*3*pi*tt)+(real4_r(rev_ramp(tt))+i*imag4_r(rev_ramp(tt)))*exp(i*(rev_ramp(tt))*f0/rpm0*4*pi*tt)+(real5_r(rev_ramp(tt))+i*imag5_r(rev_ramp(tt)))*exp(i*(rev_ramp(tt))*f0/rpm0*5*pi*tt)+(real6_r(rev_ramp(tt))+i*imag6_r(rev_ramp(tt)))*exp(i*(rev_ramp(tt))*f0/rpm0*6*pi*tt)+(real7_r(rev_ramp(tt))+i*imag7_r(rev_ramp(tt)))*exp(i*(rev_ramp(tt))*f0/rpm0*7*pi*tt))");
    model.result("pg9").feature("lngr2").label("\u53f3\u58f0\u9053");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().export().create("plot1", "pg9", "lngr1", "Plot");
    model.result("pg9").feature("lngr2").active(false);
    model.result("pg9").feature("lngr2").active(true);
    model.result().export("plot1").label("\u5de6\u58f0\u9053\u58f0\u4fe1\u53f7");
    model.result().export("plot1").set("exporttype", "wav");
    model.result().export("plot1").set("filename", "electric_motor_noise_left.wav");
    model.result("pg9").run();
    model.result().export().create("plot2", "pg9", "lngr2", "Plot");
    model.result("pg9").feature("lngr1").active(false);
    model.result("pg9").feature("lngr1").active(true);
    model.result().export("plot2").label("\u53f3\u58f0\u9053\u58f0\u4fe1\u53f7");
    model.result().export("plot2").set("exporttype", "wav");
    model.result().export("plot2").set("filename", "electric_motor_noise_right.wav");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").run();
    model.result("pg10").label("\u4f4d\u79fb\u548c\u58f0\u538b");
    model.result("pg10").set("data", "dset9");
    model.result("pg10").set("edges", false);
    model.result("pg10").set("showlegendsmaxmin", true);
    model.result("pg10").set("showlegendsunit", true);
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", "solid.disp");
    model.result("pg10").feature("surf1").create("def1", "Deform");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").feature("surf1").create("filt1", "Filter");
    model.result("pg10").run();
    model.result("pg10").feature("surf1").feature("filt1").set("expr", "x>-40.5[mm]");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").create("line1", "Line");
    model.result("pg10").feature("line1").set("expr", "0");
    model.result("pg10").feature("line1").set("titletype", "none");
    model.result("pg10").feature("line1").set("coloring", "uniform");
    model.result("pg10").feature("line1").set("color", "black");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").feature("line1").feature().copy("def1", "pg10/surf1/def1");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").feature("line1").feature().copy("filt1", "pg10/surf1/filt1");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").feature("line1").set("inheritplot", "surf1");
    model.result("pg10").feature("line1").set("inheritcolor", false);
    model.result("pg10").feature("line1").set("inheritrange", false);
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").create("iso1", "Isosurface");
    model.result("pg10").feature("iso1").set("number", 11);
    model.result("pg10").feature("iso1").create("sel1", "Selection");
    model.result("pg10").feature("iso1").feature("sel1").selection().set(9);
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").feature("iso1").feature().copy("filt1", "pg10/line1/filt1");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").run();
    model.result("pg11").label("\u58f0\u538b\u7ea7\u548c\u8f90\u5c04\u65b9\u5411\u56fe");
    model.result("pg11").set("data", "dset9");
    model.result("pg11").set("view", "new");
    model.result("pg11").selection().geom("geom2", 3);
    model.result("pg11").selection().named("geom2_unisel1");
    model.result("pg11").set("edges", false);
    model.result("pg11").set("showlegendsunit", true);
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", "acpr.Lp");
    model.result("pg11").feature("surf1").set("rangecoloractive", true);
    model.result("pg11").feature("surf1").set("rangecolormin", 80);
    model.result("pg11").feature("surf1").set("rangecolormax", 115);
    model.result("pg11").feature("surf1").create("def1", "Deform");
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").create("line1", "Line");
    model.result("pg11").feature("line1").set("expr", "0");
    model.result("pg11").feature("line1").set("titletype", "none");
    model.result("pg11").feature("line1").set("coloring", "uniform");
    model.result("pg11").feature("line1").set("color", "black");
    model.result("pg11").feature("line1").set("inheritplot", "surf1");
    model.result("pg11").feature("line1").set("inheritcolor", false);
    model.result("pg11").feature("line1").set("inheritrange", false);
    model.result("pg11").feature("line1").create("def1", "Deform");
    model.result("pg11").run();
    model.result("pg8").run();
    model.result("pg11").run();
    model.result("pg11").feature().copy("rp1", "pg8/rp1");
    model.result("pg11").run();
    model.result("pg11").feature("rp1").set("rangecolormax", 85);
    model.result("pg11").feature("rp1").set("phirange", 360);
    model.result("pg11").feature("rp1").create("tran1", "Transparency");
    model.result("pg11").run();
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").run();
    model.result("pg12").label("\u8fb9\u754c\u8f7d\u8377");
    model.result("pg12").set("data", "dset9");
    model.result("pg12").selection().geom("geom2", 3);
    model.result("pg12").selection().geom("geom2", 3);
    model.result("pg12").selection()
         .set(13, 14, 15, 16, 17, 19, 20, 21, 22, 24, 25, 26, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 45, 46, 47, 49, 50, 51, 52);
    model.result("pg12").selection().named("geom2_unisel1");
    model.result("pg12").set("applyselectiontodatasetedges", true);
    model.result("pg12").set("showlegendsunit", true);
    model.result("pg12").create("arws1", "ArrowSurface");
    model.result("pg12").feature("arws1").set("expr", new String[]{"solid.bndl1.fax", "v2", "w2"});
    model.result("pg12").feature("arws1").setIndex("expr", "solid.bndl1.fay", 1);
    model.result("pg12").feature("arws1").setIndex("expr", "solid.bndl1.faz", 2);
    model.result("pg12").feature("arws1").set("placement", "gausspoints");
    model.result("pg12").feature("arws1").create("col1", "Color");
    model.result("pg12").run();
    model.result("pg12").feature("arws1").feature("col1").set("expr", "comp2.solid.bndl1.famag");
    model.result("pg12").feature("arws1").feature("col1").set("coloring", "gradient");
    model.result("pg12").feature("arws1").feature("col1").set("topcolor", "red");
    model.result("pg10").set("applyselectiontodatasetedges", false);
    model.result("pg10").run();

    model.nodeGroup().create("grp5", "Results");
    model.nodeGroup("grp5").set("type", "plotgroup");
    model.nodeGroup().move("grp5", 3);
    model.nodeGroup("grp5").add("plotgroup", "pg10");
    model.nodeGroup("grp5").add("plotgroup", "pg11");
    model.nodeGroup("grp5").add("plotgroup", "pg12");
    model.nodeGroup("grp5").label("\u632f\u52a8\u58f0\u5b66\u7ed3\u679c - \u4e09\u6b21\u8c10\u6ce2 2360 Hz");

    model.result("pg11").run();

    model.title("\u7535\u673a\u566a\u58f0\uff1a\u6c38\u78c1\u540c\u6b65\u7535\u673a");

    model
         .description("\u672c\u6a21\u578b\u5206\u6790\u7535\u673a\u5728\u4e0d\u540c\u8f6c\u901f\u4e0b\u8fd0\u884c\u65f6\u4ea7\u751f\u7684\u566a\u58f0\uff0c\u5206\u6790\u7684\u7535\u673a\u7c7b\u578b\u4e3a\u6c38\u78c1\u540c\u6b65\u7535\u673a (PMSM)\uff0c\u5176\u4e2d\u8f6c\u5b50\u91c7\u7528\u6c38\u78c1\u4f53\uff0c\u5e76\u4f7f\u7528\u901a\u8fc7\u5b9a\u5b50\u7684\u53d8\u9891\u7535\u6d41\u4ea7\u751f\u626d\u77e9\u3002\u7535\u673a\u8fd0\u884c\u8fc7\u7a0b\u4e2d\u4ea7\u751f\u7684\u7535\u78c1\u529b\u4e0d\u4ec5\u5728\u6fc0\u52b1\u9891\u7387\u4e0b\u4ea7\u751f\u632f\u52a8\uff0c\u800c\u4e14\u5728\u66f4\u9ad8\u7684\u9891\u7387\u6216\u8c10\u6ce2\u4e0a\u4e5f\u4ea7\u751f\u632f\u52a8\u3002\u672c\u4f8b\u91c7\u7528\u77ac\u6001\u5206\u6790\u5728\u65f6\u57df\u4e2d\u786e\u5b9a\u7535\u78c1\u529b\uff0c\u5e76\u4f7f\u7528\u5085\u91cc\u53f6\u53d8\u6362\u6765\u786e\u5b9a\u9891\u57df\u4e2d\u7684\u8d21\u732e\uff0c\u8ba1\u7b97\u4e86\u6c38\u78c1\u540c\u6b65\u7535\u673a\u5916\u58f3\u7684\u632f\u52a8\u58f0\u54cd\u5e94\u53ca\u5176\u58f0\u8f90\u5c04\u3002\u751f\u6210\u7684\u574e\u8d1d\u5c14\u56fe\u663e\u793a\u4e86\u6c38\u78c1\u540c\u6b65\u7535\u673a\u5728\u5404\u79cd\u8f6c\u901f\u4e0b\uff0c\u5176\u58f0\u5b66\u54cd\u5e94\u7684\u4e3b\u8981\u8c10\u6ce2\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();

    model.label("electric_motor_noise_pmsm.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    run4(model);
  }

}
