/*
 * dot_in_well_solar_cell.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:51 by COMSOL 6.3.0.290. */
public class dot_in_well_solar_cell {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Semiconductor_Module\\Photonic_Devices_and_Sensors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("semi", "Semiconductor", "geom1");

    model.study().create("std1");
    model.study("std1").create("semie", "SemiconductorEquilibrium");
    model.study("std1").feature("semie").set("ftplistmethod", "manual");
    model.study("std1").feature("semie").set("solnum", "auto");
    model.study("std1").feature("semie").set("notsolnum", "auto");
    model.study("std1").feature("semie").set("outputmap", new String[]{});
    model.study("std1").feature("semie").set("ngenAUX", "1");
    model.study("std1").feature("semie").set("goalngenAUX", "1");
    model.study("std1").feature("semie").set("ngenAUX", "1");
    model.study("std1").feature("semie").set("goalngenAUX", "1");
    model.study("std1").feature("semie").setSolveFor("/physics/semi", true);

    model.component("comp1").geom("geom1").lengthUnit("nm");

    model.param().label("\u53c2\u6570 1 - \u51e0\u4f55\u5f62\u72b6 & QW\u3001QD");
    model.param("default").setShowInParamSel(false);
    model.param().set("area", "1[cm^2]");
    model.param().descr("area", "\u6a2a\u622a\u9762\u79ef");
    model.param().set("t_well", "16[nm]");
    model.param().descr("t_well", "QW \u7684\u539a\u5ea6");
    model.param().set("t_dot", "4[nm]");
    model.param().descr("t_dot", "QD \u7684\u6709\u6548\u539a\u5ea6");
    model.param().set("t_unit", "t_well+50[nm]");
    model.param().descr("t_unit", "\u91cd\u590d\u5355\u5143\u7684\u539a\u5ea6");
    model.param().set("tau_welltodot", "0.3[ns]");
    model.param().descr("tau_welltodot", "\u4ece QW \u5230 QD \u7684\u8dc3\u8fc1\u5bff\u547d");
    model.param().set("alpha_well", "7000[cm^-1]");
    model.param().descr("alpha_well", "QW \u7684\u5438\u6536\u7cfb\u6570");
    model.param().set("alpha_dot", "1000[cm^-1]");
    model.param().descr("alpha_dot", "QD \u7684\u5438\u6536\u7cfb\u6570");
    model.param().set("N_dot", "2e10[cm^-2]/t_well");
    model.param().descr("N_dot", "QD \u6001\u7684\u6709\u6548\u5bc6\u5ea6");
    model.param().set("N_well", "N_dot*10");
    model.param().descr("N_well", "QW \u6001\u7684\u731c\u6d4b\u5bc6\u5ea6");
    model.param().set("tau_thermal", "50[ms]");
    model.param().descr("tau_thermal", "\u4ece QW \u5230\u5bfc\u5e26\u7684\u70ed\u6fc0\u52b1\u5bff\u547d");
    model.param().set("tau_dot", "1[ms]");
    model.param().descr("tau_dot", "\u4ece QD \u5230\u4ef7\u5e26\u7684\u8dc3\u8fc1\u5bff\u547d");
    model.param().set("tau_well", "10[ns]");
    model.param().descr("tau_well", "\u4ece QW \u5230\u4ef7\u5e26\u7684\u8dc3\u8fc1\u731c\u6d4b\u5bff\u547d");
    model.param().set("T0", "300[K]");
    model.param().descr("T0", "\u6e29\u5ea6");
    model.param().set("Vth", "k_B_const*T0/e_const");
    model.param().descr("Vth", "\u70ed\u7535\u538b");
    model.param().set("gD", "1");
    model.param().descr("gD", "\u7b80\u5e76\u56e0\u5b50");
    model.param().set("DeltaE_well", "220[mV]");
    model.param().descr("DeltaE_well", "\u6765\u81ea\u5bfc\u5e26\u8fb9\u7684\u6709\u6548 QW \u80fd\u7ea7");
    model.param().set("DeltaE_dot", "600[mV]+DeltaE_well");
    model.param().descr("DeltaE_dot", "\u6765\u81ea\u5bfc\u5e26\u8fb9\u7684\u6709\u6548 QD \u80fd\u7ea7");
    model.param().set("gamma", "1/tau_thermal*gD*exp(DeltaE_well/Vth)");
    model.param().descr("gamma", "\u4ece\u5bfc\u5e26\u5230 QW \u7684\u731c\u6d4b\u8dc3\u8fc1\u7387");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 2 - \u626b\u63a0");
    model.param("par2").set("V0", "0[V]");
    model.param("par2").descr("V0", "\u504f\u538b");
    model.param("par2").set("P0_780", "0[mW/cm^2]");
    model.param("par2").descr("P0_780", "780 nm \u5149\u7684\u8f93\u5165\u529f\u7387");
    model.param("par2").set("P0_1300", "0[mW/cm^2]");
    model.param("par2").descr("P0_1300", "1300 nm \u5149\u7684\u8f93\u5165\u529f\u7387");
    model.param().create("par3");
    model.param("par3").label("\u53c2\u6570 3 - \u5149\u5b66");
    model.param("par3").setShowInParamSel(false);
    model.param("par3").set("alpha_780", "exp(-alpha_well*t_well)");
    model.param("par3")
         .descr("alpha_780", "780 nm \u5149\u88ab\u4e00\u4e2a QW \u5438\u6536\u540e\u5269\u4f59\u7684\u90e8\u5206");
    model.param("par3").set("hv_780", "h_const*c_const/780[nm]");
    model.param("par3").descr("hv_780", "780 nm \u5149\u5b50\u7684\u80fd\u91cf");
    model.param("par3").set("P1_780", "P0_780/hv_780*(1-0.33)");
    model.param("par3")
         .descr("P1_780", "\u7ecf\u8fc7 33% \u7684\u8868\u9762\u53cd\u5c04\u540e\u8fdb\u5165\u7b2c\u4e00\u4e2a QW \u7684 780 nm \u5149\u5b50\u901a\u91cf");
    model.param("par3").set("P2_780", "P1_780*alpha_780");
    model.param("par3")
         .descr("P2_780", "\u88ab\u7b2c\u4e00\u4e2a QW \u5438\u6536\u540e\u8fdb\u5165\u7b2c\u4e8c\u4e2a QW \u7684 780 nm \u5149\u5b50\u901a\u91cf");
    model.param("par3").set("G1_780", "P1_780*(1-alpha_780)/t_well");
    model.param("par3").descr("G1_780", "780 nm \u5149\u5728\u7b2c\u4e00\u4e2a QW \u4e2d\u7684\u751f\u6210\u7387");
    model.param("par3").set("G2_780", "P2_780*(1-alpha_780)/t_well");
    model.param("par3").descr("G2_780", "780 nm \u5149\u5728\u7b2c\u4e8c\u4e2a QW \u4e2d\u7684\u751f\u6210\u7387");
    model.param("par3").set("hv_1300", "h_const*c_const/1300[nm]");
    model.param("par3").descr("hv_1300", "1300 nm \u5149\u5b50\u7684\u80fd\u91cf");
    model.param("par3").set("P1_1300", "P0_1300/hv_1300*(1-0.298)");
    model.param("par3")
         .descr("P1_1300", "\u7ecf\u8fc7 29.8% \u7684\u8868\u9762\u53cd\u5c04\u540e\u8fdb\u5165\u7b2c\u4e00\u4e2a QD \u5c42\u7684 1300 nm \u5149\u5b50\u901a\u91cf");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").label("\u7ebf\u6bb5\u95f4\u9694 1 - \u9876\u90e8");
    model.component("comp1").geom("geom1").feature("i1").set("specify", "len");
    model.component("comp1").geom("geom1").feature("i1").set("left", "-150-540");
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", 150, 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", 540, 1);
    model.component("comp1").geom("geom1").run("i1");
    model.component("comp1").geom("geom1").create("i2", "Interval");
    model.component("comp1").geom("geom1").feature("i2")
         .label("\u7ebf\u6bb5\u95f4\u9694 2 - \u7b2c\u4e00\u4e2a\u5355\u5143");
    model.component("comp1").geom("geom1").feature("i2").setIndex("coord", "t_well", 1);
    model.component("comp1").geom("geom1").feature("i2").setIndex("coord", "t_unit", 2);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u7b2c\u4e00\u4e2a\u5355\u5143");
    model.component("comp1").geom("geom1").feature("i2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("i2");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").label("\u9635\u5217 1 - \u6240\u6709\u5355\u5143");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("arr1").set("size", 2);
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"t_unit"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("i3", "Interval");
    model.component("comp1").geom("geom1").feature("i3").label("\u7ebf\u6bb5\u95f4\u9694 3 - \u5e95\u90e8");
    model.component("comp1").geom("geom1").feature("i3").set("specify", "len");
    model.component("comp1").geom("geom1").feature("i3").set("left", "2*t_unit");
    model.component("comp1").geom("geom1").feature("i3").setIndex("len", 200, 0);
    model.component("comp1").geom("geom1").feature("i3").setIndex("len", 700, 1);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("SemicondMaterial", "SemicondMaterial", "Semiconductor material");
    model.component("comp1").material("mat1").label("Al(x)Ga(1-x)As - Aluminium Gallium Arsenide");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"12.9-2.84*def.x", "0", "0", "0", "12.9-2.84*def.x", "0", "0", "0", "12.9-2.84*def.x"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"(55-212*def.x+248*def.x^2)[W/(m*K)]", "0", "0", "0", "(55-212*def.x+248*def.x^2)[W/(m*K)]", "0", "0", "0", "(55-212*def.x+248*def.x^2)[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "(5320-1560*def.x)[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "(330-120*def.x)[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def").set("x", "0.2");
    model.component("comp1").material("mat1").propertyGroup("def").descr("x", "");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").label("Semiconductor material");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("Eg0", "if(def.x<0.45,(1.424+1.247*def.x),(1.9+0.125*def.x+0.143*def.x^2))[V]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("chi0", "if(def.x<0.45,(4.07-1.1*def.x),(3.64-0.14*def.x))[V]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("Nv", "(T/1[K])^(3/2)*(0.51+0.25*def.x)^(3/2)*4.82e15[1/cm^3]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("Nc", "if(def.x<0.45,((T/1[K])^(3/2)*(0.063+0.083*def.x)^(3/2)*4.82e15[1/cm^3]),((T/1[K])^(3/2)*(0.85-0.14*def.x)^(3/2)*4.82e15[1/cm^3]))");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("mun", "if(def.x<0.45,(8e3-2.2e4*def.x+1e4*def.x^2),(-255+1160*def.x-720*def.x^2))[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial")
         .set("mup", "(370-970*def.x+740*def.x^2)[cm^2/(V*s)]");
    model.component("comp1").material("mat1").propertyGroup("SemicondMaterial").addInput("temperature");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material("mat1").propertyGroup("def").set("x", new String[]{"0.3"});

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u7b2c\u4e00\u4e2a\u9631/\u70b9");
    model.component("comp1").selection("sel1").set(3);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u7b2c\u4e8c\u4e2a\u9631/\u70b9");
    model.component("comp1").selection("sel2").set(5);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u6240\u6709\u9631/\u70b9");
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel2"});

    model.component("comp1").physics("semi").prop("d").set("A", "area");
    model.component("comp1").physics("semi").create("adm1", "AnalyticDopingModel", 1);
    model.component("comp1").physics("semi").feature("adm1").selection().set(1);
    model.component("comp1").physics("semi").feature("adm1").set("NAc", "2e18[1/cm^3]");
    model.component("comp1").physics("semi").create("adm2", "AnalyticDopingModel", 1);
    model.component("comp1").physics("semi").feature("adm2").selection().set(8);
    model.component("comp1").physics("semi").feature("adm2").set("impurityType", "donor");
    model.component("comp1").physics("semi").feature("adm2").set("NDc", "5e16[1/cm^3]");
    model.component("comp1").physics("semi").create("mc1", "MetalContact", 0);
    model.component("comp1").physics("semi").feature("mc1").selection().set(9);
    model.component("comp1").physics("semi").create("mc2", "MetalContact", 0);
    model.component("comp1").physics("semi").feature("mc2").selection().set(1);
    model.component("comp1").physics("semi").feature("mc2").set("V0", "V0");
    model.component("comp1").physics("semi").create("tar1", "TrapAssistedRecombination", 1);
    model.component("comp1").physics("semi").feature("tar1").selection().named("uni1");
    model.component("comp1").physics("semi").feature("tar1").set("IncludeTraps", "ExplicitTraps");
    model.component("comp1").physics("semi").feature("tar1").set("SpecifyTrapSpecies", true);
    model.component("comp1").physics("semi").feature("tar1").create("dtd1", "DiscreteEnergyLevelDomain", 1);
    model.component("comp1").physics("semi").feature("tar1").feature("dtd1")
         .label("\u79bb\u6563\u80fd\u7ea7 - \u9631");
    model.component("comp1").physics("semi").feature("tar1").feature("dtd1").set("TrapType", "NeutralElectron");
    model.component("comp1").physics("semi").feature("tar1").feature("dtd1").set("Nt_dne", "N_well");
    model.component("comp1").physics("semi").feature("tar1").feature("dtd1")
         .set("DistributionCenterPoint", "FromConduction");
    model.component("comp1").physics("semi").feature("tar1").feature("dtd1").set("Et0in", "DeltaE_well");
    model.component("comp1").physics("semi").feature("tar1").feature("dtd1").set("gsd_d", "gD");
    model.component("comp1").physics("semi").feature("tar1").feature("dtd1")
         .set("ElectronCaptureProbabilitySelect", "userdef");
    model.component("comp1").physics("semi").feature("tar1").feature("dtd1").set("Cnin", 0);
    model.component("comp1").physics("semi").feature("tar1").feature("dtd1")
         .set("HoleCaptureProbabilitySelect", "userdef");
    model.component("comp1").physics("semi").feature("tar1").feature("dtd1").set("Cpin", 0);
    model.component("comp1").physics("semi").feature("tar1").feature().duplicate("dtd2", "dtd1");
    model.component("comp1").physics("semi").feature("tar1").feature("dtd2")
         .label("\u79bb\u6563\u80fd\u7ea7 - \u70b9");
    model.component("comp1").physics("semi").feature("tar1").feature("dtd2").set("Nt_dne", "N_dot");
    model.component("comp1").physics("semi").feature("tar1").feature("dtd2").set("Et0in", "DeltaE_dot");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").label("\u79ef\u5206 - \u7b2c\u4e00\u4e2a\u9631/\u70b9");
    model.component("comp1").cpl("intop1").selection().named("sel1");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").label("\u79ef\u5206 - \u7b2c\u4e8c\u4e2a\u9631/\u70b9");
    model.component("comp1").cpl("intop2").selection().named("sel2");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u53d8\u91cf - \u6240\u6709\u9631/\u70b9");
    model.component("comp1").variable("var1").selection().geom("geom1", 1);
    model.component("comp1").variable("var1").selection().named("uni1");
    model.component("comp1").variable("var1").set("ft_well", "semi.tar1.dtd1.ft");
    model.component("comp1").variable("var1").descr("ft_well", "QW \u7684\u5360\u6709\u7387");
    model.component("comp1").variable("var1").set("ft_dot", "semi.tar1.dtd2.ft");
    model.component("comp1").variable("var1").descr("ft_dot", "QD \u7684\u5360\u6709\u7387");
    model.component("comp1").variable("var1").set("n_well", "ft_well*N_well");
    model.component("comp1").variable("var1").descr("n_well", "\u5360\u6709\u7684 QW \u7684\u5bc6\u5ea6");
    model.component("comp1").variable("var1").set("n_dot", "ft_dot*N_dot");
    model.component("comp1").variable("var1").descr("n_dot", "\u5360\u6709\u7684 QD \u7684\u5bc6\u5ea6");
    model.component("comp1").variable("var1").set("alpha1", "exp(-alpha_dot*intop1(n_dot)/N_dot*t_dot/t_well)");
    model.component("comp1").variable("var1")
         .descr("alpha1", "\u88ab\u7b2c\u4e00\u4e2a QD \u5438\u6536\u540e\u5269\u4f59\u7684 1300 nm \u5149\u90e8\u5206");
    model.component("comp1").variable("var1").set("alpha2", "exp(-alpha_dot*intop2(n_dot)/N_dot*t_dot/t_well)");
    model.component("comp1").variable("var1")
         .descr("alpha2", "\u88ab\u7b2c\u4e8c\u4e2a QD \u5438\u6536\u540e\u5269\u4f59\u7684 1300 nm \u5149\u90e8\u5206");
    model.component("comp1").variable("var1").set("P2_1300", "P1_1300*alpha1");
    model.component("comp1").variable("var1")
         .descr("P2_1300", "\u8fdb\u5165\u7b2c\u4e8c\u4e2a QD \u7684 1300 nm \u5149\u5b50\u901a\u91cf");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("\u53d8\u91cf - \u7b2c\u4e00\u4e2a\u9631/\u70b9");
    model.component("comp1").variable("var2").selection().geom("geom1", 1);
    model.component("comp1").variable("var2").selection().named("sel1");
    model.component("comp1").variable("var2").set("G_780", "G1_780");
    model.component("comp1").variable("var2")
         .descr("G_780", "780 nm \u5149\u5728\u7b2c\u4e00\u4e2a QW \u4e2d\u7684\u751f\u6210\u7387");
    model.component("comp1").variable("var2").set("G_1300", "P1_1300*(1-alpha1)/t_well");
    model.component("comp1").variable("var2")
         .descr("G_1300", "1300 nm \u5149\u5728\u7b2c\u4e00\u4e2a QD \u4e2d\u7684\u751f\u6210\u7387");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").label("\u53d8\u91cf - \u7b2c\u4e8c\u4e2a\u9631/\u70b9");
    model.component("comp1").variable("var3").selection().geom("geom1", 1);
    model.component("comp1").variable("var3").selection().named("sel2");
    model.component("comp1").variable("var3")
         .set("G_780", "G2_780", "780 nm \u5149\u5728\u7b2c\u4e00\u4e2a QW \u4e2d\u7684\u751f\u6210\u7387");
    model.component("comp1").variable("var3")
         .descr("G_780", "780 nm \u5149\u5728\u7b2c\u4e8c\u4e2a QW \u4e2d\u7684\u751f\u6210\u7387");
    model.component("comp1").variable("var3")
         .set("G_1300", "P2_1300*(1-alpha2)/t_well", "1300 nm \u5149\u5728\u7b2c\u4e00\u4e2a QD \u4e2d\u7684\u751f\u6210\u7387");
    model.component("comp1").variable("var3")
         .descr("G_1300", "1300 nm \u5149\u5728\u7b2c\u4e8c\u4e2a QD \u4e2d\u7684\u751f\u6210\u7387");

    model.component("comp1").physics("semi").feature("tar1").feature("dtd1")
         .set("Rn_extra", "-n_well/tau_thermal+gamma*semi.N");
    model.component("comp1").physics("semi").feature("tar1").feature("dtd1")
         .set("Rp_extra", "-G_780+n_well/tau_well");
    model.component("comp1").physics("semi").feature("tar1").feature("dtd2").set("Rn_extra", "-G_1300");
    model.component("comp1").physics("semi").feature("tar1").feature("dtd2").set("Rp_extra", "n_dot/tau_dot");
    model.component("comp1").physics("semi").feature("tar1")
         .create("tdld1", "TransitionBetweenDiscreteLevelsDomain", 1);
    model.component("comp1").physics("semi").feature("tar1").feature("tdld1").set("tl2", "dtd1");
    model.component("comp1").physics("semi").feature("tar1").feature("tdld1").set("tl1", "dtd2");
    model.component("comp1").physics("semi").feature("tar1").feature("tdld1").set("tau21", "tau_welltodot");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(2, 3, 4, 5, 6, 7, 8);
    model.component("comp1").mesh("mesh1").feature().duplicate("size2", "size1");
    model.component("comp1").mesh("mesh1").feature().move("size2", 2);
    model.component("comp1").mesh("mesh1").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size2").selection().set(8);
    model.component("comp1").mesh("mesh1").feature("size2").set("hauto", 8);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u5c06 V0 \u4ece 0 \u5347\u81f3 0.5 V\uff0c\u65e0\u5149");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "V0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "V", 0);
    model.study("std1").feature("stat").setIndex("pname", "V0", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "V", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "0 0.1 0.5", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "ddog");
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
    model.result("pg1").feature("lngr1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
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
    model.result("pg1").feature("lngr2").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
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
    model.result("pg1").feature("lngr3").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
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
    model.result("pg1").feature("lngr4").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
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
    model.result("pg2").feature("lngr1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
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
    model.result("pg2").feature("lngr2").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
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
    model.result("pg3").feature("lngr1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
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

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg4");
    model.nodeGroup("grp1").label("\u7814\u7a76 1");

    model.result("pg4").run();
    model.result("pg4").setIndex("looplevelinput", "first", 0);
    model.result("pg4").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");

    return model;
  }

  public static Model run2(Model model) {
    model.study("std2").feature("stat").setSolveFor("/physics/semi", true);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").set("sweeptype", "filled");
    model.study("std2").feature("stat").setIndex("pname", "V0", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "V", 0);
    model.study("std2").feature("stat").setIndex("pname", "V0", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "V", 0);
    model.study("std2").feature("stat").setIndex("plistarr", 0.5, 0);
    model.study("std2").feature("stat").setIndex("pname", "P0_780", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "", 1);
    model.study("std2").feature("stat").setIndex("punit", "W/m^2", 1);
    model.study("std2").feature("stat").setIndex("pname", "P0_780", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "", 1);
    model.study("std2").feature("stat").setIndex("punit", "W/m^2", 1);
    model.study("std2").feature("stat").setIndex("pname", "P0_1300", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "0 2.3 16 56", 1);
    model.study("std2").feature("stat").setIndex("punit", "mW/cm^2", 1);
    model.study("std2").feature("stat").setIndex("pname", "P0_780", 2);
    model.study("std2").feature("stat").setIndex("plistarr", "", 2);
    model.study("std2").feature("stat").setIndex("punit", "W/m^2", 2);
    model.study("std2").feature("stat").setIndex("pname", "P0_780", 2);
    model.study("std2").feature("stat").setIndex("plistarr", "", 2);
    model.study("std2").feature("stat").setIndex("punit", "W/m^2", 2);
    model.study("std2").feature("stat").setIndex("plistarr", "10^range(-3,0.25,1.5)", 2);
    model.study("std2").feature("stat").setIndex("punit", "mW/cm^2", 2);
    model.study("std2").feature("stat").set("useinitsol", true);
    model.study("std2").feature("stat").set("initmethod", "sol");
    model.study("std2").feature("stat").set("initstudy", "std1");
    model.study("std2").feature("stat").set("solnum", 3);
    model.study("std2").label("\u7814\u7a76 2 - \u659c\u5761 P_780");
    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").label("\u80fd\u7ea7 (semi) 1");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "Energy Diagram");
    model.result("pg5").set("ylabel", "Energy (eV)");
    model.result("pg5").feature().create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").label("\u5bfc\u5e26\u80fd\u7ea7");
    model.result("pg5").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr1").set("expr", "semi.Ec_e");
    model.result("pg5").feature("lngr1").set("unit", "eV");
    model.result("pg5").feature("lngr1").set("linecolor", "blue");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "manual");
    model.result("pg5").feature("lngr1").set("legends", new String[]{"Ec"});
    model.result("pg5").feature("lngr1").set("resolution", "norefine");
    model.result("pg5").feature("lngr1").set("smooth", "everywhere");
    model.result("pg5").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr1").set("data", "parent");
    model.result("pg5").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result("pg5").feature().create("lngr2", "LineGraph");
    model.result("pg5").feature("lngr2").label("\u7535\u5b50\u51c6\u8d39\u7c73\u80fd\u7ea7");
    model.result("pg5").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr2").set("expr", "semi.Efn_e");
    model.result("pg5").feature("lngr2").set("unit", "eV");
    model.result("pg5").feature("lngr2").set("linestyle", "dashed");
    model.result("pg5").feature("lngr2").set("linecolor", "black");
    model.result("pg5").feature("lngr2").set("legend", true);
    model.result("pg5").feature("lngr2").set("legendmethod", "manual");
    model.result("pg5").feature("lngr2").set("legends", new String[]{"Efn"});
    model.result("pg5").feature("lngr2").set("resolution", "norefine");
    model.result("pg5").feature("lngr2").set("smooth", "everywhere");
    model.result("pg5").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr2").set("data", "parent");
    model.result("pg5").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr2").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result("pg5").feature().create("lngr3", "LineGraph");
    model.result("pg5").feature("lngr3").label("\u7a7a\u7a74\u51c6\u8d39\u7c73\u80fd\u7ea7");
    model.result("pg5").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr3").set("expr", "semi.Efp_e");
    model.result("pg5").feature("lngr3").set("unit", "eV");
    model.result("pg5").feature("lngr3").set("linestyle", "dotted");
    model.result("pg5").feature("lngr3").set("linecolor", "black");
    model.result("pg5").feature("lngr3").set("legend", true);
    model.result("pg5").feature("lngr3").set("legendmethod", "manual");
    model.result("pg5").feature("lngr3").set("legends", new String[]{"Efp"});
    model.result("pg5").feature("lngr3").set("resolution", "norefine");
    model.result("pg5").feature("lngr3").set("smooth", "everywhere");
    model.result("pg5").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr3").set("data", "parent");
    model.result("pg5").feature("lngr3").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr3").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result("pg5").feature().create("lngr4", "LineGraph");
    model.result("pg5").feature("lngr4").label("\u4ef7\u5e26\u80fd\u7ea7");
    model.result("pg5").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr4").set("expr", "semi.Ev_e");
    model.result("pg5").feature("lngr4").set("unit", "eV");
    model.result("pg5").feature("lngr4").set("linecolor", "green");
    model.result("pg5").feature("lngr4").set("legend", true);
    model.result("pg5").feature("lngr4").set("legendmethod", "manual");
    model.result("pg5").feature("lngr4").set("legends", new String[]{"Ev"});
    model.result("pg5").feature("lngr4").set("resolution", "norefine");
    model.result("pg5").feature("lngr4").set("smooth", "everywhere");
    model.result("pg5").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg5").feature("lngr4").set("data", "parent");
    model.result("pg5").feature("lngr4").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr4").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").label("\u8f7d\u6d41\u5b50\u6d53\u5ea6 (semi) 1");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg6").set("ylabel", "Carrier concentration (1/cm^3)");
    model.result("pg6").set("ylog", true);
    model.result("pg6").feature().create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").label("\u7535\u5b50\u6d53\u5ea6");
    model.result("pg6").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg6").feature("lngr1").set("expr", "semi.N");
    model.result("pg6").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").set("legendmethod", "manual");
    model.result("pg6").feature("lngr1").set("legends", new String[]{"electrons"});
    model.result("pg6").feature("lngr1").set("resolution", "norefine");
    model.result("pg6").feature("lngr1").set("smooth", "everywhere");
    model.result("pg6").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg6").feature("lngr1").set("data", "parent");
    model.result("pg6").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg6").feature("lngr1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result("pg6").feature().create("lngr2", "LineGraph");
    model.result("pg6").feature("lngr2").label("\u7a7a\u7a74\u6d53\u5ea6");
    model.result("pg6").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg6").feature("lngr2").set("expr", "semi.P");
    model.result("pg6").feature("lngr2").set("unit", "1/cm^3");
    model.result("pg6").feature("lngr2").set("legend", true);
    model.result("pg6").feature("lngr2").set("legendmethod", "manual");
    model.result("pg6").feature("lngr2").set("legends", new String[]{"holes"});
    model.result("pg6").feature("lngr2").set("resolution", "norefine");
    model.result("pg6").feature("lngr2").set("smooth", "everywhere");
    model.result("pg6").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg6").feature("lngr2").set("data", "parent");
    model.result("pg6").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg6").feature("lngr2").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").label("\u7535\u52bf (semi) 1");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg7").set("ylabel", "Electric Potential (V)");
    model.result("pg7").feature().create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg7").feature("lngr1").set("expr", "V");
    model.result("pg7").feature("lngr1").set("resolution", "norefine");
    model.result("pg7").feature("lngr1").set("smooth", "everywhere");
    model.result("pg7").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg7").feature("lngr1").set("data", "parent");
    model.result("pg7").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg7").feature("lngr1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").create("lngr2", "LineGraph");
    model.result("pg8").feature("lngr1").selection().all();
    model.result("pg8").feature("lngr1").set("xdataexpr", "X");
    model.result("pg8").feature("lngr1").set("expr", "semi.Nnetdop");
    model.result("pg8").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg8").feature("lngr1").set("linecolor", "red");
    model.result("pg8").feature("lngr1").set("legend", true);
    model.result("pg8").feature("lngr1").set("legendmethod", "manual");
    model.result("pg8").feature("lngr1").set("legends", new String[]{"P \u578b (\u7ea2\u8272)"});
    model.result("pg8").feature("lngr1").feature().create("filt1", "LineGraphFilter");
    model.result("pg8").feature("lngr1").feature("filt1").set("expr", "semi.Na-semi.Nd > 1[1/cm^3]");
    model.result("pg8").feature("lngr2").selection().all();
    model.result("pg8").feature("lngr2").set("xdataexpr", "X");
    model.result("pg8").feature("lngr2").set("expr", "semi.Nnetdop");
    model.result("pg8").feature("lngr2").set("unit", "1/cm^3");
    model.result("pg8").feature("lngr2").set("linecolor", "blue");
    model.result("pg8").feature("lngr2").set("legend", true);
    model.result("pg8").feature("lngr2").set("legendmethod", "manual");
    model.result("pg8").feature("lngr2").set("legends", new String[]{"N \u578b (\u84dd\u8272)"});
    model.result("pg8").feature("lngr2").feature().create("filt1", "LineGraphFilter");
    model.result("pg8").feature("lngr2").feature("filt1").set("expr", "semi.Nd-semi.Na > 1[1/cm^3]");
    model.result("pg8").set("titletype", "none");
    model.result("pg8").set("legendpos", "uppermiddle");
    model.result("pg8")
         .set("ylabel", "\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 \\vert N<sub>d</sub> - N<sub>a</sub>\\vert, 1/cm<sup>3</sup>");
    model.result("pg8").set("ylog", true);
    model.result("pg8").feature("lngr1").label("P \u578b");
    model.result("pg8").feature("lngr2").label("N \u578b");
    model.result("pg8").label("\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 (semi) 1");
    model.result("pg5").run();
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevelinput", "first", 2);
    model.result("pg8").setIndex("looplevelinput", "first", 1);
    model.result("pg8").setIndex("looplevelinput", "first", 0);
    model.result("pg8").run();
    model.result("pg5").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg5");
    model.nodeGroup("grp2").add("plotgroup", "pg6");
    model.nodeGroup("grp2").add("plotgroup", "pg7");
    model.nodeGroup("grp2").add("plotgroup", "pg8");
    model.nodeGroup("grp2").label("\u7814\u7a76 2");

    model.result().create("pg9", "PlotGroup1D");

    model.nodeGroup("grp2").add("plotgroup", "pg9");

    model.result("pg9").run();
    model.result("pg9").label("I vs. P_780\uff08\u56fe 7a\uff09");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").setIndex("looplevelinput", "manualindices", 1);
    model.result("pg9").setIndex("looplevelindices", "2 3 4", 1);
    model.result("pg9").set("xlog", true);
    model.result("pg9").set("ylog", true);
    model.result("pg9").set("legendpos", "upperleft");
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1").setIndex("expr", "semi.I0_1/area", 0);
    model.result("pg9").feature("glob1").setIndex("unit", "mA/cm^2", 0);
    model.result("pg9").feature("glob1").setIndex("descr", "I", 0);
    model.result("pg9").feature("glob1").set("xdata", "expr");
    model.result("pg9").feature("glob1").set("xdataexpr", "P0_780/1[mW/cm^2]");
    model.result("pg9").feature("glob1").set("autodescr", false);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");

    model.nodeGroup("grp2").add("plotgroup", "pg10");

    model.result("pg10").run();
    model.result("pg10").label("ft_dot vs. P_780\uff08\u56fe 7b\uff09");
    model.result("pg10").set("ylog", false);
    model.result("pg10").run();
    model.result("pg10").feature("glob1").setIndex("expr", "intop1(ft_dot)/t_well", 0);
    model.result("pg10").feature("glob1").setIndex("descr", "QD \u6001\u7684\u5360\u6709\u7387", 0);
    model.result("pg10").run();

    model.study().create("std3");
    model.study("std3").label("\u7814\u7a76 3 - \u659c\u5761 P_1300");
    model.study("std3").feature().copy("stat", "std2/stat");
    model.study("std3").feature("stat").move("pname", new int[]{2}, -1);
    model.study("std3").feature("stat").move("plistarr", new int[]{2}, -1);
    model.study("std3").feature("stat").move("punit", new int[]{2}, -1);
    model.study("std3").feature("stat").setIndex("plistarr", "0.064 0.63 7.7", 1);
    model.study("std3").feature("stat").setIndex("plistarr", "10^range(0,0.25,3)", 2);
    model.study("std3").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").label("\u80fd\u7ea7 (semi) 2");
    model.result("pg11").set("data", "dset4");
    model.result("pg11").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg11").set("titletype", "manual");
    model.result("pg11").set("title", "Energy Diagram");
    model.result("pg11").set("ylabel", "Energy (eV)");
    model.result("pg11").feature().create("lngr1", "LineGraph");
    model.result("pg11").feature("lngr1").label("\u5bfc\u5e26\u80fd\u7ea7");
    model.result("pg11").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg11").feature("lngr1").set("expr", "semi.Ec_e");
    model.result("pg11").feature("lngr1").set("unit", "eV");
    model.result("pg11").feature("lngr1").set("linecolor", "blue");
    model.result("pg11").feature("lngr1").set("legend", true);
    model.result("pg11").feature("lngr1").set("legendmethod", "manual");
    model.result("pg11").feature("lngr1").set("legends", new String[]{"Ec"});
    model.result("pg11").feature("lngr1").set("resolution", "norefine");
    model.result("pg11").feature("lngr1").set("smooth", "everywhere");
    model.result("pg11").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg11").feature("lngr1").set("data", "parent");
    model.result("pg11").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg11").feature("lngr1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result("pg11").feature().create("lngr2", "LineGraph");
    model.result("pg11").feature("lngr2").label("\u7535\u5b50\u51c6\u8d39\u7c73\u80fd\u7ea7");
    model.result("pg11").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg11").feature("lngr2").set("expr", "semi.Efn_e");
    model.result("pg11").feature("lngr2").set("unit", "eV");
    model.result("pg11").feature("lngr2").set("linestyle", "dashed");
    model.result("pg11").feature("lngr2").set("linecolor", "black");
    model.result("pg11").feature("lngr2").set("legend", true);
    model.result("pg11").feature("lngr2").set("legendmethod", "manual");
    model.result("pg11").feature("lngr2").set("legends", new String[]{"Efn"});
    model.result("pg11").feature("lngr2").set("resolution", "norefine");
    model.result("pg11").feature("lngr2").set("smooth", "everywhere");
    model.result("pg11").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg11").feature("lngr2").set("data", "parent");
    model.result("pg11").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg11").feature("lngr2").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result("pg11").feature().create("lngr3", "LineGraph");
    model.result("pg11").feature("lngr3").label("\u7a7a\u7a74\u51c6\u8d39\u7c73\u80fd\u7ea7");
    model.result("pg11").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg11").feature("lngr3").set("expr", "semi.Efp_e");
    model.result("pg11").feature("lngr3").set("unit", "eV");
    model.result("pg11").feature("lngr3").set("linestyle", "dotted");
    model.result("pg11").feature("lngr3").set("linecolor", "black");
    model.result("pg11").feature("lngr3").set("legend", true);
    model.result("pg11").feature("lngr3").set("legendmethod", "manual");
    model.result("pg11").feature("lngr3").set("legends", new String[]{"Efp"});
    model.result("pg11").feature("lngr3").set("resolution", "norefine");
    model.result("pg11").feature("lngr3").set("smooth", "everywhere");
    model.result("pg11").feature("lngr3").set("showsolutionparams", "on");
    model.result("pg11").feature("lngr3").set("data", "parent");
    model.result("pg11").feature("lngr3").selection().geom("geom1", 1);
    model.result("pg11").feature("lngr3").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result("pg11").feature().create("lngr4", "LineGraph");
    model.result("pg11").feature("lngr4").label("\u4ef7\u5e26\u80fd\u7ea7");
    model.result("pg11").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg11").feature("lngr4").set("expr", "semi.Ev_e");
    model.result("pg11").feature("lngr4").set("unit", "eV");
    model.result("pg11").feature("lngr4").set("linecolor", "green");
    model.result("pg11").feature("lngr4").set("legend", true);
    model.result("pg11").feature("lngr4").set("legendmethod", "manual");
    model.result("pg11").feature("lngr4").set("legends", new String[]{"Ev"});
    model.result("pg11").feature("lngr4").set("resolution", "norefine");
    model.result("pg11").feature("lngr4").set("smooth", "everywhere");
    model.result("pg11").feature("lngr4").set("showsolutionparams", "on");
    model.result("pg11").feature("lngr4").set("data", "parent");
    model.result("pg11").feature("lngr4").selection().geom("geom1", 1);
    model.result("pg11").feature("lngr4").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").label("\u8f7d\u6d41\u5b50\u6d53\u5ea6 (semi) 2");
    model.result("pg12").set("data", "dset4");
    model.result("pg12").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg12").set("ylabel", "Carrier concentration (1/cm^3)");
    model.result("pg12").set("ylog", true);
    model.result("pg12").feature().create("lngr1", "LineGraph");
    model.result("pg12").feature("lngr1").label("\u7535\u5b50\u6d53\u5ea6");
    model.result("pg12").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg12").feature("lngr1").set("expr", "semi.N");
    model.result("pg12").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg12").feature("lngr1").set("legend", true);
    model.result("pg12").feature("lngr1").set("legendmethod", "manual");
    model.result("pg12").feature("lngr1").set("legends", new String[]{"electrons"});
    model.result("pg12").feature("lngr1").set("resolution", "norefine");
    model.result("pg12").feature("lngr1").set("smooth", "everywhere");
    model.result("pg12").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg12").feature("lngr1").set("data", "parent");
    model.result("pg12").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg12").feature("lngr1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result("pg12").feature().create("lngr2", "LineGraph");
    model.result("pg12").feature("lngr2").label("\u7a7a\u7a74\u6d53\u5ea6");
    model.result("pg12").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg12").feature("lngr2").set("expr", "semi.P");
    model.result("pg12").feature("lngr2").set("unit", "1/cm^3");
    model.result("pg12").feature("lngr2").set("legend", true);
    model.result("pg12").feature("lngr2").set("legendmethod", "manual");
    model.result("pg12").feature("lngr2").set("legends", new String[]{"holes"});
    model.result("pg12").feature("lngr2").set("resolution", "norefine");
    model.result("pg12").feature("lngr2").set("smooth", "everywhere");
    model.result("pg12").feature("lngr2").set("showsolutionparams", "on");
    model.result("pg12").feature("lngr2").set("data", "parent");
    model.result("pg12").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg12").feature("lngr2").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").label("\u7535\u52bf (semi) 2");
    model.result("pg13").set("data", "dset4");
    model.result("pg13").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result("pg13").set("ylabel", "Electric Potential (V)");
    model.result("pg13").feature().create("lngr1", "LineGraph");
    model.result("pg13").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg13").feature("lngr1").set("expr", "V");
    model.result("pg13").feature("lngr1").set("resolution", "norefine");
    model.result("pg13").feature("lngr1").set("smooth", "everywhere");
    model.result("pg13").feature("lngr1").set("showsolutionparams", "on");
    model.result("pg13").feature("lngr1").set("data", "parent");
    model.result("pg13").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg13").feature("lngr1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result().create("pg14", "PlotGroup1D");
    model.result("pg14").set("data", "dset4");
    model.result("pg14").create("lngr1", "LineGraph");
    model.result("pg14").create("lngr2", "LineGraph");
    model.result("pg14").feature("lngr1").selection().all();
    model.result("pg14").feature("lngr1").set("xdataexpr", "X");
    model.result("pg14").feature("lngr1").set("expr", "semi.Nnetdop");
    model.result("pg14").feature("lngr1").set("unit", "1/cm^3");
    model.result("pg14").feature("lngr1").set("linecolor", "red");
    model.result("pg14").feature("lngr1").set("legend", true);
    model.result("pg14").feature("lngr1").set("legendmethod", "manual");
    model.result("pg14").feature("lngr1").set("legends", new String[]{"P \u578b (\u7ea2\u8272)"});
    model.result("pg14").feature("lngr1").feature().create("filt1", "LineGraphFilter");
    model.result("pg14").feature("lngr1").feature("filt1").set("expr", "semi.Na-semi.Nd > 1[1/cm^3]");
    model.result("pg14").feature("lngr2").selection().all();
    model.result("pg14").feature("lngr2").set("xdataexpr", "X");
    model.result("pg14").feature("lngr2").set("expr", "semi.Nnetdop");
    model.result("pg14").feature("lngr2").set("unit", "1/cm^3");
    model.result("pg14").feature("lngr2").set("linecolor", "blue");
    model.result("pg14").feature("lngr2").set("legend", true);
    model.result("pg14").feature("lngr2").set("legendmethod", "manual");
    model.result("pg14").feature("lngr2").set("legends", new String[]{"N \u578b (\u84dd\u8272)"});
    model.result("pg14").feature("lngr2").feature().create("filt1", "LineGraphFilter");
    model.result("pg14").feature("lngr2").feature("filt1").set("expr", "semi.Nd-semi.Na > 1[1/cm^3]");
    model.result("pg14").set("titletype", "none");
    model.result("pg14").set("legendpos", "uppermiddle");
    model.result("pg14")
         .set("ylabel", "\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 \\vert N<sub>d</sub> - N<sub>a</sub>\\vert, 1/cm<sup>3</sup>");
    model.result("pg14").set("ylog", true);
    model.result("pg14").feature("lngr1").label("P \u578b");
    model.result("pg14").feature("lngr2").label("N \u578b");
    model.result("pg14").label("\u51c0\u63ba\u6742\u5242\u6d53\u5ea6 (semi) 2");
    model.result("pg11").run();
    model.result("pg14").run();
    model.result("pg14").setIndex("looplevelinput", "first", 2);
    model.result("pg14").setIndex("looplevelinput", "first", 1);
    model.result("pg14").setIndex("looplevelinput", "first", 0);
    model.result("pg14").run();
    model.result("pg11").run();

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").set("type", "plotgroup");
    model.nodeGroup().move("grp3", 2);
    model.nodeGroup("grp3").add("plotgroup", "pg11");
    model.nodeGroup("grp3").add("plotgroup", "pg12");
    model.nodeGroup("grp3").add("plotgroup", "pg13");
    model.nodeGroup("grp3").add("plotgroup", "pg14");
    model.nodeGroup("grp3").label("\u7814\u7a76 3");

    model.result("pg9").run();
    model.result().copy("pg15", "pg9");

    model.nodeGroup("grp3").add("plotgroup", "pg15");

    model.result("pg15").run();
    model.result("pg15").label("I vs. P_1300\uff08\u56fe 7c\uff09");
    model.result("pg15").setIndex("looplevelinput", "all", 1);
    model.result("pg15").set("data", "dset4");
    model.result("pg15").run();
    model.result("pg15").feature("glob1").set("xdataexpr", "P0_1300/1[mW/cm^2]");
    model.result("pg15").run();
    model.result("pg10").run();
    model.result().copy("pg16", "pg10");

    model.nodeGroup("grp3").add("plotgroup", "pg16");

    model.result("pg16").run();
    model.result("pg16").label("ft_dot vs. P_1300\uff08\u56fe 7d\uff09");
    model.result("pg16").setIndex("looplevelinput", "all", 1);
    model.result("pg16").set("data", "dset4");
    model.result("pg16").set("legendpos", "center");
    model.result("pg16").run();
    model.result("pg16").feature("glob1").set("xdataexpr", "P0_1300/1[mW/cm^2]");
    model.result("pg16").run();

    model
         .title("\u5728 AlGaAs/GaAs \u91cf\u5b50\u9631\u4e2d\u5d4c\u5165 InAs \u91cf\u5b50\u70b9\u592a\u9633\u80fd\u7535\u6c60");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u4e00\u79cd\u8fd1\u4f3c\u65b9\u6cd5\u6765\u6a21\u62df Asahi \u7b49\u4eba\u5728\u53c2\u8003\u8bba\u6587\u4e2d\u63cf\u8ff0\u7684\u9631\u4e2d\u70b9\u592a\u9633\u80fd\u7535\u6c60\u3002\u91cf\u5b50\u9631\u548c\u91cf\u5b50\u70b9\u5c42\u5747\u88ab\u89c6\u4e3a\u5e26\u9699\u4e2d\u7684\u96c6\u603b\u80fd\u7ea7\uff0c\u4f5c\u8005\u6307\u5b9a\u4e86\u70b9/\u9631\u80fd\u7ea7\u4e0e\u80fd\u5e26\u4e4b\u95f4\u7684\u8dc3\u8fc1\uff0c\u800c\u7535\u6d41\u5bc6\u5ea6\u7684\u8fde\u7eed\u90e8\u5206\u5219\u4e0d\u53d7\u9631\u548c\u70b9\u7684\u5f71\u54cd\u3002\u8fd9\u4e00\u63cf\u8ff0\u7b49\u540c\u4e8e\u201c\u534a\u5bfc\u4f53\u201d\u63a5\u53e3\u4e2d\u7684\u9677\u9631\u7279\u5f81\uff0c\u56e0\u6b64\u672c\u4f8b\u4f7f\u7528\u8be5\u7279\u5f81\u5bf9\u9631\u548c\u70b9\u8fdb\u884c\u5efa\u6a21\uff0c\u8ba1\u7b97\u5f97\u5230\u7684\u5149\u7535\u6d41\u8d8b\u52bf\u548c\u91cf\u5b50\u70b9\u6001\u7684\u5360\u6709\u7387\u4e0e\u8bba\u6587\u4e2d\u663e\u793a\u7684\u7ed3\u679c\u975e\u5e38\u543b\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("dot_in_well_solar_cell.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
